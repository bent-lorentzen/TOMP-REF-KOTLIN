package org.tomp.api.booking

import io.swagger.client.ApiException
import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingOperation.OperationEnum
import io.swagger.model.BookingRequest
import io.swagger.model.BookingState
import io.swagger.model.ExtraCosts
import io.swagger.model.JournalCategory
import io.swagger.model.JournalEntry
import io.swagger.model.JournalEntry.DistanceTypeEnum
import io.swagger.model.JournalState
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.controllers.WebsocketController
import org.tomp.api.model.LookupService
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.utils.ClientUtil
import org.tomp.api.utils.FareUtil
import org.tomp.api.utils.LegUtil
import org.tomp.api.utils.MailUtil
import java.util.Optional
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.booking"], havingValue = "shared-car", matchIfMissing = false)
open class SharedCarBookingProvider @Autowired constructor(
    private val repository: DefaultRepository, mailService: Optional<MailUtil?>,
    private val configuration: ExternalConfiguration, private val clientUtil: ClientUtil, private val lookupService: LookupService
) : BookingProvider {
    private val mailService: MailUtil?
    private var request: HttpServletRequest? = null

    @Autowired
    var fareUtil: FareUtil? = null

    @Autowired
    var legUtil: LegUtil? = null

    @Autowired
    var websocket: WebsocketController? = null

    init {
        this.mailService = if (mailService.isPresent) mailService.get() else null
    }

    override fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking? {
        log.info("Booking request {}", body!!.id)
        val id = body.id
        val booking = repository.getBooking(id)
        booking!!.id = id
        booking.state = BookingState.PENDING
        booking.from!!.physicalAddress = body.from!!.physicalAddress
        booking.to!!.physicalAddress = body.to!!.physicalAddress
        booking.customer = body.customer
        repository.saveBooking(booking)
        return booking
    }

    protected fun validateId(id: String?) {
        if (repository.getSavedOption(id) == null) {
            log.error("Did not provide this leg {}", id)
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    override fun addNewBookingEvent(body: BookingOperation?, acceptLanguage: String?, id: String): Booking? {
        validateId(id)
        log.info("{} {}", body!!.operation, id)
        return when (body.operation) {
            OperationEnum.COMMIT -> commitBooking(id)
            OperationEnum.CANCEL -> cancelBooking(id)
            OperationEnum.DENY -> throw UnsupportedOperationException()
            OperationEnum.EXPIRE -> throw UnsupportedOperationException()
            else -> throw UnsupportedOperationException()
        }
    }

    protected fun cancelBooking(id: String?): Booking? {
        val booking = repository.getBooking(id)
        if (booking!!.state === BookingState.CONFIRMED) {
            // TODO test if it's not yet started!
            booking!!.state = BookingState.CANCELLED
            val entry = JournalEntry()
            val savedOption = repository.getLeg(id)
            val calculated = fareUtil!!.calculateFare(savedOption)
            if (calculated > 0) {
                entry.amount = (calculated * 0.05).toFloat() // 5% fine in case of cancelling a booked leg
                val costs = ExtraCosts()
                costs.amount = (calculated * 0.05).toFloat()
                costs.category = JournalCategory.FINE
                entry.details = costs
                repository.saveJournalEntry(entry, request!!.getHeader("maas-id"))
            }
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Only CONFIRMED bookings can be cancelled")
        }
        return booking
    }

    protected fun commitBooking(id: String?): Booking? {
        val booking = repository.getBooking(id)
        booking!!.state = BookingState.CONDITIONAL_CONFIRMED
        val maasId = request!!.getHeader(MAAS_ID)
        val extraData = HashMap<String?, Any?>()
        extraData[MAAS_ID] = maasId
        booking.setExtraData(extraData)
        repository.saveBooking(booking)
        val entry = JournalEntry()
        val leg = repository.getLeg(id)
        entry.state = JournalState.TO_INVOICE
        entry.details = leg!!.pricing
        leg.pricing!!.isEstimated = false
        entry.journalId = leg.id
        val f = FareUtil()
        val l = LegUtil()
        val distance = l.getDistance(leg)
        entry.distance = (distance / 1000).toFloat()
        entry.distanceType = DistanceTypeEnum.KM
        val duration = l.getDuration(leg)
        entry.usedTime = duration
        entry.amount = f.calculateFare(leg.pricing, (duration / 60).toDouble(), distance).toFloat()
        repository.saveJournalEntry(entry, request!!.getHeader("maas-id"))
        sendMail(booking)
        return booking
    }

    private fun sendMail(booking: Booking?) {
        var mpUrl = configuration.externalUrl
        if (mailService != null) {
            val builder = getBookingRequestText(booking)
            var to = booking!!.customer!!.email
            if (to == null || to == "") {
                to = configuration.bookingMailBox
            }
            val t = SendMailThread(
                configuration.bookingMailBox, to, booking.id,
                builder.toString()
            )
            Thread(t).start()
        } else {
            if (mpUrl!!.endsWith("/")) {
                mpUrl = mpUrl.substring(0, mpUrl.length - 1)
            }
        }
        val url = mpUrl + "/postponed/" + booking!!.id
        log.info("URL: {}", url)
        websocket!!.sendMessage(url, null)
    }

    private inner class SendMailThread(private val from: String?, private val to: String?, private val bookingId: String?, private val body: String) :
        Runnable {
        override fun run() {
            mailService!!.sendSimpleMessage(from, to, "Booking request: $bookingId", body)
        }
    }

    private fun getBookingRequestText(booking: Booking?): StringBuilder {
        val builder = StringBuilder()
        builder.append("Request for booking ")
        var mpUrl = configuration.externalUrl
        if (mpUrl!!.endsWith("/")) {
            mpUrl = mpUrl.substring(0, mpUrl.length - 1)
        }
        builder.append(mpUrl + "/postponed/" + booking!!.id)
        builder.append("\r\n")
        val customer = booking.customer
        if (customer != null) {
            builder.append("Customer: ")
            if (customer.firstName != null) {
                builder.append(customer.firstName)
            }
            builder.append(" ")
            if (customer.lastName != null) {
                builder.append(customer.lastName)
            }
            builder.append("\r\n")
            builder.append("Birth date: ")
            if (customer.birthDate != null) {
                builder.append(customer.birthDate)
            }
            builder.append("\r\n")
        }
        val leg = repository.getLeg(booking.id)
        val from = leg!!.from!!.coordinates
        builder.append("Start ")
        builder.append(leg.departureTime)
        builder.append(" - ")
        builder.append(from!!.lat)
        builder.append("/")
        builder.append(from.lng)
        builder.append("\r\n")
        val to = leg.to!!.coordinates
        builder.append("End ")
        builder.append(leg.arrivalTime)
        builder.append(" - ")
        builder.append(to!!.lat)
        builder.append("/")
        builder.append(to.lng)
        builder.append("\r\n")
        return builder
    }

    override fun setRequest(request: HttpServletRequest?) {
        this.request = request
    }

    protected fun getRequest(): HttpServletRequest? {
        return request
    }

    fun getPostponedBookingHtml(id: String, url: String): String {
        val booking = repository.getBooking(id)
        val builder = getBookingRequestText(booking)
        val firstPart = builder.toString().replace("\r\n", "<br>")
        return (firstPart + "<form action=\"" + url + "\" method=\"post\"> <div class=\"control\">"
                + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" + "  <label class=\"radio\">"
                + "    <input type=\"radio\" name=\"choice\" value=0>DENY" + "  </label><br>"
                + "  <label class=\"radio\">"
                + "    <input type=\"radio\" name=\"choice\" value=1 checked=\"checked\">COMMIT" + "  </label>"
                + "</div>" + "<input type=\"submit\" value=\"Submit\"> " + "</form>")
    }

    open fun saveResult(id: String?, committed: Boolean, remark: String?) {
        val booking = repository.getBooking(id)
        booking!!.state = if (committed) BookingState.CONFIRMED else BookingState.CANCELLED
        if (!committed) {
            var extraData: MutableMap<String?, Any?>? = booking.getExtraData()
            if (extraData == null) {
                extraData = HashMap()
            }
            extraData["DenyReason"] = remark
            booking.setExtraData(extraData)
        }
        repository.saveBooking(booking)
        val operation = BookingOperation()
        operation.operation = if (committed) OperationEnum.COMMIT else OperationEnum.DENY
        for ((key, value) in (booking.getExtraData() as Map<String?, Any?>?)!!) {
            if (key == MAAS_ID) {
                val mp = lookupService.getMaasOperator(value.toString())
                if (mp != null) {
                    try {
                        clientUtil.post(mp, "/bookings/$id/events", operation, Void::class.java)
                    } catch (e: ApiException) {
                        log.error("MP {} cannot be reached", value)
                        log.error(e.message)
                    }
                } else {
                    log.error("MP not in meta directory: {} or Meta directory not available", value)
                }
                return
            }
        }
    }

    override fun getBooking(id: String?): Booking? {
        return repository.getBooking(id)
    }

    override fun subscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun unsubscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    companion object {
        private const val MAAS_ID = "maas-id"
        private val log = LoggerFactory.getLogger(SharedCarBookingProvider::class.java)
    }
}
