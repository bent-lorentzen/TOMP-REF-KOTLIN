package org.tomp.api.booking

import io.swagger.client.ApiException
import io.swagger.model.Address
import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingOperation.OperationEnum
import io.swagger.model.BookingRequest
import io.swagger.model.BookingState
import io.swagger.model.Place
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.tomp.api.model.MaasOperator
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.utils.ClientUtil
import org.tomp.api.utils.GeoCoderUtil
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.booking"], havingValue = "generic", matchIfMissing = true)
open class GenericBookingProvider : BookingProvider {
    private val listeners: MutableList<String?> = ArrayList()

    @Autowired
    var clientUtil: ClientUtil? = null

    @Autowired
    var geocoderUtil: GeoCoderUtil? = null

    @Autowired
    var repository: DefaultRepository? = null
    override fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking? {
        log.info("POST bookings {}", body!!.id)
        val id = body.id
        validateId(id)
        val booking = repository!!.getSavedOption(id)
        booking!!.state = BookingState.PENDING
        if (geocoderUtil!!.isActive) {
            val from: @NotNull @Valid Place? = booking.from
            val to: @Valid Place? = booking.to
            var address = from!!.physicalAddress
            if (address == null) {
                address = Address()
                val p = Place()
                p.physicalAddress = address
                booking.from = p
            }
            var coord = booking.from!!.coordinates
            geocoderUtil!!.getPhysicalAddressByCoordinate(coord, address)
            address = to!!.physicalAddress
            if (address == null) {
                address = Address()
                val p = Place()
                p.physicalAddress = address
                booking.to = p
            }
            coord = booking.to!!.coordinates
            geocoderUtil!!.getPhysicalAddressByCoordinate(coord, address)
        }
        repository!!.saveBooking(booking)
        return booking
    }

    protected fun validateId(id: String?) {
        if (repository!!.getSavedOption(id) == null) {
            log.error("Did not provide this leg {}", id)
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    override fun addNewBookingEvent(body: BookingOperation?, acceptLanguage: String?, id: String): Booking? {
        validateId(id)
        log.info("POST bookings/{}/events {}", id, body!!.operation)
        val booking = repository!!.getBooking(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        when (body.operation) {
            OperationEnum.COMMIT -> booking.state = BookingState.CONFIRMED
            OperationEnum.CANCEL -> booking.state = BookingState.CANCELLED
            OperationEnum.DENY -> booking.state = BookingState.RELEASED
            OperationEnum.EXPIRE -> booking.state = BookingState.EXPIRED
        }
        informListeners(body, id)
        repository!!.saveBooking(booking)
        return booking
    }

    private fun informListeners(body: BookingOperation?, id: String) {
        if (listeners.contains(id)) {
            val to = MaasOperator()
            // to.setUrl(listener.getWebhook());
            try {
                clientUtil!!.post(to, "", body, Void::class.java)
            } catch (e: ApiException) {
                log.error(e.message)
            }
        }
    }

    override fun setRequest(request: HttpServletRequest?) {}
    override fun getBooking(id: String?): Booking? {
        return repository!!.getBooking(id)
    }

    override fun subscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        listeners.add(id)
    }

    override fun unsubscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        listeners.remove(id)
    }

    companion object {
        private val log = LoggerFactory.getLogger(GenericBookingProvider::class.java)
    }
}
