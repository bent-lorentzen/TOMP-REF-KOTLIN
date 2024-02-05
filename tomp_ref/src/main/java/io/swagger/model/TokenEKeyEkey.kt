package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * TokenEKeyEkey
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class TokenEKeyEkey {
    /**
     * certificate
     * @return key
     */
    @get:Schema(description = "certificate")
    @JsonProperty("key")
    var key: String? = null

    /**
     * one time pass key
     * @return passkey
     */
    @get:Schema(description = "one time pass key")
    @JsonProperty("passkey")
    var passkey: String? = null
    fun key(key: String?): TokenEKeyEkey {
        this.key = key
        return this
    }

    fun passkey(passkey: String?): TokenEKeyEkey {
        this.passkey = passkey
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenEKeyEkey = o as TokenEKeyEkey
        return key == tokenEKeyEkey.key && passkey == tokenEKeyEkey.passkey
    }

    override fun hashCode(): Int {
        return Objects.hash(key, passkey)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenEKeyEkey {\n")
        sb.append("    key: ").append(toIndentedString(key)).append("\n")
        sb.append("    passkey: ").append(toIndentedString(passkey)).append("\n")
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
