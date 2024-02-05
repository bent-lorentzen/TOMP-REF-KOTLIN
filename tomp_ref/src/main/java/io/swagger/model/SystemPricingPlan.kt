package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * SystemPricingPlan
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class SystemPricingPlan {
    /**
     * a unique identifier for this plan in the system
     * @return planId
     */
    @JvmField
    @get:Schema(
        example = "freeplan1",
        required = true,
        description = "a unique identifier for this plan in the system"
    )
    @JsonProperty("planId")
    var planId: String? = null

    /**
     * a fully qualified URL where the customer can learn more about this particular scheme
     * @return url
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/freeplan",
        description = "a fully qualified URL where the customer can learn more about this particular scheme"
    )
    @JsonProperty("url")
    var url: String? = null

    /**
     * name of this pricing scheme, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(
        example = "Free Plan",
        required = true,
        description = "name of this pricing scheme, could match Content-Language"
    )
    @JsonProperty("name")
    var name: String? = null

    /**
     * pricing plan for a specific station
     * @return stationId
     */
    @get:Schema(description = "pricing plan for a specific station")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * pricing plan for a specific region
     * @return regionId
     */
    @get:Schema(description = "pricing plan for a specific region")
    @JsonProperty("regionId")
    var regionId: String? = null

    /**
     * Get fare
     * @return fare
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("fare")
    var fare: Fare? = null

    /**
     * false indicates that no additional tax will be added (either because tax is not charged, or because it is included) true indicates that tax will be added to the base price
     * @return isTaxable
     */
    @get:Schema(
        required = true,
        description = "false indicates that no additional tax will be added (either because tax is not charged, or because it is included) true indicates that tax will be added to the base price"
    )
    @JsonProperty("isTaxable")
    var isIsTaxable: Boolean? = null
        private set

    /**
     * Text field describing the particular pricing plan in human readable terms. This should include the duration, price, conditions, etc. that the publisher would like users to see. This is intended to be a human-readable description and should not be used for automatic calculations, should match Content-Language
     * @return description
     */
    @JvmField
    @get:Schema(
        example = "Unlimited plan for free bikes, as long as you don't break them!",
        required = true,
        description = "Text field describing the particular pricing plan in human readable terms. This should include the duration, price, conditions, etc. that the publisher would like users to see. This is intended to be a human-readable description and should not be used for automatic calculations, should match Content-Language"
    )
    @JsonProperty("description")
    var description: String? = null
    fun planId(planId: String?): SystemPricingPlan {
        this.planId = planId
        return this
    }

    fun url(url: String?): SystemPricingPlan {
        this.url = url
        return this
    }

    fun name(name: String?): SystemPricingPlan {
        this.name = name
        return this
    }

    fun stationId(stationId: String?): SystemPricingPlan {
        this.stationId = stationId
        return this
    }

    fun regionId(regionId: String?): SystemPricingPlan {
        this.regionId = regionId
        return this
    }

    fun fare(fare: Fare?): SystemPricingPlan {
        this.fare = fare
        return this
    }

    fun isTaxable(isTaxable: Boolean?): SystemPricingPlan {
        isIsTaxable = isTaxable
        return this
    }

    fun setIsTaxable(isTaxable: Boolean?) {
        isIsTaxable = isTaxable
    }

    fun description(description: String?): SystemPricingPlan {
        this.description = description
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemPricingPlan = o as SystemPricingPlan
        return planId == systemPricingPlan.planId && url == systemPricingPlan.url && name == systemPricingPlan.name && stationId == systemPricingPlan.stationId && regionId == systemPricingPlan.regionId && fare == systemPricingPlan.fare && isIsTaxable == systemPricingPlan.isIsTaxable && description == systemPricingPlan.description
    }

    override fun hashCode(): Int {
        return Objects.hash(planId, url, name, stationId, regionId, fare, isIsTaxable, description)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemPricingPlan {\n")
        sb.append("    planId: ").append(toIndentedString(planId)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    fare: ").append(toIndentedString(fare)).append("\n")
        sb.append("    isTaxable: ").append(toIndentedString(isIsTaxable)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
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
