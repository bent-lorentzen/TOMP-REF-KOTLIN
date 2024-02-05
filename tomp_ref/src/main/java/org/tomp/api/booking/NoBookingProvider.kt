package org.tomp.api.booking

import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingRequest
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.booking"], havingValue = "none", matchIfMissing = false)
class NoBookingProvider : BookingProvider {
    override fun setRequest(request: HttpServletRequest?) {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun addNewBookingEvent(body: BookingOperation?, acceptLanguage: String?, id: String): Booking? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getBooking(id: String?): Booking? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun subscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun unsubscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?) {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }
}
