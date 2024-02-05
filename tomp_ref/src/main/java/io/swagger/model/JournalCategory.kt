package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * ALL and FARE are not allowed to use in the journalEntry object. They are there for filtering purposes in the journal entry endpoint.
 */
enum class JournalCategory(private val value: String) {
    ALL("ALL"),
    DAMAGE("DAMAGE"),
    LOSS("LOSS"),
    STOLEN("STOLEN"),
    EXTRA_USAGE("EXTRA_USAGE"),
    REFUND("REFUND"),
    FINE("FINE"),
    OTHER_ASSET_USED("OTHER_ASSET_USED"),
    CREDIT("CREDIT"),
    VOUCHER("VOUCHER"),
    DEPOSIT("DEPOSIT"),
    OTHER("OTHER"),
    FARE("FARE");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): JournalCategory? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
