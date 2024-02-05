package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * To identify the operator
 */
@Schema(description = "To identify the operator")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class ChamberOfCommerceInfo {
    /**
     * Get number
     * @return number
     */
    @get:Schema(description = "")
    @JsonProperty("number")
    var number: String? = null

    /**
     * Get place
     * @return place
     */
    @get:Schema(description = "")
    @JsonProperty("place")
    var place: String? = null
    fun number(number: String?): ChamberOfCommerceInfo {
        this.number = number
        return this
    }

    fun place(place: String?): ChamberOfCommerceInfo {
        this.place = place
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val chamberOfCommerceInfo = o as ChamberOfCommerceInfo
        return number == chamberOfCommerceInfo.number && place == chamberOfCommerceInfo.place
    }

    override fun hashCode(): Int {
        return Objects.hash(number, place)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ChamberOfCommerceInfo {\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    place: ").append(toIndentedString(place)).append("\n")
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
