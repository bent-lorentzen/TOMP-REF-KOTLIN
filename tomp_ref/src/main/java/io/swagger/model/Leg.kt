package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * A planned (segment of) a booked trip using one asset type
 */
@Schema(description = "A planned (segment of) a booked trip using one asset type")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
open class Leg {
    /**
     * The unique identifier (TO) of this leg
     * @return id
     */
    @JvmField
    @get:Schema(description = "The unique identifier (TO) of this leg")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get from
     * @return from
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("from")
    var from: Place? = null

    /**
     * Get to
     * @return to
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("to")
    var to: Place? = null

    /**
     * The departure time of this leg
     * @return departureTime
     */
    @JvmField
    @get:Schema(description = "The departure time of this leg")
    @JsonProperty("departureTime")
    var departureTime: OffsetDateTime? = null

    /**
     * The intended arrival time at the to place
     * @return arrivalTime
     */
    @JvmField
    @get:Schema(description = "The intended arrival time at the to place")
    @JsonProperty("arrivalTime")
    var arrivalTime: OffsetDateTime? = null

    @JsonProperty("travelerReferenceNumbers")
    private var travelerReferenceNumbers: @Valid MutableList<String>? = null

    /**
     * Get assetType
     * @return assetType
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("assetType")
    var assetType: AssetType? = null

    /**
     * The order of the leg in the booking. There can be multiple legs with the same sequence (different user or parallel usage (eg. parking lot and a bike)).
     * @return legSequenceNumber
     */
    @get:Schema(description = "The order of the leg in the booking. There can be multiple legs with the same sequence (different user or parallel usage (eg. parking lot and a bike)).")
    @JsonProperty("legSequenceNumber")
    var legSequenceNumber: Int? = null

    /**
     * Get asset
     * @return asset
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("asset")
    var asset: Asset? = null

    /**
     * Get pricing
     * @return pricing
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("pricing")
    var pricing: Fare? = null

    /**
     * Get suboperator
     * @return suboperator
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("suboperator")
    var suboperator: Suboperator? = null

    @JsonProperty("conditions")
    private var conditions: @Valid MutableList<OneOflegConditionsItems>? = null

    /**
     * Get state
     * @return state
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("state")
    var state: LegState? = null

    /**
     * A duration of some time (relative to a time) in milliseconds
     * minimum: 0
     * maximum: 2147483647
     * @return departureDelay
     */
    @get:Schema(example = "11112", description = "A duration of some time (relative to a time) in milliseconds")
    @JsonProperty("departureDelay")
    var departureDelay: Int? = null

    /**
     * A duration of some time (relative to a time) in milliseconds
     * minimum: 0
     * maximum: 2147483647
     * @return arrivalDelay
     */
    @get:Schema(example = "11112", description = "A duration of some time (relative to a time) in milliseconds")
    @JsonProperty("arrivalDelay")
    var arrivalDelay: Int? = null

    /**
     * The estimated distance travelled in the leg (in meters)
     * minimum: 0
     * @return distance
     */
    @JvmField
    @get:Schema(example = "7250", description = "The estimated distance travelled in the leg (in meters)")
    @JsonProperty("distance")
    var distance: Int? = null

    @JsonProperty("progressGeometry")
    private var progressGeometry = GeojsonLine()

    /**
     * Get ticket
     * @return ticket
     */
    @get:Schema(description = "")
    @JsonProperty("ticket")
    var ticket: Token? = null

    /**
     * Get assetAccessData
     * @return assetAccessData
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("assetAccessData")
    var assetAccessData: Token? = null

    @JsonProperty("allAssetAccessData")
    private var allAssetAccessData = TokenArray()
    fun id(id: String?): Leg {
        this.id = id
        return this
    }

    fun from(from: Place?): Leg {
        this.from = from
        return this
    }

    fun to(to: Place?): Leg {
        this.to = to
        return this
    }

    fun departureTime(departureTime: OffsetDateTime?): Leg {
        this.departureTime = departureTime
        return this
    }

    fun arrivalTime(arrivalTime: OffsetDateTime?): Leg {
        this.arrivalTime = arrivalTime
        return this
    }

    fun travelerReferenceNumbers(travelerReferenceNumbers: List<String>?): Leg {
        this.travelerReferenceNumbers = travelerReferenceNumbers
        return this
    }

    fun addTravelerReferenceNumbersItem(travelerReferenceNumbersItem: String): Leg {
        if (travelerReferenceNumbers == null) {
            travelerReferenceNumbers = ArrayList()
        }
        travelerReferenceNumbers!!.add(travelerReferenceNumbersItem)
        return this
    }

    /**
     * reference to the travelers field of the request. If missing, it is refering to the first (if any). it is an array to facilitate multiple users on one leg (e.g. using a car). If multiple access informations are needed, please create a leg per used asset.
     * @return travelerReferenceNumbers
     */
    @Schema(description = "reference to the travelers field of the request. If missing, it is refering to the first (if any). it is an array to facilitate multiple users on one leg (e.g. using a car). If multiple access informations are needed, please create a leg per used asset.")
    fun getTravelerReferenceNumbers(): List<String>? {
        return travelerReferenceNumbers
    }

    fun setTravelerReferenceNumbers(travelerReferenceNumbers: List<String>?) {
        this.travelerReferenceNumbers = travelerReferenceNumbers
    }

    fun assetType(assetType: AssetType?): Leg {
        this.assetType = assetType
        return this
    }

    fun legSequenceNumber(legSequenceNumber: Int?): Leg {
        this.legSequenceNumber = legSequenceNumber
        return this
    }

    fun asset(asset: Asset?): Leg {
        this.asset = asset
        return this
    }

    fun pricing(pricing: Fare?): Leg {
        this.pricing = pricing
        return this
    }

    fun suboperator(suboperator: Suboperator?): Leg {
        this.suboperator = suboperator
        return this
    }

    fun conditions(conditions: List<OneOflegConditionsItems>?): Leg {
        this.conditions = conditions
        return this
    }

    fun addConditionsItem(conditionsItem: OneOflegConditionsItems): Leg {
        if (conditions == null) {
            conditions = ArrayList()
        }
        conditions!!.add(conditionsItem)
        return this
    }

    /**
     * The conditions that apply to this leg, there may be more conditions in a parent booking and planning object (if this is returned as part of those)
     * @return conditions
     */
    @Schema(description = "The conditions that apply to this leg, there may be more conditions in a parent booking and planning object (if this is returned as part of those)")
    fun getConditions(): List<OneOflegConditionsItems>? {
        return conditions
    }

    fun setConditions(conditions: List<OneOflegConditionsItems>?) {
        this.conditions = conditions
    }

    fun state(state: LegState?): Leg {
        this.state = state
        return this
    }

    fun departureDelay(departureDelay: Int?): Leg {
        this.departureDelay = departureDelay
        return this
    }

    fun arrivalDelay(arrivalDelay: Int?): Leg {
        this.arrivalDelay = arrivalDelay
        return this
    }

    fun distance(distance: Int?): Leg {
        this.distance = distance
        return this
    }

    fun progressGeometry(progressGeometry: GeojsonLine): Leg {
        this.progressGeometry = progressGeometry
        return this
    }

    /**
     * An array  of WGS84 coordinate pairs
     * @return progressGeometry
     */
    @Schema(example = "[[6.169639,52.253279],[6.05623,52.63473]]", description = "An array  of WGS84 coordinate pairs")
    fun getProgressGeometry(): @Valid GeojsonLine? {
        return progressGeometry
    }

    fun setProgressGeometry(progressGeometry: GeojsonLine) {
        this.progressGeometry = progressGeometry
    }

    fun ticket(ticket: Token?): Leg {
        this.ticket = ticket
        return this
    }

    fun assetAccessData(assetAccessData: Token?): Leg {
        this.assetAccessData = assetAccessData
        return this
    }

    fun allAssetAccessData(allAssetAccessData: TokenArray): Leg {
        this.allAssetAccessData = allAssetAccessData
        return this
    }

    /**
     * Get allAssetAccessData
     * @return allAssetAccessData
     */
    @Schema(description = "")
    fun getAllAssetAccessData(): @Valid TokenArray? {
        return allAssetAccessData
    }

    fun setAllAssetAccessData(allAssetAccessData: TokenArray) {
        this.allAssetAccessData = allAssetAccessData
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val leg = o as Leg
        return id == leg.id && from == leg.from && to == leg.to && departureTime == leg.departureTime && arrivalTime == leg.arrivalTime && travelerReferenceNumbers == leg.travelerReferenceNumbers && assetType == leg.assetType && legSequenceNumber == leg.legSequenceNumber && asset == leg.asset && pricing == leg.pricing && suboperator == leg.suboperator && conditions == leg.conditions && state == leg.state && departureDelay == leg.departureDelay && arrivalDelay == leg.arrivalDelay && distance == leg.distance && progressGeometry == leg.progressGeometry && ticket == leg.ticket && assetAccessData == leg.assetAccessData && allAssetAccessData == leg.allAssetAccessData
    }

    override fun hashCode(): Int {
        return Objects.hash(
            id,
            from,
            to,
            departureTime,
            arrivalTime,
            travelerReferenceNumbers,
            assetType,
            legSequenceNumber,
            asset,
            pricing,
            suboperator,
            conditions,
            state,
            departureDelay,
            arrivalDelay,
            distance,
            progressGeometry,
            ticket,
            assetAccessData,
            allAssetAccessData
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Leg {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    from: ").append(toIndentedString(from)).append("\n")
        sb.append("    to: ").append(toIndentedString(to)).append("\n")
        sb.append("    departureTime: ").append(toIndentedString(departureTime)).append("\n")
        sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n")
        sb.append("    travelerReferenceNumbers: ").append(toIndentedString(travelerReferenceNumbers)).append("\n")
        sb.append("    assetType: ").append(toIndentedString(assetType)).append("\n")
        sb.append("    legSequenceNumber: ").append(toIndentedString(legSequenceNumber)).append("\n")
        sb.append("    asset: ").append(toIndentedString(asset)).append("\n")
        sb.append("    pricing: ").append(toIndentedString(pricing)).append("\n")
        sb.append("    suboperator: ").append(toIndentedString(suboperator)).append("\n")
        sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n")
        sb.append("    state: ").append(toIndentedString(state)).append("\n")
        sb.append("    departureDelay: ").append(toIndentedString(departureDelay)).append("\n")
        sb.append("    arrivalDelay: ").append(toIndentedString(arrivalDelay)).append("\n")
        sb.append("    distance: ").append(toIndentedString(distance)).append("\n")
        sb.append("    progressGeometry: ").append(toIndentedString(progressGeometry)).append("\n")
        sb.append("    ticket: ").append(toIndentedString(ticket)).append("\n")
        sb.append("    assetAccessData: ").append(toIndentedString(assetAccessData)).append("\n")
        sb.append("    allAssetAccessData: ").append(toIndentedString(allAssetAccessData)).append("\n")
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
