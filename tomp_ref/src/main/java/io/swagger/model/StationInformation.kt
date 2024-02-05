package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * StationInformation
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class StationInformation {
    /**
     * unique identifier of a station
     * @return stationId
     */
    @JvmField
    @get:Schema(example = "XX:Y:12345678", required = true, description = "unique identifier of a station")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * public name of the station, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(
        example = "Island Central",
        required = true,
        description = "public name of the station, could match Content-Language"
    )
    @JsonProperty("name")
    var name: String? = null

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

    /**
     * Cross street of where the station is located. This field is intended to be a descriptive field for human consumption. In cities, this would be a cross street, but could also be a description of a location in a park, etc, should match Content-Language
     * @return crossStreet
     */
    @get:Schema(
        example = "on the corner with Secondary Road",
        description = "Cross street of where the station is located. This field is intended to be a descriptive field for human consumption. In cities, this would be a cross street, but could also be a description of a location in a park, etc, should match Content-Language"
    )
    @JsonProperty("crossStreet")
    var crossStreet: String? = null

    /**
     * ID of the region where the station operates (see \"systemRegions\")
     * @return regionId
     */
    @JvmField
    @get:Schema(description = "ID of the region where the station operates (see \"systemRegions\")")
    @JsonProperty("regionId")
    var regionId: String? = null

    /**
     * Gets or Sets rentalMethods
     */
    enum class RentalMethodsEnum(private val value: String) {
        KEY("KEY"),
        CREDITCARD("CREDITCARD"),
        PAYPASS("PAYPASS"),
        APPLEPAY("APPLEPAY"),
        ANDROIDPAY("ANDROIDPAY"),
        TRANSITCARD("TRANSITCARD"),
        ACCOUNTNUMBER("ACCOUNTNUMBER"),
        PHONE("PHONE"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): RentalMethodsEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    @JsonProperty("rentalMethods")
    private var rentalMethods: @Valid MutableList<RentalMethodsEnum>? = null

    /**
     * web uri for renting assets at this station. Only added to be consistent with GBFS 2.0.
     * @return rentalUrl
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com",
        description = "web uri for renting assets at this station. Only added to be consistent with GBFS 2.0."
    )
    @JsonProperty("rentalUrl")
    var rentalUrl: String? = null

    /**
     * android uri for renting assets at this station. Only added to be consistent with GBFS 2.0.
     * @return rentalUrlAndroid
     */
    @get:Schema(
        example = "https://www.rentmyfreebikecom/app?sid=1234567890&platform=android",
        description = "android uri for renting assets at this station. Only added to be consistent with GBFS 2.0."
    )
    @JsonProperty("rentalUrlAndroid")
    var rentalUrlAndroid: String? = null

    /**
     * ios uri for renting assets at this station. Only added to be consistent with GBFS 2.0.
     * @return rentalUrlIOS
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios",
        description = "ios uri for renting assets at this station. Only added to be consistent with GBFS 2.0."
    )
    @JsonProperty("rentalUrlIOS")
    var rentalUrlIOS: String? = null
    fun stationId(stationId: String?): StationInformation {
        this.stationId = stationId
        return this
    }

    fun name(name: String?): StationInformation {
        this.name = name
        return this
    }

    fun coordinates(coordinates: Coordinates?): StationInformation {
        this.coordinates = coordinates
        return this
    }

    fun physicalAddress(physicalAddress: Address?): StationInformation {
        this.physicalAddress = physicalAddress
        return this
    }

    fun crossStreet(crossStreet: String?): StationInformation {
        this.crossStreet = crossStreet
        return this
    }

    fun regionId(regionId: String?): StationInformation {
        this.regionId = regionId
        return this
    }

    fun rentalMethods(rentalMethods: List<RentalMethodsEnum>?): StationInformation {
        this.rentalMethods = rentalMethods
        return this
    }

    fun addRentalMethodsItem(rentalMethodsItem: RentalMethodsEnum): StationInformation {
        if (rentalMethods == null) {
            rentalMethods = ArrayList()
        }
        rentalMethods!!.add(rentalMethodsItem)
        return this
    }

    /**
     * Array of enumerables containing the payment methods accepted at this station.
     * @return rentalMethods
     */
    @Schema(
        example = "[\"CREDITCARD\",\"PAYPASS\",\"APPLEPAY\"]",
        description = "Array of enumerables containing the payment methods accepted at this station."
    )
    fun getRentalMethods(): List<RentalMethodsEnum>? {
        return rentalMethods
    }

    fun setRentalMethods(rentalMethods: List<RentalMethodsEnum>?) {
        this.rentalMethods = rentalMethods
    }

    fun rentalUrl(rentalUrl: String?): StationInformation {
        this.rentalUrl = rentalUrl
        return this
    }

    fun rentalUrlAndroid(rentalUrlAndroid: String?): StationInformation {
        this.rentalUrlAndroid = rentalUrlAndroid
        return this
    }

    fun rentalUrlIOS(rentalUrlIOS: String?): StationInformation {
        this.rentalUrlIOS = rentalUrlIOS
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val stationInformation = o as StationInformation
        return stationId == stationInformation.stationId && name == stationInformation.name && coordinates == stationInformation.coordinates && physicalAddress == stationInformation.physicalAddress && crossStreet == stationInformation.crossStreet && regionId == stationInformation.regionId && rentalMethods == stationInformation.rentalMethods && rentalUrl == stationInformation.rentalUrl && rentalUrlAndroid == stationInformation.rentalUrlAndroid && rentalUrlIOS == stationInformation.rentalUrlIOS
    }

    override fun hashCode(): Int {
        return Objects.hash(
            stationId,
            name,
            coordinates,
            physicalAddress,
            crossStreet,
            regionId,
            rentalMethods,
            rentalUrl,
            rentalUrlAndroid,
            rentalUrlIOS
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class StationInformation {\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n")
        sb.append("    physicalAddress: ").append(toIndentedString(physicalAddress)).append("\n")
        sb.append("    crossStreet: ").append(toIndentedString(crossStreet)).append("\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    rentalMethods: ").append(toIndentedString(rentalMethods)).append("\n")
        sb.append("    rentalUrl: ").append(toIndentedString(rentalUrl)).append("\n")
        sb.append("    rentalUrlAndroid: ").append(toIndentedString(rentalUrlAndroid)).append("\n")
        sb.append("    rentalUrlIOS: ").append(toIndentedString(rentalUrlIOS)).append("\n")
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
