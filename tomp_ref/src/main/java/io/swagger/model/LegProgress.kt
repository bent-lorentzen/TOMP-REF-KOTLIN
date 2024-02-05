package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * provides current asset location &amp; duration and distance of the current leg
 */
@Schema(description = "provides current asset location & duration and distance of the current leg")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class LegProgress {
    /**
     * Get coordinates
     * @return coordinates
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("coordinates")
    var coordinates: Coordinates? = null

    /**
     * A duration of some time (relative to a time) in milliseconds
     * minimum: 0
     * maximum: 2147483647
     * @return duration
     */
    @get:Schema(example = "11112", description = "A duration of some time (relative to a time) in milliseconds")
    @JsonProperty("duration")
    var duration: Int? = null

    /**
     * The estimated distance travelled in the leg (in meters)
     * minimum: 0
     * @return distance
     */
    @get:Schema(example = "7250", description = "The estimated distance travelled in the leg (in meters)")
    @JsonProperty("distance")
    var distance: Int? = null
    fun coordinates(coordinates: Coordinates?): LegProgress {
        this.coordinates = coordinates
        return this
    }

    fun duration(duration: Int?): LegProgress {
        this.duration = duration
        return this
    }

    fun distance(distance: Int?): LegProgress {
        this.distance = distance
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val legProgress = o as LegProgress
        return coordinates == legProgress.coordinates && duration == legProgress.duration && distance == legProgress.distance
    }

    override fun hashCode(): Int {
        return Objects.hash(coordinates, duration, distance)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class LegProgress {\n")
        sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n")
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n")
        sb.append("    distance: ").append(toIndentedString(distance)).append("\n")
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
