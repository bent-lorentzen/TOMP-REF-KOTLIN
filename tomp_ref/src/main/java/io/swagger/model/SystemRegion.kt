package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * SystemRegion
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class SystemRegion {
    /**
     * Unique identifier for this region
     * @return regionId
     */
    @JvmField
    @get:Schema(example = "BikeRegion", required = true, description = "Unique identifier for this region")
    @JsonProperty("regionId")
    var regionId: String? = null

    /**
     * Public name for this region, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(
        example = "BikeTown",
        required = true,
        description = "Public name for this region, could match Content-Language"
    )
    @JsonProperty("name")
    var name: String? = null

    /**
     * the type of area. Default this is 'OPERATING', but other area's can be published here as well (since 1.3.0). Before 1.3.0, it was only allowed to communicate OPERATING area's.
     */
    enum class TypeEnum(private val value: String) {
        OPERATING("OPERATING"),
        PROHIBITED_TO_ACCESS("PROHIBITED_TO_ACCESS"),
        PROHIBITED_TO_PAUSE("PROHIBITED_TO_PAUSE"),
        PARKING("PARKING"),
        DISCOUNT("DISCOUNT");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): TypeEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * the type of area. Default this is 'OPERATING', but other area's can be published here as well (since 1.3.0). Before 1.3.0, it was only allowed to communicate OPERATING area's.
     * @return type
     */
    @get:Schema(description = "the type of area. Default this is 'OPERATING', but other area's can be published here as well (since 1.3.0). Before 1.3.0, it was only allowed to communicate OPERATING area's.")
    @JsonProperty("type")
    var type = TypeEnum.OPERATING

    @JsonProperty("serviceArea")
    private var serviceArea = GeojsonPolygon()
    fun regionId(regionId: String?): SystemRegion {
        this.regionId = regionId
        return this
    }

    fun name(name: String?): SystemRegion {
        this.name = name
        return this
    }

    fun type(type: TypeEnum): SystemRegion {
        this.type = type
        return this
    }

    fun serviceArea(serviceArea: GeojsonPolygon): SystemRegion {
        this.serviceArea = serviceArea
        return this
    }

    /**
     * geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point.
     * @return serviceArea
     */
    @Schema(
        example = "[[[1,1],[0,1],[0,0],[1,0],[1,1]]]",
        description = "geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point."
    )
    fun getServiceArea(): @Valid GeojsonPolygon? {
        return serviceArea
    }

    fun setServiceArea(serviceArea: GeojsonPolygon) {
        this.serviceArea = serviceArea
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemRegion = o as SystemRegion
        return regionId == systemRegion.regionId && name == systemRegion.name && type == systemRegion.type && serviceArea == systemRegion.serviceArea
    }

    override fun hashCode(): Int {
        return Objects.hash(regionId, name, type, serviceArea)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemRegion {\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    serviceArea: ").append(toIndentedString(serviceArea)).append("\n")
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
