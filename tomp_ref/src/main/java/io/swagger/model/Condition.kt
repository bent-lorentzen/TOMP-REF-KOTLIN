package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * Condition
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
open class Condition {
    /**
     * The specific subclass of condition, should match the schema name exactly
     * @return conditionType
     */
    @JvmField
    @get:Schema(
        required = true,
        description = "The specific subclass of condition, should match the schema name exactly"
    )
    @JsonProperty("conditionType")
    var conditionType: String? = null

    /**
     * An identifier for this condition that can be used to refer to this condition
     * @return id
     */
    @JvmField
    @get:Schema(
        example = "deposit50eu",
        description = "An identifier for this condition that can be used to refer to this condition"
    )
    @JsonProperty("id")
    var id: String? = null
    fun conditionType(conditionType: String?): Condition {
        this.conditionType = conditionType
        return this
    }

    fun id(id: String?): Condition {
        this.id = id
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val condition = o as Condition
        return conditionType == condition.conditionType && id == condition.id
    }

    override fun hashCode(): Int {
        return Objects.hash(conditionType, id)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Condition {\n")
        sb.append("    conditionType: ").append(toIndentedString(conditionType)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
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
