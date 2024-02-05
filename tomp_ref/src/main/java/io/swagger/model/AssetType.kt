package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * AssetType
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
class AssetType {
    /**
     * Unique identifier of an asset type,
     * @return id
     */
    @get:Schema(required = true, description = "Unique identifier of an asset type,")
    @JsonProperty("id")
    var id: String? = null

    /**
     * If stationId is present, the nrAvailable is expected to find the availability at that particular station
     * @return stationId
     */
    @get:Schema(description = "If stationId is present, the nrAvailable is expected to find the availability at that particular station")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * Get nrAvailable
     * @return nrAvailable
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("nrAvailable")
    var nrAvailable: Int? = null

    @JsonProperty("assets")
    private var assets: @Valid MutableList<Asset>? = null

    /**
     * Get assetClass
     * @return assetClass
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("assetClass")
    var assetClass: AssetClass? = null

    /**
     * a more precise classification of the asset, like 'cargo bike', 'public bus', 'coach bus', 'office bus', 'water taxi',  'segway'. This is mandatory when using 'OTHER' as class.
     * @return assetSubClass
     */
    @JvmField
    @get:Schema(description = "a more precise classification of the asset, like 'cargo bike', 'public bus', 'coach bus', 'office bus', 'water taxi',  'segway'. This is mandatory when using 'OTHER' as class.")
    @JsonProperty("assetSubClass")
    var assetSubClass: String? = null

    /**
     * Get sharedProperties
     * @return sharedProperties
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("sharedProperties")
    var sharedProperties: AssetProperties? = null

    @JsonProperty("applicablePricings")
    private var applicablePricings: @Valid MutableList<SystemPricingPlan>? = null

    @JsonProperty("conditions")
    private var conditions: @Valid MutableList<OneOfassetTypeConditionsItems>? = null
    fun id(id: String?): AssetType {
        this.id = id
        return this
    }

    fun stationId(stationId: String?): AssetType {
        this.stationId = stationId
        return this
    }

    fun nrAvailable(nrAvailable: Int?): AssetType {
        this.nrAvailable = nrAvailable
        return this
    }

    fun assets(assets: List<Asset>?): AssetType {
        this.assets = assets
        return this
    }

    fun addAssetsItem(assetsItem: Asset): AssetType {
        if (assets == null) {
            assets = ArrayList()
        }
        assets!!.add(assetsItem)
        return this
    }

    /**
     * Get assets
     * @return assets
     */
    @Schema(description = "")
    fun getAssets(): @Valid MutableList<Asset>? {
        return assets
    }

    fun setAssets(assets: List<Asset>?) {
        this.assets = assets
    }

    fun assetClass(assetClass: AssetClass?): AssetType {
        this.assetClass = assetClass
        return this
    }

    fun assetSubClass(assetSubClass: String?): AssetType {
        this.assetSubClass = assetSubClass
        return this
    }

    fun sharedProperties(sharedProperties: AssetProperties?): AssetType {
        this.sharedProperties = sharedProperties
        return this
    }

    fun applicablePricings(applicablePricings: List<SystemPricingPlan>?): AssetType {
        this.applicablePricings = applicablePricings
        return this
    }

    fun addApplicablePricingsItem(applicablePricingsItem: SystemPricingPlan): AssetType {
        if (applicablePricings == null) {
            applicablePricings = ArrayList()
        }
        applicablePricings!!.add(applicablePricingsItem)
        return this
    }

    /**
     * pricing plans that can be applicable for this assetType. Business logic to determine the final pricing plan is not exposed. Just call the plannings endpoint (v1.2) or the inquiries endpoint (v.1.3)
     * @return applicablePricings
     */
    @Schema(description = "pricing plans that can be applicable for this assetType. Business logic to determine the final pricing plan is not exposed. Just call the plannings endpoint (v1.2) or the inquiries endpoint (v.1.3)")
    fun getApplicablePricings(): @Valid MutableList<SystemPricingPlan>? {
        return applicablePricings
    }

    fun setApplicablePricings(applicablePricings: List<SystemPricingPlan>?) {
        this.applicablePricings = applicablePricings
    }

    fun conditions(conditions: List<OneOfassetTypeConditionsItems>?): AssetType {
        this.conditions = conditions
        return this
    }

    fun addConditionsItem(conditionsItem: OneOfassetTypeConditionsItems): AssetType {
        if (conditions == null) {
            conditions = ArrayList()
        }
        conditions!!.add(conditionsItem)
        return this
    }

    /**
     * extra information about the asset type, making it possible to f.x. specifying that booking this car requires a driver license.
     * @return conditions
     */
    @Schema(description = "extra information about the asset type, making it possible to f.x. specifying that booking this car requires a driver license.")
    fun getConditions(): List<OneOfassetTypeConditionsItems>? {
        return conditions
    }

    fun setConditions(conditions: List<OneOfassetTypeConditionsItems>?) {
        this.conditions = conditions
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val assetType = o as AssetType
        return id == assetType.id && stationId == assetType.stationId && nrAvailable == assetType.nrAvailable && assets == assetType.assets && assetClass == assetType.assetClass && assetSubClass == assetType.assetSubClass && sharedProperties == assetType.sharedProperties && applicablePricings == assetType.applicablePricings && conditions == assetType.conditions
    }

    override fun hashCode(): Int {
        return Objects.hash(id, stationId, nrAvailable, assets, assetClass, assetSubClass, sharedProperties, applicablePricings, conditions)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class AssetType {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    nrAvailable: ").append(toIndentedString(nrAvailable)).append("\n")
        sb.append("    assets: ").append(toIndentedString(assets)).append("\n")
        sb.append("    assetClass: ").append(toIndentedString(assetClass)).append("\n")
        sb.append("    assetSubClass: ").append(toIndentedString(assetSubClass)).append("\n")
        sb.append("    sharedProperties: ").append(toIndentedString(sharedProperties)).append("\n")
        sb.append("    applicablePricings: ").append(toIndentedString(applicablePricings)).append("\n")
        sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n")
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
