package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * Arbitrary data the TO may pass along the ticket to the client
 */
@Schema(description = "Arbitrary data the TO may pass along the ticket to the client")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class TokenDefault : TokenData(), OneOftokenTokenData {
    /**
     * download url for html/pdf
     * @return url
     */
    @get:Schema(description = "download url for html/pdf")
    @JsonProperty("url")
    var url: String? = null
    fun url(url: String?): TokenDefault {
        this.url = url
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenDefault = o as TokenDefault
        return url == tokenDefault.url &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(url, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenDefault {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
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
