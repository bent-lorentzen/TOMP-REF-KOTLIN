package org.tomp.api.booking

import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingRequest
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

interface BookingProvider {
    fun setRequest(request: HttpServletRequest?)
    fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking?
    fun addNewBookingEvent(body: BookingOperation?, acceptLanguage: String?, id: String): Booking?
    fun getBooking(id: String?): Booking?
    fun subscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?)
    fun unsubscribeToBookings(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?)
}
