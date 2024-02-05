package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * street address, including number OR PO box number, eventually extended with internal reference like room number, could match Content-Language
 */
@Schema(description = "street address, including number OR PO box number, eventually extended with internal reference like room number, could match Content-Language")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-06-08T10:19:00.171Z[GMT]")
class Address {
    /**
     * Get streetAddress
     * @return streetAddress
     */
    @JvmField
    @get:Schema(example = "example street 18, 2nd floor, 18-B33", required = true, description = "")
    @JsonProperty("streetAddress")
    var streetAddress: String? = null

    /**
     * street, consistent with streetAddress
     * @return street
     */
    @get:Schema(description = "street, consistent with streetAddress")
    @JsonProperty("street")
    var street: String? = null

    /**
     * house number, consistent with streetAddress
     * minimum: 0
     * @return houseNumber
     */
    @get:Schema(description = "house number, consistent with streetAddress")
    @JsonProperty("houseNumber")
    var houseNumber: Int? = null

    /**
     * the additional part of the house number (f.x. 13bis, where 'bis' is the additional part), consistent with streetAddress
     * @return houseNumberAddition
     */
    @get:Schema(description = "the additional part of the house number (f.x. 13bis, where 'bis' is the additional part), consistent with streetAddress")
    @JsonProperty("houseNumberAddition")
    var houseNumberAddition: String? = null

    /**
     * additional information to find the address (f.x. just around the corner)
     * @return addressAdditionalInfo
     */
    @get:Schema(description = "additional information to find the address (f.x. just around the corner)")
    @JsonProperty("addressAdditionalInfo")
    var addressAdditionalInfo: String? = null

    /**
     * city or town, principal subdivision such as province, state or county, could match Content-Language
     * @return areaReference
     */
    @JvmField
    @get:Schema(
        example = "Smallcity, Pinetree county",
        required = true,
        description = "city or town, principal subdivision such as province, state or county, could match Content-Language"
    )
    @JsonProperty("areaReference")
    var areaReference: String? = null

    /**
     * specified city or town, consistent with areaReference
     * @return city
     */
    @get:Schema(description = "specified city or town, consistent with areaReference")
    @JsonProperty("city")
    var city: String? = null

    /**
     * province or region, consistent with areaReference
     * @return province
     */
    @get:Schema(description = "province or region, consistent with areaReference")
    @JsonProperty("province")
    var province: String? = null

    /**
     * state, consistent with areaReference
     * @return state
     */
    @get:Schema(description = "state, consistent with areaReference")
    @JsonProperty("state")
    var state: String? = null

    /**
     * Get postalCode
     * @return postalCode
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("postalCode")
    var postalCode: String? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return country
     */
    @JvmField
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("country")
    var country: String? = null
    fun streetAddress(streetAddress: String?): Address {
        this.streetAddress = streetAddress
        return this
    }

    fun street(street: String?): Address {
        this.street = street
        return this
    }

    fun houseNumber(houseNumber: Int?): Address {
        this.houseNumber = houseNumber
        return this
    }

    fun houseNumberAddition(houseNumberAddition: String?): Address {
        this.houseNumberAddition = houseNumberAddition
        return this
    }

    fun addressAdditionalInfo(addressAdditionalInfo: String?): Address {
        this.addressAdditionalInfo = addressAdditionalInfo
        return this
    }

    fun areaReference(areaReference: String?): Address {
        this.areaReference = areaReference
        return this
    }

    fun city(city: String?): Address {
        this.city = city
        return this
    }

    fun province(province: String?): Address {
        this.province = province
        return this
    }

    fun state(state: String?): Address {
        this.state = state
        return this
    }

    fun postalCode(postalCode: String?): Address {
        this.postalCode = postalCode
        return this
    }

    fun country(country: String?): Address {
        this.country = country
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val address = o as Address
        return streetAddress == address.streetAddress && street == address.street && houseNumber == address.houseNumber && houseNumberAddition == address.houseNumberAddition && addressAdditionalInfo == address.addressAdditionalInfo && areaReference == address.areaReference && city == address.city && province == address.province && state == address.state && postalCode == address.postalCode && country == address.country
    }

    override fun hashCode(): Int {
        return Objects.hash(
            streetAddress,
            street,
            houseNumber,
            houseNumberAddition,
            addressAdditionalInfo,
            areaReference,
            city,
            province,
            state,
            postalCode,
            country
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Address {\n")
        sb.append("    streetAddress: ").append(toIndentedString(streetAddress)).append("\n")
        sb.append("    street: ").append(toIndentedString(street)).append("\n")
        sb.append("    houseNumber: ").append(toIndentedString(houseNumber)).append("\n")
        sb.append("    houseNumberAddition: ").append(toIndentedString(houseNumberAddition)).append("\n")
        sb.append("    addressAdditionalInfo: ").append(toIndentedString(addressAdditionalInfo)).append("\n")
        sb.append("    areaReference: ").append(toIndentedString(areaReference)).append("\n")
        sb.append("    city: ").append(toIndentedString(city)).append("\n")
        sb.append("    province: ").append(toIndentedString(province)).append("\n")
        sb.append("    state: ").append(toIndentedString(state)).append("\n")
        sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n")
        sb.append("    country: ").append(toIndentedString(country)).append("\n")
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
