package org.tomp.api.tripexecution

import io.swagger.client.ApiException
import io.swagger.model.Asset
import io.swagger.model.ExtraCosts
import io.swagger.model.JournalCategory
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
import io.swagger.model.Leg
import io.swagger.model.LegEvent
import io.swagger.model.LegEvent.EventEnum
import io.swagger.model.Notification
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.threeten.bp.OffsetDateTime
import org.tomp.api.controllers.WebsocketController
import org.tomp.api.model.LookupService
import org.tomp.api.model.MaasEnvironmentType
import org.tomp.api.model.MaasOperator
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.utils.ClientUtil
import java.util.Random
import java.util.UUID

@Component
@ConditionalOnProperty(value = ["tomp.providers.tripexecution"], havingValue = "taxi", matchIfMissing = false) /*
 * The Taxi Trip Executor is in the lead. It will report the MP about the
 * progress using a) sending the prepare event as soon the taxi starts heading
 * to the pick up point b) sends notifications with ETAs c) sends a notification
 * at arrival at the pick up point d) sends a setInUse event when leaving the
 * pick up point e) sends a finish event when arrived
 */
class TaxiTripExecutionProvider : TripExecutionProvider {
    @Autowired
    private val repository: DefaultRepository? = null

    @Autowired
    private val lookupService: LookupService? = null

    @Autowired
    private val clientUtil: ClientUtil? = null

    @Autowired
    var websocket: WebsocketController? = null
    var tripId: String? = null
    var maasIdFromMP: String? = null
    var mp: MaasOperator? = null
    var random = Random()
    fun startExecution(tripId: String?) {
        this.tripId = tripId
        val booking = repository!!.getBooking(tripId)
        for ((key, value) in (booking!!.getExtraData() as Map<String?, Any?>?)!!) {
            if (key == MAAS_ID) {
                maasIdFromMP = value.toString()
            }
        }
        if (maasIdFromMP == null) return
        findMp()
        waitAFewSeconds()
        sendPrepare()
        var remainingSeconds = random.nextInt(10)
        while (remainingSeconds > 0) {
            waitAFewSeconds()
            sendETA(remainingSeconds)
            val minus = random.nextInt(remainingSeconds)
            if (minus == 0) remainingSeconds = 0 else remainingSeconds -= minus
        }
        sendArrivalAtPickup()
        waitAFewSeconds()
        sendSetInUse()
        waitAFewSeconds()
        sendFinish()
        sendExtraCosts()
    }

    private fun findMp() {
        if (mp == null) {
            mp = lookupService!!.getMaasOperator(maasIdFromMP)
            if (mp == null) {
                mp = MaasOperator()
                mp.setId(UUID.randomUUID().toString())
                mp.setName("Dummy MP")
                mp.setType(MaasEnvironmentType.MP)
                mp.setUrl("http://localhost:7999/")
            }
            return
        }
        if (mp.getId() != maasIdFromMP) {
            mp = lookupService!!.getMaasOperator(maasIdFromMP)
        }
    }

    private fun sendExtraCosts() {
        if (mp != null) {
            try {
                val toSend = ExtraCosts()
                toSend.amount = 3.12f
                toSend.category = JournalCategory.EXTRA_USAGE
                toSend.currencyCode = "EUR"
                toSend.description = "Difference between estimated initial costs"
                val entry = JournalEntry()
                entry.amount = toSend.amount
                entry.comment = toSend.description
                entry.details = toSend
                entry.state = JournalState.TO_INVOICE
                entry.journalId = tripId
                repository!!.saveJournalEntry(entry, maasIdFromMP)
                clientUtil!!.patch(mp, "/payment/$tripId/claim-extra-costs", toSend, Void::class.java)
                websocket!!.sendMessage("PATCH /payment/$tripId/claim-extra-costs", toSend)
            } catch (e: ApiException) {
                log.error("MP {} cannot be reached", maasIdFromMP)
                log.error(e.message)
            }
        } else {
            log.error("MP not in meta directory: {} or Meta directory not available", maasIdFromMP)
        }
    }

    private fun send(url: String, toSend: Any) {
        websocket!!.sendMessage(url, toSend)
        if (mp != null) {
            try {
                clientUtil!!.post(mp, url, toSend, Void::class.java)
            } catch (e: ApiException) {
                log.error("MP {} cannot be reached", maasIdFromMP)
                log.error(e.message)
            }
        } else {
            log.error("MP not in meta directory: {} or Meta directory not available", maasIdFromMP)
        }
    }

    private fun sendFinish() {
        val event = LegEvent()
        event.event = EventEnum.FINISH
        send("/legs/$tripId/events", event)
    }

    private fun sendSetInUse() {
        val event = LegEvent()
        event.event = EventEnum.SET_IN_USE
        send("/legs/$tripId/events", event)
    }

    private fun sendArrivalAtPickup() {
        val notification = Notification()
        notification.type = Notification.TypeEnum.ETA
        notification.comment = "Arrived"
        send("/bookings/$tripId/notifications", notification)
    }

    private fun sendETA(remainingSeconds: Int) {
        val notification = Notification()
        notification.type = Notification.TypeEnum.ETA
        notification.comment = String.format("Seconds to ETA: %s", remainingSeconds)
        send("/bookings/$tripId/notifications", notification)
    }

    private fun sendPrepare() {
        val event = LegEvent()
        event.event = EventEnum.PREPARE
        event.time = OffsetDateTime.now()
        val asset = Asset()
        asset.id = "ZH 445 789"
        event.asset = asset
        send("/legs/$tripId/events", event)
    }

    private fun waitAFewSeconds() {
        try {
            // Thread.sleep(1000L * random.nextInt(5));
            Thread.sleep(20000L)
        } catch (e: InterruptedException) {
            log.error(e.message)
        }
    }

    override fun prepare(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun assignAsset(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun reserve(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun setInUse(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun pause(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun startFinishing(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun finish(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    companion object {
        private const val MAAS_ID = "maas-id"
        private val log = LoggerFactory.getLogger(TaxiTripExecutionProvider::class.java)
    }
}
