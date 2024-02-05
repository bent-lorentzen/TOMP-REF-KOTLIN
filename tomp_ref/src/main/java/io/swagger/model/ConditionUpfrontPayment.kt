package io.swagger.model

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * in case the TO demands a upfront payment before usage. The payment should be made in the booking phase.
 */
@Schema(description = "in case the TO demands a upfront payment before usage. The payment should be made in the booking phase.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
class ConditionUpfrontPayment : Condition(), OneOfassetTypeConditionsItems, OneOflegConditionsItems {
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
        sb.append("class ConditionUpfrontPayment {\n")
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
