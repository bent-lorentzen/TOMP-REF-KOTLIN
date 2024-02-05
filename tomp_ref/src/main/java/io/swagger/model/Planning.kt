package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * A travel planning with bookable options that fulfil the constraints of the planning
 */
@Schema(description = "A travel planning with bookable options that fulfil the constraints of the planning")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Planning {
    /**
     * The time until which the presented options are (likely) available
     * @return validUntil
     */
    @get:Schema(
        required = true,
        description = "The time until which the presented options are (likely) available"
    )
    @JsonProperty("validUntil")
    var validUntil: OffsetDateTime? = null

    @JsonProperty("options")
    private var options: @Valid MutableList<Booking>? = ArrayList()
    fun validUntil(validUntil: OffsetDateTime?): Planning {
        this.validUntil = validUntil
        return this
    }

    fun options(options: List<Booking>?): Planning {
        this.options = options
        return this
    }

    fun addOptionsItem(optionsItem: Booking): Planning {
        options!!.add(optionsItem)
        return this
    }

    /**
     * Get options
     * @return options
     */
    @Schema(required = true, description = "")
    fun getOptions(): @NotNull @Valid MutableList<Booking>? {
        return options
    }

    fun setOptions(options: List<Booking>?) {
        this.options = options
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val planning = o as Planning
        return validUntil == planning.validUntil && options == planning.options
    }

    override fun hashCode(): Int {
        return Objects.hash(validUntil, options)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Planning {\n")
        sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n")
        sb.append("    options: ").append(toIndentedString(options)).append("\n")
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
