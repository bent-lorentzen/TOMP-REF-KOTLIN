package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Gets or Sets scenario
 */
enum class Scenario(private val value: String) {
    POSTPONED_COMMIT("POSTPONED_COMMIT"),
    DEPOSIT("DEPOSIT"),
    PAY_WHEN_FINISHED("PAY_WHEN_FINISHED"),
    REQUIRE_BOOKING_DATA("REQUIRE_BOOKING_DATA"),
    RETURN_AREA("RETURN_AREA"),
    UPFRONT_PAYMENT("UPFRONT_PAYMENT");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): Scenario? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
