package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Gets or Sets day
 */
enum class Day(private val value: String) {
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THU("THU"),
    FRI("FRI"),
    SAT("SAT"),
    SUN("SUN");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromValue(text: String): Day? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
