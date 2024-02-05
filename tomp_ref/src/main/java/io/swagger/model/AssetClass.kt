package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * These classes are taken from the NeTeX standard, but ALL and UNKNOWN are removed. On the other hand OTHER and PARKING are added.
 */
enum class AssetClass(private val value: String) {
    AIR("AIR"),
    BUS("BUS"),
    TROLLEYBUS("TROLLEYBUS"),
    TRAM("TRAM"),
    COACH("COACH"),
    RAIL("RAIL"),
    INTERCITYRAIL("INTERCITYRAIL"),
    URBANRAIL("URBANRAIL"),
    METRO("METRO"),
    WATER("WATER"),
    CABLEWAY("CABLEWAY"),
    FUNICULAR("FUNICULAR"),
    TAXI("TAXI"),
    SELFDRIVE("SELFDRIVE"),
    FOOT("FOOT"),
    BICYCLE("BICYCLE"),
    MOTORCYCLE("MOTORCYCLE"),
    CAR("CAR"),
    SHUTTLE("SHUTTLE"),
    OTHER("OTHER"),
    PARKING("PARKING"),
    MOPED("MOPED"),
    STEP("STEP");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): AssetClass? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
