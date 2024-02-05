package org.tomp.api.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Gets or Sets maasEnvironmentType
 */
enum class MaasEnvironmentType(private val value: String) {
    TO("TO"),
    MP("MP"),
    LOOKUP_SERVICE("LOOKUP_SERVICE"),
    TP("TP"),
    VAULT("VAULT"),
    DATA_DRAIN("DATA_DRAIN");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): MaasEnvironmentType? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
