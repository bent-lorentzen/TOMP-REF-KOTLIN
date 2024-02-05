package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * the total fare is the sum of all parts, except for the &#x27;MAX&#x27; farePart. This one descripes the maximum price for the complete leg.
 */
@Schema(description = "the total fare is the sum of all parts, except for the 'MAX' farePart. This one descripes the maximum price for the complete leg.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-08-19T12:10:17.865Z[GMT]")
class Fare {
    /**
     * is this fare an estimation?
     * @return estimated
     */
    @get:Schema(required = true, description = "is this fare an estimation?")
    @JsonProperty("estimated")
    var isEstimated: Boolean? = null

    /**
     * user friendly description of the fare (e.g. 'full fare'), should match Content-Language
     * @return description
     */
    @get:Schema(description = "user friendly description of the fare (e.g. 'full fare'), should match Content-Language")
    @JsonProperty("description")
    var description: String? = null

    /**
     * in the future we'll set up an enumeration of possible \"fare classes\". For now it's free format.
     * @return propertyClass
     */
    @get:Schema(description = "in the future we'll set up an enumeration of possible \"fare classes\". For now it's free format.")
    @JsonProperty("class")
    var propertyClass: String? = null

    @JsonProperty("parts")
    private var parts: @Valid MutableList<FarePart>? = ArrayList()
    fun estimated(estimated: Boolean?): Fare {
        isEstimated = estimated
        return this
    }

    fun description(description: String?): Fare {
        this.description = description
        return this
    }

    fun propertyClass(propertyClass: String?): Fare {
        this.propertyClass = propertyClass
        return this
    }

    fun parts(parts: List<FarePart>?): Fare {
        this.parts = parts
        return this
    }

    fun addPartsItem(partsItem: FarePart): Fare {
        parts!!.add(partsItem)
        return this
    }

    /**
     * Get parts
     * @return parts
     */
    @Schema(required = true, description = "")
    fun getParts(): @NotNull @Valid MutableList<FarePart>? {
        return parts
    }

    fun setParts(parts: List<FarePart>?) {
        this.parts = parts
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val fare = o as Fare
        return isEstimated == fare.isEstimated && description == fare.description && propertyClass == fare.propertyClass && parts == fare.parts
    }

    override fun hashCode(): Int {
        return Objects.hash(isEstimated, description, propertyClass, parts)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Fare {\n")
        sb.append("    estimated: ").append(toIndentedString(isEstimated)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    propertyClass: ").append(toIndentedString(propertyClass)).append("\n")
        sb.append("    parts: ").append(toIndentedString(parts)).append("\n")
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
