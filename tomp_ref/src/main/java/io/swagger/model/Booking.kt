package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * The booking information describing the state and details of an agreed upon trip
 */
@Schema(description = "The booking information describing the state and details of an agreed upon trip")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class Booking : BookingRequest() {
    /**
     * Get state
     * @return state
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("state")
    var state: BookingState? = null

    @JsonProperty("legs")
    private var legs: @Valid MutableList<Leg>? = null

    /**
     * Get pricing
     * @return pricing
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("pricing")
    var pricing: Fare? = null

    /**
     * The initial departure time (over all legs)
     * @return departureTime
     */
    @get:Schema(description = "The initial departure time (over all legs)")
    @JsonProperty("departureTime")
    var departureTime: OffsetDateTime? = null

    /**
     * The intended arrival time at the destination of the booking (over all legs)
     * @return arrivalTime
     */
    @get:Schema(description = "The intended arrival time at the destination of the booking (over all legs)")
    @JsonProperty("arrivalTime")
    var arrivalTime: OffsetDateTime? = null

    /**
     * Get mainAssetType
     * @return mainAssetType
     */
    @get:Schema(description = "")
    @JsonProperty("mainAssetType")
    var mainAssetType: AssetType? = null

    @JsonProperty("extraData")
    private var extraData: Map<*, *>? = null
    fun state(state: BookingState?): Booking {
        this.state = state
        return this
    }

    fun legs(legs: List<Leg>?): Booking {
        this.legs = legs
        return this
    }

    fun addLegsItem(legsItem: Leg): Booking {
        if (legs == null) {
            legs = ArrayList()
        }
        legs!!.add(legsItem)
        return this
    }

    /**
     * The legs of this booking, generally just one for simple legs, in order of how they will be travelled. If this part is not present, it means that there is only one leg. This leg can be constructed * leg[0].id = booking.id * leg[0].departureTime = booking.departureTime * leg[0].arrivalTime = booking.arrivalTime * leg[0].assetType = booking.mainAssetType * leg[0].pricing = booking.pricing This approach is not allowed in the trip execution part
     * @return legs
     */
    @Schema(description = "The legs of this booking, generally just one for simple legs, in order of how they will be travelled. If this part is not present, it means that there is only one leg. This leg can be constructed * leg[0].id = booking.id * leg[0].departureTime = booking.departureTime * leg[0].arrivalTime = booking.arrivalTime * leg[0].assetType = booking.mainAssetType * leg[0].pricing = booking.pricing This approach is not allowed in the trip execution part")
    fun getLegs(): @Valid MutableList<Leg>? {
        return legs
    }

    fun setLegs(legs: List<Leg>?) {
        this.legs = legs
    }

    fun pricing(pricing: Fare?): Booking {
        this.pricing = pricing
        return this
    }

    fun departureTime(departureTime: OffsetDateTime?): Booking {
        this.departureTime = departureTime
        return this
    }

    fun arrivalTime(arrivalTime: OffsetDateTime?): Booking {
        this.arrivalTime = arrivalTime
        return this
    }

    fun mainAssetType(mainAssetType: AssetType?): Booking {
        this.mainAssetType = mainAssetType
        return this
    }

    fun extraData(extraData: Map<*, *>?): Booking {
        this.extraData = extraData
        return this
    }

    /**
     * Get extraData
     * @return extraData
     */
    @Schema(description = "")
    fun getExtraData(): @Valid MutableMap<*, *>? {
        return extraData
    }

    fun setExtraData(extraData: Map<*, *>?) {
        this.extraData = extraData
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val booking = o as Booking
        return state == booking.state && legs == booking.legs && pricing == booking.pricing && departureTime == booking.departureTime && arrivalTime == booking.arrivalTime && mainAssetType == booking.mainAssetType && extraData == booking.extraData &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(state, legs, pricing, departureTime, arrivalTime, mainAssetType, extraData, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Booking {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    state: ").append(toIndentedString(state)).append("\n")
        sb.append("    legs: ").append(toIndentedString(legs)).append("\n")
        sb.append("    pricing: ").append(toIndentedString(pricing)).append("\n")
        sb.append("    departureTime: ").append(toIndentedString(departureTime)).append("\n")
        sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n")
        sb.append("    mainAssetType: ").append(toIndentedString(mainAssetType)).append("\n")
        sb.append("    extraData: ").append(toIndentedString(extraData)).append("\n")
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
