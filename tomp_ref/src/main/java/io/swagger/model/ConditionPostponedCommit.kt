package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated

/**
 * ConditionPostponedCommit
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
class ConditionPostponedCommit : Condition(), OneOfassetTypeConditionsItems, OneOflegConditionsItems {
    /**
     * Get ultimateResponseTime
     * @return ultimateResponseTime
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("ultimateResponseTime")
    var ultimateResponseTime: OffsetDateTime? = null
    fun ultimateResponseTime(ultimateResponseTime: OffsetDateTime?): ConditionPostponedCommit {
        this.ultimateResponseTime = ultimateResponseTime
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val conditionPostponedCommit = o as ConditionPostponedCommit
        return ultimateResponseTime == conditionPostponedCommit.ultimateResponseTime &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(ultimateResponseTime, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ConditionPostponedCommit {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    ultimateResponseTime: ").append(toIndentedString(ultimateResponseTime)).append("\n")
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
