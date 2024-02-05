package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Gets or Sets assetAccessMethods
 */
enum class AssetAccessMethods(private val value: String) {
    DEEPLINK("DEEPLINK"),
    QR("QR"),
    AZTEC("AZTEC"),
    TOMP_API("TOMP-API"),
    AXA_EKEY_OTP("AXA-EKEY-OTP"),
    PHYSICAL_KEY("PHYSICAL-KEY"),
    BARCODE("BARCODE"),
    PDF("PDF"),
    HTML("HTML"),
    OVC("OVC"),
    EMV("EMV"),
    NONE("NONE");

    @JsonValue
    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): AssetAccessMethods? {
            for (b in entries) {
                if (b.value.toString() == text) {
                    return b
                }
            }
            return null
        }
    }
}
