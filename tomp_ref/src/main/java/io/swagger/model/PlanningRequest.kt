package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * A travel planning for which bookable options are requested
 */
@Schema(description = "A travel planning for which bookable options are requested")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class PlanningRequest {
    /**
     * Get from
     * @return from
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("from")
    var from: Place? = null

    /**
     * Maximum distance in meters a user wants to travel to reach the travel option
     * minimum: 0
     * @return radius
     */
    @JvmField
    @get:Schema(description = "Maximum distance in meters a user wants to travel to reach the travel option")
    @JsonProperty("radius")
    var radius: Int? = null

    /**
     * Get to
     * @return to
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("to")
    var to: Place? = null

    /**
     * instead of using the from/to construct, it is also possible to give an indication of the distance to travel. The process identifier 'USE_ESTIMATED_DISTANCE' is used to indicate this scenario. Also in meters
     * minimum: 0
     * @return estimatedDistance
     */
    @get:Schema(description = "instead of using the from/to construct, it is also possible to give an indication of the distance to travel. The process identifier 'USE_ESTIMATED_DISTANCE' is used to indicate this scenario. Also in meters")
    @JsonProperty("estimatedDistance")
    var estimatedDistance: Int? = null

    /**
     * The intended departure time. If left out and no arrivalTime is set, the current time should be assumed.
     * @return departureTime
     */
    @JvmField
    @get:Schema(description = "The intended departure time. If left out and no arrivalTime is set, the current time should be assumed.")
    @JsonProperty("departureTime")
    var departureTime: OffsetDateTime? = null

    /**
     * The intended arrival time, at the to place if set otherwise the time the user intends to stop using the asset.
     * @return arrivalTime
     */
    @JvmField
    @get:Schema(description = "The intended arrival time, at the to place if set otherwise the time the user intends to stop using the asset.")
    @JsonProperty("arrivalTime")
    var arrivalTime: OffsetDateTime? = null

    /**
     * The number of people that intend to travel, including the customer.
     * minimum: 1
     * @return nrOfTravelers
     */
    @get:Schema(description = "The number of people that intend to travel, including the customer.")
    @JsonProperty("nrOfTravelers")
    var nrOfTravelers: Int? = null

    @JsonProperty("travelers")
    private var travelers: @Valid MutableList<Traveler>? = null

    @JsonProperty("useAssets")
    private var useAssets: @Valid MutableList<String>? = null

    @JsonProperty("userGroups")
    private var userGroups: @Valid MutableList<String>? = null

    @JsonProperty("useAssetTypes")
    private var useAssetTypes: @Valid MutableList<String>? = null
    fun from(from: Place?): PlanningRequest {
        this.from = from
        return this
    }

    fun radius(radius: Int?): PlanningRequest {
        this.radius = radius
        return this
    }

    fun to(to: Place?): PlanningRequest {
        this.to = to
        return this
    }

    fun estimatedDistance(estimatedDistance: Int?): PlanningRequest {
        this.estimatedDistance = estimatedDistance
        return this
    }

    fun departureTime(departureTime: OffsetDateTime?): PlanningRequest {
        this.departureTime = departureTime
        return this
    }

    fun arrivalTime(arrivalTime: OffsetDateTime?): PlanningRequest {
        this.arrivalTime = arrivalTime
        return this
    }

    fun nrOfTravelers(nrOfTravelers: Int?): PlanningRequest {
        this.nrOfTravelers = nrOfTravelers
        return this
    }

    fun travelers(travelers: List<Traveler>?): PlanningRequest {
        this.travelers = travelers
        return this
    }

    fun addTravelersItem(travelersItem: Traveler): PlanningRequest {
        if (travelers == null) {
            travelers = ArrayList()
        }
        travelers!!.add(travelersItem)
        return this
    }

    /**
     * Extra information about the people that intend to travel if relevant, length must be less than or equal to nrOftravelers.
     * @return travelers
     */
    @Schema(description = "Extra information about the people that intend to travel if relevant, length must be less than or equal to nrOftravelers.")
    fun getTravelers(): @Valid MutableList<Traveler>? {
        return travelers
    }

    fun setTravelers(travelers: List<Traveler>?) {
        this.travelers = travelers
    }

    fun useAssets(useAssets: List<String>?): PlanningRequest {
        this.useAssets = useAssets
        return this
    }

    fun addUseAssetsItem(useAssetsItem: String): PlanningRequest {
        if (useAssets == null) {
            useAssets = ArrayList()
        }
        useAssets!!.add(useAssetsItem)
        return this
    }

    /**
     * The specific asset(s) the user wishes to receive leg options for
     * @return useAssets
     */
    @Schema(description = "The specific asset(s) the user wishes to receive leg options for")
    fun getUseAssets(): List<String>? {
        return useAssets
    }

    fun setUseAssets(useAssets: List<String>?) {
        this.useAssets = useAssets
    }

    fun userGroups(userGroups: List<String>?): PlanningRequest {
        this.userGroups = userGroups
        return this
    }

    fun addUserGroupsItem(userGroupsItem: String): PlanningRequest {
        if (userGroups == null) {
            userGroups = ArrayList()
        }
        userGroups!!.add(userGroupsItem)
        return this
    }

    /**
     * Id(s) of user groups that the user belongs to. This provides access to exclusive assets that are hidden to the public. Id's are agreed upon by TO and MP.
     * @return userGroups
     */
    @Schema(description = "Id(s) of user groups that the user belongs to. This provides access to exclusive assets that are hidden to the public. Id's are agreed upon by TO and MP.")
    fun getUserGroups(): List<String>? {
        return userGroups
    }

    fun setUserGroups(userGroups: List<String>?) {
        this.userGroups = userGroups
    }

    fun useAssetTypes(useAssetTypes: List<String>?): PlanningRequest {
        this.useAssetTypes = useAssetTypes
        return this
    }

    fun addUseAssetTypesItem(useAssetTypesItem: String): PlanningRequest {
        if (useAssetTypes == null) {
            useAssetTypes = ArrayList()
        }
        useAssetTypes!!.add(useAssetTypesItem)
        return this
    }

    /**
     * The specific asset type(s) the user wishes to receive leg options for
     * @return useAssetTypes
     */
    @Schema(description = "The specific asset type(s) the user wishes to receive leg options for")
    fun getUseAssetTypes(): List<String>? {
        return useAssetTypes
    }

    fun setUseAssetTypes(useAssetTypes: List<String>?) {
        this.useAssetTypes = useAssetTypes
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val planningRequest = o as PlanningRequest
        return from == planningRequest.from && radius == planningRequest.radius && to == planningRequest.to && estimatedDistance == planningRequest.estimatedDistance && departureTime == planningRequest.departureTime && arrivalTime == planningRequest.arrivalTime && nrOfTravelers == planningRequest.nrOfTravelers && travelers == planningRequest.travelers && useAssets == planningRequest.useAssets && userGroups == planningRequest.userGroups && useAssetTypes == planningRequest.useAssetTypes
    }

    override fun hashCode(): Int {
        return Objects.hash(
            from,
            radius,
            to,
            estimatedDistance,
            departureTime,
            arrivalTime,
            nrOfTravelers,
            travelers,
            useAssets,
            userGroups,
            useAssetTypes
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class PlanningRequest {\n")
        sb.append("    from: ").append(toIndentedString(from)).append("\n")
        sb.append("    radius: ").append(toIndentedString(radius)).append("\n")
        sb.append("    to: ").append(toIndentedString(to)).append("\n")
        sb.append("    estimatedDistance: ").append(toIndentedString(estimatedDistance)).append("\n")
        sb.append("    departureTime: ").append(toIndentedString(departureTime)).append("\n")
        sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n")
        sb.append("    nrOfTravelers: ").append(toIndentedString(nrOfTravelers)).append("\n")
        sb.append("    travelers: ").append(toIndentedString(travelers)).append("\n")
        sb.append("    useAssets: ").append(toIndentedString(useAssets)).append("\n")
        sb.append("    userGroups: ").append(toIndentedString(userGroups)).append("\n")
        sb.append("    useAssetTypes: ").append(toIndentedString(useAssetTypes)).append("\n")
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
