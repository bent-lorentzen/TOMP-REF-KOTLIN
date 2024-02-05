package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * a lon, lat (WGS84, EPSG:4326)
 */
@Schema(description = "a lon, lat (WGS84, EPSG:4326)")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class Coordinates {
    /**
     * Get lng
     * minimum: 0
     * @return lng
     */
    @JvmField
    @get:Schema(example = "6.169639", required = true, description = "")
    @JsonProperty("lng")
    var lng: Float? = null

    /**
     * Get lat
     * minimum: 0
     * @return lat
     */
    @JvmField
    @get:Schema(example = "52.253279", required = true, description = "")
    @JsonProperty("lat")
    var lat: Float? = null

    /**
     * altitude, in meters above sea level
     * minimum: 0
     * @return alt
     */
    @get:Schema(description = "altitude, in meters above sea level")
    @JsonProperty("alt")
    var alt: Float? = null
    fun lng(lng: Float?): Coordinates {
        this.lng = lng
        return this
    }

    fun lat(lat: Float?): Coordinates {
        this.lat = lat
        return this
    }

    fun alt(alt: Float?): Coordinates {
        this.alt = alt
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val coordinates = o as Coordinates
        return lng == coordinates.lng && lat == coordinates.lat && alt == coordinates.alt
    }

    override fun hashCode(): Int {
        return Objects.hash(lng, lat, alt)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Coordinates {\n")
        sb.append("    lng: ").append(toIndentedString(lng)).append("\n")
        sb.append("    lat: ").append(toIndentedString(lat)).append("\n")
        sb.append("    alt: ").append(toIndentedString(alt)).append("\n")
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
