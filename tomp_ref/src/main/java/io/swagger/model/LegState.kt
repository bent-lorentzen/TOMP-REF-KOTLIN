package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * status of a leg
 */
enum class LegState(private val value: String) {
    NOT_STARTED("NOT_STARTED"),
    PREPARING("PREPARING"),
    IN_USE("IN_USE"),
    PAUSED("PAUSED"),
    FINISHING("FINISHING"),
    FINISHED("FINISHED"),
    ISSUE_REPORTED("ISSUE_REPORTED"),
    CANCELLED("CANCELLED");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): LegState? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
