package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * TokenData
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
open class TokenData : HashMap<String?, Any?>() {
    /**
     * Get tokenType
     * @return tokenType
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("tokenType")
    var tokenType: String? = null
    fun tokenType(tokenType: String?): TokenData {
        this.tokenType = tokenType
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenData = o as TokenData
        return tokenType == tokenData.tokenType &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(tokenType, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenData {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n")
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
