package io.swagger.model

import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * TokenArray
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-28T07:34:31.139Z[GMT]")
class TokenArray : ArrayList<Token?>() {
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        return if (o == null || javaClass != o.javaClass) {
            false
        } else true
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenArray {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
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
