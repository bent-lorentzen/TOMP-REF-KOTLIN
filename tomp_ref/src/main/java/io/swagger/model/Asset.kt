package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated

/**
 * Asset
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class Asset {
    /**
     * Identifier of an asset. Whenever used in Operator Information changed after every trip (GDPR).
     * @return id
     */
    @JvmField
    @get:Schema(
        required = true,
        description = "Identifier of an asset. Whenever used in Operator Information changed after every trip (GDPR)."
    )
    @JsonProperty("id")
    var id: String? = null

    /**
     * true indicates the bike is currently reserved for someone else
     * @return isReserved
     */
    @get:Schema(description = "true indicates the bike is currently reserved for someone else")
    @JsonProperty("isReserved")
    var isIsReserved: Boolean? = null
        private set

    /**
     * optional addition to determine if an asset is reserved in the future
     * @return isReservedFrom
     */
    @get:Schema(description = "optional addition to determine if an asset is reserved in the future")
    @JsonProperty("isReservedFrom")
    var isReservedFrom: OffsetDateTime? = null

    /**
     * optional addition to determine when asset is available in the future
     * @return isReservedTo
     */
    @get:Schema(description = "optional addition to determine when asset is available in the future")
    @JsonProperty("isReservedTo")
    var isReservedTo: OffsetDateTime? = null

    /**
     * true indicates the asset is currently disabled (broken)
     * @return isDisabled
     */
    @get:Schema(description = "true indicates the asset is currently disabled (broken)")
    @JsonProperty("isDisabled")
    var isIsDisabled: Boolean? = null
        private set

    /**
     * deep-linking option from GBFS+. Only added to be consistent with GBFS 2.0
     * @return rentalUrl
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/app?sid=1234567890",
        description = "deep-linking option from GBFS+. Only added to be consistent with GBFS 2.0"
    )
    @JsonProperty("rentalUrl")
    var rentalUrl: String? = null

    /**
     * deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0
     * @return rentalUrlAndroid
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/app?sid=1234567890&platform=android",
        description = "deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0"
    )
    @JsonProperty("rentalUrlAndroid")
    var rentalUrlAndroid: String? = null

    /**
     * deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0
     * @return rentalUrlIOS
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios",
        description = "deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0"
    )
    @JsonProperty("rentalUrlIOS")
    var rentalUrlIOS: String? = null

    /**
     * the current mileage of the asset
     * minimum: 0
     * @return mileage
     */
    @get:Schema(description = "the current mileage of the asset")
    @JsonProperty("mileage")
    var mileage: Float? = null

    /**
     * the usage of this field requires a secure environment. When assets are published in available-assets, this field can be used to track assets. Be aware of this.
     * @return licensePlate
     */
    @get:Schema(description = "the usage of this field requires a secure environment. When assets are published in available-assets, this field can be used to track assets. Be aware of this.")
    @JsonProperty("licensePlate")
    var licensePlate: String? = null

    /**
     * Get overriddenProperties
     * @return overriddenProperties
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("overriddenProperties")
    var overriddenProperties: AssetProperties? = null
    fun id(id: String?): Asset {
        this.id = id
        return this
    }

    fun isReserved(isReserved: Boolean?): Asset {
        isIsReserved = isReserved
        return this
    }

    fun setIsReserved(isReserved: Boolean?) {
        isIsReserved = isReserved
    }

    fun isReservedFrom(isReservedFrom: OffsetDateTime?): Asset {
        this.isReservedFrom = isReservedFrom
        return this
    }

    fun isReservedTo(isReservedTo: OffsetDateTime?): Asset {
        this.isReservedTo = isReservedTo
        return this
    }

    fun isDisabled(isDisabled: Boolean?): Asset {
        isIsDisabled = isDisabled
        return this
    }

    fun setIsDisabled(isDisabled: Boolean?) {
        isIsDisabled = isDisabled
    }

    fun rentalUrl(rentalUrl: String?): Asset {
        this.rentalUrl = rentalUrl
        return this
    }

    fun rentalUrlAndroid(rentalUrlAndroid: String?): Asset {
        this.rentalUrlAndroid = rentalUrlAndroid
        return this
    }

    fun rentalUrlIOS(rentalUrlIOS: String?): Asset {
        this.rentalUrlIOS = rentalUrlIOS
        return this
    }

    fun mileage(mileage: Float?): Asset {
        this.mileage = mileage
        return this
    }

    fun licensePlate(licensePlate: String?): Asset {
        this.licensePlate = licensePlate
        return this
    }

    fun overriddenProperties(overriddenProperties: AssetProperties?): Asset {
        this.overriddenProperties = overriddenProperties
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val asset = o as Asset
        return id == asset.id && isIsReserved == asset.isIsReserved && isReservedFrom == asset.isReservedFrom && isReservedTo == asset.isReservedTo && isIsDisabled == asset.isIsDisabled && rentalUrl == asset.rentalUrl && rentalUrlAndroid == asset.rentalUrlAndroid && rentalUrlIOS == asset.rentalUrlIOS && mileage == asset.mileage && licensePlate == asset.licensePlate && overriddenProperties == asset.overriddenProperties
    }

    override fun hashCode(): Int {
        return Objects.hash(
            id,
            isIsReserved,
            isReservedFrom,
            isReservedTo,
            isIsDisabled,
            rentalUrl,
            rentalUrlAndroid,
            rentalUrlIOS,
            mileage,
            licensePlate,
            overriddenProperties
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Asset {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    isReserved: ").append(toIndentedString(isIsReserved)).append("\n")
        sb.append("    isReservedFrom: ").append(toIndentedString(isReservedFrom)).append("\n")
        sb.append("    isReservedTo: ").append(toIndentedString(isReservedTo)).append("\n")
        sb.append("    isDisabled: ").append(toIndentedString(isIsDisabled)).append("\n")
        sb.append("    rentalUrl: ").append(toIndentedString(rentalUrl)).append("\n")
        sb.append("    rentalUrlAndroid: ").append(toIndentedString(rentalUrlAndroid)).append("\n")
        sb.append("    rentalUrlIOS: ").append(toIndentedString(rentalUrlIOS)).append("\n")
        sb.append("    mileage: ").append(toIndentedString(mileage)).append("\n")
        sb.append("    licensePlate: ").append(toIndentedString(licensePlate)).append("\n")
        sb.append("    overriddenProperties: ").append(toIndentedString(overriddenProperties)).append("\n")
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
