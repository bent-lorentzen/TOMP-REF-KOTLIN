package org.tomp.api.repository

import io.swagger.model.Booking
import io.swagger.model.ExtraCosts
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
import io.swagger.model.Leg
import io.swagger.model.LegEvent
import io.swagger.model.Planning
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.tomp.api.configuration.ExternalConfiguration
import java.util.function.Predicate
import java.util.stream.Stream

@Component
class DefaultRepository {
    @Autowired
    var configuration: ExternalConfiguration? = null
    private val bookings: MutableMap<String?, Booking?> = HashMap()
    private val journalEntries: MutableMap<String?, MutableMap<String?, MutableList<JournalEntry>>> = HashMap()
    private val legEvents: MutableMap<String?, MutableList<LegEvent?>> = HashMap()
    fun saveBookingOption(optionsToSave: Planning?) {
        if (optionsToSave == null) {
            return
        }
        for (booking in optionsToSave.getOptions()!!) {
            log.info("Saved booking: {}", booking.id)
            bookings[booking.id] = booking
        }
    }

    fun getSavedOption(id: String?): Booking? {
        return bookings[id]
    }

    fun saveBooking(booking: Booking?) {
        bookings[booking!!.id] = booking
    }

    fun getBooking(id: String?): Booking? {
        return bookings[id]
    }

    fun getBookings(predicate: Predicate<in Booking?>?): Stream<Booking?> {
        return bookings.values.stream().filter(predicate)
    }

    fun getLeg(id: String?): Leg? {
        for (b in bookings.values) {
            for (leg in b!!.getLegs()!!) {
                if (leg.id == id) {
                    return leg
                }
            }
        }
        return null
    }

    fun saveLegEvent(id: String?, body: LegEvent?) {
        if (!legEvents.containsKey(id)) {
            legEvents[id] = ArrayList()
        }
        legEvents[id]!!.add(body)
    }

    fun getLegEvents(id: String?): List<LegEvent?> {
        return legEvents[id]!!
    }

    fun saveJournalEntry(entry: JournalEntry, maasId: String?) {
        val journalId = entry.journalId
        if (!journalEntries.containsKey(maasId)) {
            journalEntries[maasId] = HashMap()
        }
        val journalItemsPerMP = journalEntries[maasId]!!
        calculateSequenceId(journalItemsPerMP, entry)
        if (!journalItemsPerMP.containsKey(journalId)) {
            journalItemsPerMP[journalId] = ArrayList()
        }
        journalItemsPerMP[journalId]!!.add(entry)
    }

    private fun calculateSequenceId(journalItemsPerTO: Map<String?, MutableList<JournalEntry>>, entry: JournalEntry) {
        val journal: List<JournalEntry>? = journalItemsPerTO[entry.journalId]
        if (journal == null) entry.journalSequenceId = "0" else {
            entry.journalSequenceId = journalItemsPerTO.size.toString()
        }
    }

    fun getLastStartJournalEntry(maasId: String?, id: String?): JournalEntry {
        val list: List<JournalEntry> = journalEntries[maasId]!![id]!!
        for (i in list.indices.reversed()) {
            val journalEntry = list[i]
            if (journalEntry.state == null) {
                return journalEntry
            }
        }
        return list[0]
    }

    fun getJournalEntries(
        acceptLanguage: String?, from: OffsetDateTime?, to: OffsetDateTime?,
        state: JournalState?, category: String?, id: String?, maasId: String?
    ): List<JournalEntry> {
        val entries = ArrayList<JournalEntry>()
        val map: Map<String?, MutableList<JournalEntry>>? = journalEntries[maasId]
        if (map != null) {
            for (journal in map.values) {
                for (entry in journal) {
                    if (conditionsMet(entry, from, to, state, category, id)) {
                        entries.add(entry)
                    }
                }
            }
        }
        return entries
    }

    private fun conditionsMet(
        entry: JournalEntry?, from: OffsetDateTime?, to: OffsetDateTime?, state: JournalState?,
        category: String?, id: String?
    ): Boolean {
        if (id != null && entry!!.journalId == id) {
            return true
        }
        if (entry == null || entry.expirationDate == null || entry.state == null) return false
        if (to != null && entry.expirationDate!!.isAfter(to)) return false
        if (from != null && entry.expirationDate!!.isBefore(from)) return false
        if (state != null && entry.state !== state) return false
        if (category != null && entry.details != null && entry.details is ExtraCosts) {
            val c = entry.details as ExtraCosts?
            return c!!.category.toString() == category
        }
        return true
    }

    companion object {
        private val log = LoggerFactory.getLogger(DefaultRepository::class.java)
    }
}
