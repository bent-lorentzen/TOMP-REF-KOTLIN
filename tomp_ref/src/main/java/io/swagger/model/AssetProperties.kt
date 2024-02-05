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
 * Aspects of an asset or assetType. Most aspects are optional and should only be used when applicable.
 */
@Schema(description = "Aspects of an asset or assetType. Most aspects are optional and should only be used when applicable.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class AssetProperties {
    /**
     * name of asset (type), required in either assetType or asset, should match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(description = "name of asset (type), required in either assetType or asset, should match Content-Language")
    @JsonProperty("name")
    var name: String? = null

    /**
     * Get location
     * @return location
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("location")
    var location: Place? = null

    /**
     * Gets or Sets fuel
     */
    enum class FuelEnum(private val value: String) {
        NONE("NONE"),
        GASOLINE("GASOLINE"),
        DIESEL("DIESEL"),
        ELECTRIC("ELECTRIC"),
        HYBRID_GASOLINE("HYBRID_GASOLINE"),
        HYBRID_DIESEL("HYBRID_DIESEL"),
        HYBRID_GAS("HYBRID_GAS"),
        HYDROGEN("HYDROGEN"),
        GAS("GAS"),
        BIO_MASS("BIO_MASS"),
        KEROSINE("KEROSINE"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): FuelEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * Get fuel
     * @return fuel
     */
    @get:Schema(description = "")
    @JsonProperty("fuel")
    var fuel: FuelEnum? = null

    /**
     * Energy efficiency
     */
    enum class EnergyLabelEnum(private val value: String) {
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): EnergyLabelEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * Energy efficiency
     * @return energyLabel
     */
    @JvmField
    @get:Schema(description = "Energy efficiency")
    @JsonProperty("energyLabel")
    var energyLabel: EnergyLabelEnum? = null

    /**
     * Get co2PerKm
     * minimum: 0
     * @return co2PerKm
     */
    @get:Schema(description = "")
    @JsonProperty("co2PerKm")
    var co2PerKm: Float? = null

    /**
     * brand of the asset
     * @return brand
     */
    @get:Schema(description = "brand of the asset")
    @JsonProperty("brand")
    var brand: String? = null

    /**
     * Get model
     * @return model
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("model")
    var model: String? = null

    /**
     * Get buildingYear
     * @return buildingYear
     */
    @get:Schema(description = "")
    @JsonProperty("buildingYear")
    var buildingYear: Int? = null

    /**
     * true indicates asset is allowed to travel abroad
     * @return travelAbroad
     */
    @get:Schema(description = "true indicates asset is allowed to travel abroad")
    @JsonProperty("travelAbroad")
    var isTravelAbroad: Boolean? = null

    /**
     * true indicates airconditioning required
     * @return airConditioning
     */
    @get:Schema(description = "true indicates airconditioning required")
    @JsonProperty("airConditioning")
    var isAirConditioning: Boolean? = null

    /**
     * true indicates cabrio required
     * @return cabrio
     */
    @get:Schema(description = "true indicates cabrio required")
    @JsonProperty("cabrio")
    var isCabrio: Boolean? = null

    /**
     * colour of the asset, should match Content-Language
     * @return colour
     */
    @get:Schema(description = "colour of the asset, should match Content-Language")
    @JsonProperty("colour")
    var colour: String? = null

    /**
     * describes options to carry cargo, should match Content-Language
     * @return cargo
     */
    @get:Schema(description = "describes options to carry cargo, should match Content-Language")
    @JsonProperty("cargo")
    var cargo: String? = null

    /**
     * describes if asset is or needs to be easily accessible
     */
    enum class EasyAccessibilityEnum(private val value: String) {
        LIFT("LIFT"),
        ESCALATOR("ESCALATOR"),
        GROUND_LEVEL("GROUND_LEVEL"),
        SIGHTIMPAIRMENT("SIGHTIMPAIRMENT"),
        HEARINGIMPAIRMENT("HEARINGIMPAIRMENT"),
        WHEELCHAIR("WHEELCHAIR");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): EasyAccessibilityEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * describes if asset is or needs to be easily accessible
     * @return easyAccessibility
     */
    @get:Schema(description = "describes if asset is or needs to be easily accessible")
    @JsonProperty("easyAccessibility")
    var easyAccessibility: EasyAccessibilityEnum? = null

    /**
     * number of gears of the asset
     * @return gears
     */
    @get:Schema(description = "number of gears of the asset")
    @JsonProperty("gears")
    var gears: Int? = null

    /**
     * type of gearbox
     */
    enum class GearboxEnum(private val value: String) {
        MANUAL("MANUAL"),
        AUTOMATIC("AUTOMATIC"),
        SEMIAUTOMATIC("SEMIAUTOMATIC");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): GearboxEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * type of gearbox
     * @return gearbox
     */
    @get:Schema(description = "type of gearbox")
    @JsonProperty("gearbox")
    var gearbox: GearboxEnum? = null

    /**
     * Link to an image of the asset
     * @return image
     */
    @get:Schema(
        example = "https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg",
        description = "Link to an image of the asset"
    )
    @JsonProperty("image")
    var image: String? = null

    /**
     * true indicates infant seat required
     * @return infantSeat
     */
    @get:Schema(description = "true indicates infant seat required")
    @JsonProperty("infantSeat")
    var isInfantSeat: Boolean? = null

    /**
     * number of persons able to use the asset
     * minimum: 1
     * @return persons
     */
    @get:Schema(description = "number of persons able to use the asset")
    @JsonProperty("persons")
    var persons: Int? = null

    /**
     * true indicates pets are allowed on asset
     * @return pets
     */
    @get:Schema(description = "true indicates pets are allowed on asset")
    @JsonProperty("pets")
    var isPets: Boolean? = null

    /**
     * way in which the asset is powered
     */
    enum class PropulsionEnum(private val value: String) {
        MUSCLE("MUSCLE"),
        ELECTRIC("ELECTRIC"),
        GASOLINE("GASOLINE"),
        DIESEL("DIESEL"),
        HYBRID("HYBRID"),
        LPG("LPG"),
        HYDROGEN("HYDROGEN");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): PropulsionEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * way in which the asset is powered
     * @return propulsion
     */
    @get:Schema(description = "way in which the asset is powered")
    @JsonProperty("propulsion")
    var propulsion: PropulsionEnum? = null

    /**
     * true indicates smoking is allowed on asset
     * @return smoking
     */
    @get:Schema(description = "true indicates smoking is allowed on asset")
    @JsonProperty("smoking")
    var isSmoking: Boolean? = null

    /**
     * percentage of charge available
     * minimum: 0
     * maximum: 100
     * @return stateOfCharge
     */
    @get:Schema(description = "percentage of charge available")
    @JsonProperty("stateOfCharge")
    var stateOfCharge: Int? = null

    /**
     * true indicates towing hook required
     * @return towingHook
     */
    @get:Schema(description = "true indicates towing hook required")
    @JsonProperty("towingHook")
    var isTowingHook: Boolean? = null

    /**
     * true indicates underground parking is allowed with asset
     * @return undergroundParking
     */
    @get:Schema(description = "true indicates underground parking is allowed with asset")
    @JsonProperty("undergroundParking")
    var isUndergroundParking: Boolean? = null

    /**
     * true indicates winter tires required
     * @return winterTires
     */
    @get:Schema(description = "true indicates winter tires required")
    @JsonProperty("winterTires")
    var isWinterTires: Boolean? = null

    /**
     * the maximum allowed speed for this asset (in km/h)
     * @return maxSpeed
     */
    @get:Schema(description = "the maximum allowed speed for this asset (in km/h)")
    @JsonProperty("maxSpeed")
    var maxSpeed: Int? = null

    /**
     * is a helmet required to operate this asset
     * @return helmetRequired
     */
    @get:Schema(description = "is a helmet required to operate this asset")
    @JsonProperty("helmetRequired")
    var isHelmetRequired = false

    /**
     * free text to describe asset, should match Content-Language
     * @return other
     */
    @get:Schema(description = "free text to describe asset, should match Content-Language")
    @JsonProperty("other")
    var other: String? = null

    /**
     * the number of doors of the vehicle. Return only when applicable
     * @return nrOfDoors
     */
    @get:Schema(description = "the number of doors of the vehicle. Return only when applicable")
    @JsonProperty("nrOfDoors")
    var nrOfDoors: Int? = null

    @JsonProperty("meta")
    private var meta: @Valid MutableMap<String, Any>? = null

    @JsonProperty("accessMethods")
    private var accessMethods: @Valid MutableList<AssetAccessMethods>? = null

    @JsonProperty("ancillaries")
    private var ancillaries: @Valid MutableList<Requirement>? = null
    fun name(name: String?): AssetProperties {
        this.name = name
        return this
    }

    fun location(location: Place?): AssetProperties {
        this.location = location
        return this
    }

    fun fuel(fuel: FuelEnum?): AssetProperties {
        this.fuel = fuel
        return this
    }

    fun energyLabel(energyLabel: EnergyLabelEnum?): AssetProperties {
        this.energyLabel = energyLabel
        return this
    }

    fun co2PerKm(co2PerKm: Float?): AssetProperties {
        this.co2PerKm = co2PerKm
        return this
    }

    fun brand(brand: String?): AssetProperties {
        this.brand = brand
        return this
    }

    fun model(model: String?): AssetProperties {
        this.model = model
        return this
    }

    fun buildingYear(buildingYear: Int?): AssetProperties {
        this.buildingYear = buildingYear
        return this
    }

    fun travelAbroad(travelAbroad: Boolean?): AssetProperties {
        isTravelAbroad = travelAbroad
        return this
    }

    fun airConditioning(airConditioning: Boolean?): AssetProperties {
        isAirConditioning = airConditioning
        return this
    }

    fun cabrio(cabrio: Boolean?): AssetProperties {
        isCabrio = cabrio
        return this
    }

    fun colour(colour: String?): AssetProperties {
        this.colour = colour
        return this
    }

    fun cargo(cargo: String?): AssetProperties {
        this.cargo = cargo
        return this
    }

    fun easyAccessibility(easyAccessibility: EasyAccessibilityEnum?): AssetProperties {
        this.easyAccessibility = easyAccessibility
        return this
    }

    fun gears(gears: Int?): AssetProperties {
        this.gears = gears
        return this
    }

    fun gearbox(gearbox: GearboxEnum?): AssetProperties {
        this.gearbox = gearbox
        return this
    }

    fun image(image: String?): AssetProperties {
        this.image = image
        return this
    }

    fun infantSeat(infantSeat: Boolean?): AssetProperties {
        isInfantSeat = infantSeat
        return this
    }

    fun persons(persons: Int?): AssetProperties {
        this.persons = persons
        return this
    }

    fun pets(pets: Boolean?): AssetProperties {
        isPets = pets
        return this
    }

    fun propulsion(propulsion: PropulsionEnum?): AssetProperties {
        this.propulsion = propulsion
        return this
    }

    fun smoking(smoking: Boolean?): AssetProperties {
        isSmoking = smoking
        return this
    }

    fun stateOfCharge(stateOfCharge: Int?): AssetProperties {
        this.stateOfCharge = stateOfCharge
        return this
    }

    fun towingHook(towingHook: Boolean?): AssetProperties {
        isTowingHook = towingHook
        return this
    }

    fun undergroundParking(undergroundParking: Boolean?): AssetProperties {
        isUndergroundParking = undergroundParking
        return this
    }

    fun winterTires(winterTires: Boolean?): AssetProperties {
        isWinterTires = winterTires
        return this
    }

    fun maxSpeed(maxSpeed: Int?): AssetProperties {
        this.maxSpeed = maxSpeed
        return this
    }

    fun helmetRequired(helmetRequired: Boolean): AssetProperties {
        isHelmetRequired = helmetRequired
        return this
    }

    fun other(other: String?): AssetProperties {
        this.other = other
        return this
    }

    fun nrOfDoors(nrOfDoors: Int?): AssetProperties {
        this.nrOfDoors = nrOfDoors
        return this
    }

    fun meta(meta: Map<String, Any>?): AssetProperties {
        this.meta = meta
        return this
    }

    fun putMetaItem(key: String, metaItem: Any): AssetProperties {
        if (meta == null) {
            meta = HashMap()
        }
        meta!![key] = metaItem
        return this
    }

    /**
     * this object can contain extra information about the type of asset. For instance values from the 'Woordenboek Reizigerskenmerken'. [https://github.com/efel85/TOMP-API/issues/17]. These values can also be used in the planning.
     * @return meta
     */
    @Schema(description = "this object can contain extra information about the type of asset. For instance values from the 'Woordenboek Reizigerskenmerken'. [https://github.com/efel85/TOMP-API/issues/17]. These values can also be used in the planning.")
    fun getMeta(): Map<String, Any>? {
        return meta
    }

    fun setMeta(meta: Map<String, Any>?) {
        this.meta = meta
    }

    fun accessMethods(accessMethods: List<AssetAccessMethods>?): AssetProperties {
        this.accessMethods = accessMethods
        return this
    }

    fun addAccessMethodsItem(accessMethodsItem: AssetAccessMethods): AssetProperties {
        if (accessMethods == null) {
            accessMethods = ArrayList()
        }
        accessMethods!!.add(accessMethodsItem)
        return this
    }

    /**
     * access method for trip execution. Data will be delivered in the response of /booking/{id}/events - COMMIT or /leg/{id}/events - PREPARE (preferred).
     * @return accessMethods
     */
    @Schema(description = "access method for trip execution. Data will be delivered in the response of /booking/{id}/events - COMMIT or /leg/{id}/events - PREPARE (preferred).")
    fun getAccessMethods(): @Valid MutableList<AssetAccessMethods>? {
        return accessMethods
    }

    fun setAccessMethods(accessMethods: List<AssetAccessMethods>?) {
        this.accessMethods = accessMethods
    }

    fun ancillaries(ancillaries: List<Requirement>?): AssetProperties {
        this.ancillaries = ancillaries
        return this
    }

    fun addAncillariesItem(ancillariesItem: Requirement): AssetProperties {
        if (ancillaries == null) {
            ancillaries = ArrayList()
        }
        ancillaries!!.add(ancillariesItem)
        return this
    }

    /**
     * Get ancillaries
     * @return ancillaries
     */
    @Schema(description = "")
    fun getAncillaries(): @Valid MutableList<Requirement>? {
        return ancillaries
    }

    fun setAncillaries(ancillaries: List<Requirement>?) {
        this.ancillaries = ancillaries
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val assetProperties = o as AssetProperties
        return name == assetProperties.name && location == assetProperties.location && fuel == assetProperties.fuel && energyLabel == assetProperties.energyLabel && co2PerKm == assetProperties.co2PerKm && brand == assetProperties.brand && model == assetProperties.model && buildingYear == assetProperties.buildingYear && isTravelAbroad == assetProperties.isTravelAbroad && isAirConditioning == assetProperties.isAirConditioning && isCabrio == assetProperties.isCabrio && colour == assetProperties.colour && cargo == assetProperties.cargo && easyAccessibility == assetProperties.easyAccessibility && gears == assetProperties.gears && gearbox == assetProperties.gearbox && image == assetProperties.image && isInfantSeat == assetProperties.isInfantSeat && persons == assetProperties.persons && isPets == assetProperties.isPets && propulsion == assetProperties.propulsion && isSmoking == assetProperties.isSmoking && stateOfCharge == assetProperties.stateOfCharge && isTowingHook == assetProperties.isTowingHook && isUndergroundParking == assetProperties.isUndergroundParking && isWinterTires == assetProperties.isWinterTires && maxSpeed == assetProperties.maxSpeed && isHelmetRequired == assetProperties.isHelmetRequired && other == assetProperties.other && nrOfDoors == assetProperties.nrOfDoors && meta == assetProperties.meta && accessMethods == assetProperties.accessMethods && ancillaries == assetProperties.ancillaries
    }

    override fun hashCode(): Int {
        return Objects.hash(
            name,
            location,
            fuel,
            energyLabel,
            co2PerKm,
            brand,
            model,
            buildingYear,
            isTravelAbroad,
            isAirConditioning,
            isCabrio,
            colour,
            cargo,
            easyAccessibility,
            gears,
            gearbox,
            image,
            isInfantSeat,
            persons,
            isPets,
            propulsion,
            isSmoking,
            stateOfCharge,
            isTowingHook,
            isUndergroundParking,
            isWinterTires,
            maxSpeed,
            isHelmetRequired,
            other,
            nrOfDoors,
            meta,
            accessMethods,
            ancillaries
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class AssetProperties {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    location: ").append(toIndentedString(location)).append("\n")
        sb.append("    fuel: ").append(toIndentedString(fuel)).append("\n")
        sb.append("    energyLabel: ").append(toIndentedString(energyLabel)).append("\n")
        sb.append("    co2PerKm: ").append(toIndentedString(co2PerKm)).append("\n")
        sb.append("    brand: ").append(toIndentedString(brand)).append("\n")
        sb.append("    model: ").append(toIndentedString(model)).append("\n")
        sb.append("    buildingYear: ").append(toIndentedString(buildingYear)).append("\n")
        sb.append("    travelAbroad: ").append(toIndentedString(isTravelAbroad)).append("\n")
        sb.append("    airConditioning: ").append(toIndentedString(isAirConditioning)).append("\n")
        sb.append("    cabrio: ").append(toIndentedString(isCabrio)).append("\n")
        sb.append("    colour: ").append(toIndentedString(colour)).append("\n")
        sb.append("    cargo: ").append(toIndentedString(cargo)).append("\n")
        sb.append("    easyAccessibility: ").append(toIndentedString(easyAccessibility)).append("\n")
        sb.append("    gears: ").append(toIndentedString(gears)).append("\n")
        sb.append("    gearbox: ").append(toIndentedString(gearbox)).append("\n")
        sb.append("    image: ").append(toIndentedString(image)).append("\n")
        sb.append("    infantSeat: ").append(toIndentedString(isInfantSeat)).append("\n")
        sb.append("    persons: ").append(toIndentedString(persons)).append("\n")
        sb.append("    pets: ").append(toIndentedString(isPets)).append("\n")
        sb.append("    propulsion: ").append(toIndentedString(propulsion)).append("\n")
        sb.append("    smoking: ").append(toIndentedString(isSmoking)).append("\n")
        sb.append("    stateOfCharge: ").append(toIndentedString(stateOfCharge)).append("\n")
        sb.append("    towingHook: ").append(toIndentedString(isTowingHook)).append("\n")
        sb.append("    undergroundParking: ").append(toIndentedString(isUndergroundParking)).append("\n")
        sb.append("    winterTires: ").append(toIndentedString(isWinterTires)).append("\n")
        sb.append("    maxSpeed: ").append(toIndentedString(maxSpeed)).append("\n")
        sb.append("    helmetRequired: ").append(toIndentedString(isHelmetRequired)).append("\n")
        sb.append("    other: ").append(toIndentedString(other)).append("\n")
        sb.append("    nrOfDoors: ").append(toIndentedString(nrOfDoors)).append("\n")
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n")
        sb.append("    accessMethods: ").append(toIndentedString(accessMethods)).append("\n")
        sb.append("    ancillaries: ").append(toIndentedString(ancillaries)).append("\n")
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
