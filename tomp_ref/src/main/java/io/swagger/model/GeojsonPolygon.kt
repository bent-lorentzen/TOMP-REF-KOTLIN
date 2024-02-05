package io.swagger.model

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point.
 */
@Schema(description = "geojson representation of a polygon. First and last point must be equal. See also https://geojson.org/geojson-spec.html#polygon and example https://geojson.org/geojson-spec.html#id4. The order should be lon, lat [[[lon1, lat1], [lon2,lat2], [lon3,lat3], [lon1,lat1]]], the first point should match the last point.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class GeojsonPolygon : ArrayList<GeojsonLine?>() {
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
        sb.append("class GeojsonPolygon {\n")
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
