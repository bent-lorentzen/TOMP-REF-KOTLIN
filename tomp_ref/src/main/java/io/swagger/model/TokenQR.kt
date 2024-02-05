package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * QR information
 */
@Schema(description = "QR information")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class TokenQR : TokenData(), OneOftokenTokenData {
    /**
     * base 64 QR code
     * @return base64
     */
    @get:Schema(required = true, description = "base 64 QR code")
    @JsonProperty("base64")
    var base64: String? = null

    /**
     * Get version
     * @return version
     */
    @get:Schema(description = "")
    @JsonProperty("version")
    var version: String? = null
    fun base64(base64: String?): TokenQR {
        this.base64 = base64
        return this
    }

    fun version(version: String?): TokenQR {
        this.version = version
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenQR = o as TokenQR
        return base64 == tokenQR.base64 && version == tokenQR.version &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(base64, version, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenQR {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    base64: ").append(toIndentedString(base64)).append("\n")
        sb.append("    version: ").append(toIndentedString(version)).append("\n")
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
