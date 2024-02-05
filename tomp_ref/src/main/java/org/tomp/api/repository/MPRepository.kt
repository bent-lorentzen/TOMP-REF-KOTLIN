package org.tomp.api.repository

import io.swagger.model.Booking
import io.swagger.model.Leg
import org.springframework.stereotype.Component
import org.tomp.api.model.TransportOperator
import org.tomp.api.model.Trip
import java.util.AbstractMap

@Component
class MPRepository {
    fun saveBooking(booking: Booking, trip: Trip) {
        options[booking.id] = trip
    }

    fun getTrip(id: String?): Trip? {
        return options[id]
    }

    fun addClientBooking(booking: Booking, operator: TransportOperator?, clientBooking: Booking) {
        var clientBookings = bookings[booking]
        if (clientBookings == null) {
            clientBookings = ArrayList()
        }
        clientBookings.add(AbstractMap.SimpleEntry(clientBooking, operator))
        bookings[booking] = clientBookings
    }

    fun getClientBooking(id: String): Booking? {
        for ((_, value) in bookings) {
            for ((key) in value) {
                if (key.id == id) {
                    return key
                }
            }
        }
        return null
    }

    fun getClientBookings(maasBooking: Booking): ArrayList<AbstractMap.SimpleEntry<Booking, TransportOperator?>> {
        return bookings[maasBooking]!!
    }

    fun saveLeg(id: String, leg: Leg) {
        legs[id] = leg
    }

    fun getLeg(id: String): Leg? {
        return legs[id]
    }

    companion object {
        private val options: MutableMap<String?, Trip> = HashMap()
        private val bookings: MutableMap<Booking, ArrayList<AbstractMap.SimpleEntry<Booking, TransportOperator?>>> = HashMap()
        private val legs: MutableMap<String, Leg> = HashMap()
    }
}
