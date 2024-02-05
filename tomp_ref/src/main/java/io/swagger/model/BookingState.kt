package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * The life-cycle state of the booking (from NEW to FINISHED)
 */
enum class BookingState(private val value: String) {
    NEW("NEW"),
    PENDING("PENDING"),
    REJECTED("REJECTED"),
    RELEASED("RELEASED"),
    EXPIRED("EXPIRED"),
    CONDITIONAL_CONFIRMED("CONDITIONAL_CONFIRMED"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    STARTED("STARTED"),
    FINISHED("FINISHED");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): BookingState? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
