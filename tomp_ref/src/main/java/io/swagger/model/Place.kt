package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * a origin or destination of a leg, 3D. lon/lat in WGS84.
 */
@Schema(description = "a origin or destination of a leg, 3D. lon/lat in WGS84.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Place {
    /**
     * Human readable name of the place, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(description = "Human readable name of the place, could match Content-Language")
    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("stopReference")
    private var stopReference: @Valid MutableList<StopReference>? = null

    /**
     * reference to /operator/stations
     * @return stationId
     */
    @JvmField
    @get:Schema(description = "reference to /operator/stations")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * Get coordinates
     * @return coordinates
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("coordinates")
    var coordinates: Coordinates? = null

    /**
     * Get physicalAddress
     * @return physicalAddress
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("physicalAddress")
    var physicalAddress: Address? = null

    @JsonProperty("extraInfo")
    private var extraInfo: @Valid MutableMap<String, Any>? = null
    fun name(name: String?): Place {
        this.name = name
        return this
    }

    fun stopReference(stopReference: List<StopReference>?): Place {
        this.stopReference = stopReference
        return this
    }

    fun addStopReferenceItem(stopReferenceItem: StopReference): Place {
        if (stopReference == null) {
            stopReference = ArrayList()
        }
        stopReference!!.add(stopReferenceItem)
        return this
    }

    /**
     * Get stopReference
     * @return stopReference
     */
    @Schema(description = "")
    fun getStopReference(): @Valid MutableList<StopReference>? {
        return stopReference
    }

    fun setStopReference(stopReference: List<StopReference>?) {
        this.stopReference = stopReference
    }

    fun stationId(stationId: String?): Place {
        this.stationId = stationId
        return this
    }

    fun coordinates(coordinates: Coordinates?): Place {
        this.coordinates = coordinates
        return this
    }

    fun physicalAddress(physicalAddress: Address?): Place {
        this.physicalAddress = physicalAddress
        return this
    }

    fun extraInfo(extraInfo: Map<String, Any>?): Place {
        this.extraInfo = extraInfo
        return this
    }

    fun putExtraInfoItem(key: String, extraInfoItem: Any): Place {
        if (extraInfo == null) {
            extraInfo = HashMap()
        }
        extraInfo!![key] = extraInfoItem
        return this
    }

    /**
     * Get extraInfo
     * @return extraInfo
     */
    @Schema(description = "")
    fun getExtraInfo(): Map<String, Any>? {
        return extraInfo
    }

    fun setExtraInfo(extraInfo: Map<String, Any>?) {
        this.extraInfo = extraInfo
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val place = o as Place
        return name == place.name && stopReference == place.stopReference && stationId == place.stationId && coordinates == place.coordinates && physicalAddress == place.physicalAddress && extraInfo == place.extraInfo
    }

    override fun hashCode(): Int {
        return Objects.hash(name, stopReference, stationId, coordinates, physicalAddress, extraInfo)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Place {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    stopReference: ").append(toIndentedString(stopReference)).append("\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n")
        sb.append("    physicalAddress: ").append(toIndentedString(physicalAddress)).append("\n")
        sb.append("    extraInfo: ").append(toIndentedString(extraInfo)).append("\n")
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
