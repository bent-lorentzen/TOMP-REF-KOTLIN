package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated

/**
 * The validity token (such as booking ID, travel ticket etc.) that MaaS clients will display to show their right to travel, or use to access an asset
 */
@Schema(description = "The validity token (such as booking ID, travel ticket etc.) that MaaS clients will display to show their right to travel, or use to access an asset")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
class Token {
    /**
     * Get validFrom
     * @return validFrom
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("validFrom")
    var validFrom: OffsetDateTime? = null

    /**
     * Get validUntil
     * @return validUntil
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("validUntil")
    var validUntil: OffsetDateTime? = null

    /**
     * The type of data held in this token, will later be an enum
     */
    enum class TokenTypeEnum(private val value: String) {
        TOKENDEFAULT("tokenDefault"),
        TOKENDEEPLINK("tokenDeeplink"),
        TOKENEKEY("tokenEKey"),
        TOKENQR("tokenQR");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): TokenTypeEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * The type of data held in this token, will later be an enum
     * @return tokenType
     */
    @get:Schema(required = true, description = "The type of data held in this token, will later be an enum")
    @JsonProperty("tokenType")
    var tokenType: TokenTypeEnum? = null

    /**
     * Get tokenData
     * @return tokenData
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("tokenData")
    var tokenData: OneOftokenTokenData? = null
    fun validFrom(validFrom: OffsetDateTime?): Token {
        this.validFrom = validFrom
        return this
    }

    fun validUntil(validUntil: OffsetDateTime?): Token {
        this.validUntil = validUntil
        return this
    }

    fun tokenType(tokenType: TokenTypeEnum?): Token {
        this.tokenType = tokenType
        return this
    }

    fun tokenData(tokenData: OneOftokenTokenData?): Token {
        this.tokenData = tokenData
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val token = o as Token
        return validFrom == token.validFrom && validUntil == token.validUntil && tokenType == token.tokenType && tokenData == token.tokenData
    }

    override fun hashCode(): Int {
        return Objects.hash(validFrom, validUntil, tokenType, tokenData)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Token {\n")
        sb.append("    validFrom: ").append(toIndentedString(validFrom)).append("\n")
        sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n")
        sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n")
        sb.append("    tokenData: ").append(toIndentedString(tokenData)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}
