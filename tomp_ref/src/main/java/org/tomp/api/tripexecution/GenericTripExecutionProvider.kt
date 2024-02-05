package org.tomp.api.tripexecution

import io.swagger.model.Asset
import io.swagger.model.FarePart
import io.swagger.model.FarePart.UnitTypeEnum
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
import io.swagger.model.Leg
import io.swagger.model.LegEvent
import io.swagger.model.LegState
import io.swagger.model.Suboperator
import io.swagger.model.Token
import io.swagger.model.TokenDefault
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.repository.DefaultRepository
import java.util.UUID
import kotlin.math.abs
import kotlin.math.sqrt

@Component
@ConditionalOnProperty(value = ["tomp.providers.tripexecution"], havingValue = "generic", matchIfMissing = true)
class GenericTripExecutionProvider : TripExecutionProvider {
    @Autowired
    var repository: DefaultRepository? = null

    @Autowired
    var configuration: ExternalConfiguration? = null
    override fun prepare(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        leg!!.assetAccessData = constructTokenToOpenAsset(body, leg)
        leg.state = LegState.PREPARING
        repository!!.saveLegEvent(id, body)
        return leg
    }

    private fun constructTokenToOpenAsset(body: LegEvent?, planning: Leg?): Token {
        val token = Token()
        token.validFrom = body!!.time
        token.validUntil = ChronoUnit.SECONDS.addTo(planning!!.departureTime, -3600)
        val tokenData = TokenDefault()
        tokenData["code"] = UUID.randomUUID()
        token.tokenData = tokenData
        return token
    }

    override fun assignAsset(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        val asset = Asset()
        asset.id = id
        leg!!.asset = asset
        repository!!.saveLegEvent(id, body)
        return leg
    }

    override fun reserve(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        val asset = Asset()
        asset.id = id
        leg!!.asset = asset
        repository!!.saveLegEvent(id, body)
        return leg
    }

    override fun setInUse(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val execution = repository!!.getLeg(id)
        val suboperator = Suboperator()
        suboperator.maasId = maasId
        execution!!.suboperator = suboperator
        if (execution.asset == null && body!!.asset != null) {
            execution.asset = body.asset
        }
        execution.from = body!!.asset!!.overriddenProperties!!.location
        execution.departureTime = body.time
        repository!!.saveLegEvent(id, body)
        execution.state = LegState.IN_USE
        createJournalItem(id, execution, body, maasId)
        return execution
    }

    override fun pause(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        leg!!.state = LegState.PAUSED
        repository!!.saveLegEvent(id, body)
        return leg
    }

    override fun startFinishing(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        leg!!.state = LegState.FINISHING
        repository!!.saveLegEvent(id, body)
        return leg
    }

    override fun finish(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        val leg = repository!!.getLeg(id)
        leg!!.state = LegState.FINISHED
        leg.to = body!!.asset!!.overriddenProperties!!.location
        leg.arrivalTime = body.time
        leg.pricing = leg.pricing
        leg.pricing!!.isEstimated = false
        finaliseJournalItem(id, leg, body, maasId)
        repository!!.saveLegEvent(id, body)
        return leg
    }

    private fun createJournalItem(id: String?, execution: Leg?, body: LegEvent?, maasId: String?) {
        val entry = JournalEntry()
        entry.journalId = id
        entry.state = null
        repository!!.saveJournalEntry(entry, maasId)
    }

    private fun calculateFare(execution: Leg?): Float {
        var amount = 0.0
        val fare = execution!!.pricing
        for (part in fare!!.getParts()!!) {
            when (part.type) {
                FarePart.TypeEnum.FIXED -> amount += part.amount!!.toDouble()
                FarePart.TypeEnum.FLEX -> amount += calculateFlexPart(part, execution)
                FarePart.TypeEnum.MAX -> if (amount > part.amount!!.toDouble()) {
                    amount = part.amount!!.toDouble()
                    break
                }

                else -> {}
            }
        }
        return amount.toFloat()
    }

    private fun calculateFlexPart(part: FarePart, execution: Leg?): Double {
        val amount = part.amount!!.toDouble()
        when (part.unitType) {
            UnitTypeEnum.HOUR -> return ChronoUnit.HOURS.between(execution!!.departureTime, execution.arrivalTime)
                .toDouble()

            UnitTypeEnum.KM -> return amount * (execution!!.distance!!.toDouble() / 1000)
            UnitTypeEnum.MILE -> return amount * (execution!!.distance!!.toDouble() / 1609)
            UnitTypeEnum.MINUTE -> return ChronoUnit.MINUTES.between(execution!!.departureTime, execution.arrivalTime).toDouble()
            UnitTypeEnum.PERCENTAGE -> {}
            UnitTypeEnum.SECOND -> return ChronoUnit.SECONDS.between(execution!!.departureTime, execution.arrivalTime).toDouble()
            else -> {}
        }
        return 0
    }

    private fun finaliseJournalItem(id: String?, execution: Leg?, legEvent: LegEvent?, maasId: String?) {
        val entry = repository!!.getLastStartJournalEntry(maasId, id)
        entry!!.usedTime = ChronoUnit.SECONDS.between(execution!!.departureTime, execution.arrivalTime).toInt()
        entry.distance = calculateDistance(entry, legEvent).toFloat()
        val amount = calculateFare(execution)
        entry.amount = amount
        entry.currencyCode = configuration.getCurrencyCode()
        val vatRate = configuration.getVatRate()
        entry.vatRate = vatRate.toFloat()
        entry.amountExVat = (amount.toDouble() * ((100.0 - vatRate) / 100.0)).toFloat()
        entry.vatCountryCode = configuration.getCurrencyCode()
        entry.expirationDate = ChronoUnit.DAYS.addTo(OffsetDateTime.now(), configuration.getExpirationDays().toLong())
        entry.state = JournalState.TO_INVOICE
    }

    private fun calculateDistance(entry: JournalEntry?, legEvent: LegEvent?): Int {
        val legEvents = repository!!.getLegEvents(entry!!.journalId)
        val coordinates = legEvents!![0]!!.asset!!.overriddenProperties!!.location!!.coordinates
        val coordinates2 = legEvent!!.asset!!.overriddenProperties!!.location!!.coordinates
        var lat = (coordinates!!.lat!! - abs(coordinates2!!.lat!!.toDouble())).toFloat()
        var lon = (coordinates.lng!! - abs(coordinates2.lng!!.toDouble())).toFloat()
        lat = lat * lat
        lon = lon * lon
        return sqrt(lat.toDouble() + lon.toDouble()).toInt()
    }
}
