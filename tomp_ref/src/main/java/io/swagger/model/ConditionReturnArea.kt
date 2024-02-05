package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * a return area. In the condition list there can be multiple return area&#x27;s.
 */
@Schema(description = "a return area. In the condition list there can be multiple return area's.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class ConditionReturnArea : Condition(), OneOfassetTypeConditionsItems, OneOflegConditionsItems {
    /**
     * station to which the asset should be returned
     * @return stationId
     */
    @JvmField
    @get:Schema(description = "station to which the asset should be returned")
    @JsonProperty("stationId")
    var stationId: String? = null

    @JsonProperty("returnArea")
    private var returnArea = GeojsonPolygon()

    /**
     * Get coordinates
     * @return coordinates
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("coordinates")
    var coordinates: Coordinates? = null

    @JsonProperty("returnHours")
    private var returnHours: @Valid MutableList<SystemHours>? = null
    fun stationId(stationId: String?): ConditionReturnArea {
        this.stationId = stationId
        return this
    }

    fun returnArea(returnArea: GeojsonPolygon): ConditionReturnArea {
        this.returnArea = returnArea
        return this
    }

    /**
     * geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point.
     * @return returnArea
     */
    @Schema(
        example = "[[[1,1],[0,1],[0,0],[1,0],[1,1]]]",
        description = "geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point."
    )
    fun getReturnArea(): @Valid GeojsonPolygon? {
        return returnArea
    }

    fun setReturnArea(returnArea: GeojsonPolygon) {
        this.returnArea = returnArea
    }

    fun coordinates(coordinates: Coordinates?): ConditionReturnArea {
        this.coordinates = coordinates
        return this
    }

    fun returnHours(returnHours: List<SystemHours>?): ConditionReturnArea {
        this.returnHours = returnHours
        return this
    }

    fun addReturnHoursItem(returnHoursItem: SystemHours): ConditionReturnArea {
        if (returnHours == null) {
            returnHours = ArrayList()
        }
        returnHours!!.add(returnHoursItem)
        return this
    }

    /**
     * the return hours of the facility (if different from operating-hours)
     * @return returnHours
     */
    @Schema(description = "the return hours of the facility (if different from operating-hours)")
    fun getReturnHours(): @Valid MutableList<SystemHours>? {
        return returnHours
    }

    fun setReturnHours(returnHours: List<SystemHours>?) {
        this.returnHours = returnHours
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val conditionReturnArea = o as ConditionReturnArea
        return stationId == conditionReturnArea.stationId && returnArea == conditionReturnArea.returnArea && coordinates == conditionReturnArea.coordinates && returnHours == conditionReturnArea.returnHours &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(stationId, returnArea, coordinates, returnHours, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ConditionReturnArea {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    returnArea: ").append(toIndentedString(returnArea)).append("\n")
        sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n")
        sb.append("    returnHours: ").append(toIndentedString(returnHours)).append("\n")
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
