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
 * this describes a part of the fare (or discount). It contains a for instance the startup costs (fixed) or the flex part (e.g. 1.25 EUR per 2.0 MILES). The amount is tax included. In case of discounts, the values are negative. With &#x27;MAX&#x27; you can specify e.g. a maximum of 15 euro per day. Percentage is mainly added for discounts. The &#x60;scale&#x60; properties create the ability to communicate scales (e.g. the first 4 kilometers you&#x27;ve to pay EUR 0.35 per kilometer, the kilometers 4 until 8 EUR 0.50 and above it EUR 0.80 per kilometer).
 */
@Schema(description = "this describes a part of the fare (or discount). It contains a for instance the startup costs (fixed) or the flex part (e.g. 1.25 EUR per 2.0 MILES). The amount is tax included. In case of discounts, the values are negative. With 'MAX' you can specify e.g. a maximum of 15 euro per day. Percentage is mainly added for discounts. The `scale` properties create the ability to communicate scales (e.g. the first 4 kilometers you've to pay EUR 0.35 per kilometer, the kilometers 4 until 8 EUR 0.50 and above it EUR 0.80 per kilometer).")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class FarePart : AmountOfMoney() {
    /**
     * type of fare part. If there is only one farepart and this field is missing, it should be assumed it is 'FIXED'. In all other situations this field is mandatory.
     */
    enum class TypeEnum(private val value: String) {
        FIXED("FIXED"),
        FLEX("FLEX"),
        MAX("MAX");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): TypeEnum? {
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
     * type of fare part. If there is only one farepart and this field is missing, it should be assumed it is 'FIXED'. In all other situations this field is mandatory.
     * @return type
     */
    @JvmField
    @get:Schema(description = "type of fare part. If there is only one farepart and this field is missing, it should be assumed it is 'FIXED'. In all other situations this field is mandatory.")
    @JsonProperty("type")
    var type: TypeEnum? = null

    /**
     * is this the default price or is this an additional part (discount, price surge). In case of a DISCOUNT, the amount must always be negative and in case of SURGE it must be positive. This also means, that when you're working with discounts or surges, you have to deliver 2 fareparts, one for the default price and one for the discount/surge. This can be used in combination with as well the fixed price parts as with the flex price parts.
     */
    enum class KindEnum(private val value: String) {
        DEFAULT("DEFAULT"),
        DISCOUNT("DISCOUNT"),
        SURGE("SURGE");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): KindEnum? {
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
     * is this the default price or is this an additional part (discount, price surge). In case of a DISCOUNT, the amount must always be negative and in case of SURGE it must be positive. This also means, that when you're working with discounts or surges, you have to deliver 2 fareparts, one for the default price and one for the discount/surge. This can be used in combination with as well the fixed price parts as with the flex price parts.
     * @return kind
     */
    @get:Schema(description = "is this the default price or is this an additional part (discount, price surge). In case of a DISCOUNT, the amount must always be negative and in case of SURGE it must be positive. This also means, that when you're working with discounts or surges, you have to deliver 2 fareparts, one for the default price and one for the discount/surge. This can be used in combination with as well the fixed price parts as with the flex price parts.")
    @JsonProperty("kind")
    var kind: KindEnum? = null

    /**
     * in case of 'FLEX' mandatory, otherwise not allowed. E.g. 0.5 EUR per HOUR
     */
    enum class UnitTypeEnum(private val value: String) {
        KM("KM"),
        SECOND("SECOND"),
        MINUTE("MINUTE"),
        HOUR("HOUR"),
        MILE("MILE"),
        PERCENTAGE("PERCENTAGE");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): UnitTypeEnum? {
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
     * in case of 'FLEX' mandatory, otherwise not allowed. E.g. 0.5 EUR per HOUR
     * @return unitType
     */
    @JvmField
    @get:Schema(description = "in case of 'FLEX' mandatory, otherwise not allowed. E.g. 0.5 EUR per HOUR")
    @JsonProperty("unitType")
    var unitType: UnitTypeEnum? = null

    /**
     * the number of km, seconds etc. Mandatory when the type is 'FLEX', otherwise not allowed. In case of 0.5 EUR per 15 MINUTES, `units` should contain 15 and `unitType` MINUTES.
     * minimum: 0
     * @return units
     */
    @JvmField
    @get:Schema(description = "the number of km, seconds etc. Mandatory when the type is 'FLEX', otherwise not allowed. In case of 0.5 EUR per 15 MINUTES, `units` should contain 15 and `unitType` MINUTES.")
    @JsonProperty("units")
    var units: Float? = null

    /**
     * in case of scaling, this is the bottom value (f.x. in the first hour 3 CAD, the `scaleFrom` should contain 0 and the `scaleType` HOUR). When `scaleTo` is used, but this field is missing, it should be assumed it is a 0.
     * minimum: 0
     * @return scaleFrom
     */
    @JvmField
    @get:Schema(description = "in case of scaling, this is the bottom value (f.x. in the first hour 3 CAD, the `scaleFrom` should contain 0 and the `scaleType` HOUR). When `scaleTo` is used, but this field is missing, it should be assumed it is a 0.")
    @JsonProperty("scaleFrom")
    var scaleFrom: Float? = null

    /**
     * the upper value of the scale (f.x. 3 CAD in the first hour, this field should contain 1, `scaleFrom` 0 and `scaleType` HOUR)
     * minimum: 0
     * @return scaleTo
     */
    @JvmField
    @get:Schema(description = "the upper value of the scale (f.x. 3 CAD in the first hour, this field should contain 1, `scaleFrom` 0 and `scaleType` HOUR)")
    @JsonProperty("scaleTo")
    var scaleTo: Float? = null

    /**
     * Gets or Sets scaleType
     */
    enum class ScaleTypeEnum(private val value: String) {
        KM("KM"),
        MILE("MILE"),
        HOUR("HOUR"),
        MINUTE("MINUTE");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): ScaleTypeEnum? {
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
     * Get scaleType
     * @return scaleType
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("scaleType")
    var scaleType: ScaleTypeEnum? = null

    /**
     * an optional description of this fare part.
     * @return name
     */
    @get:Schema(description = "an optional description of this fare part.")
    @JsonProperty("name")
    var name: String? = null

    /**
     * class of this fare part. Could be FARE or ANCILLARY
     */
    enum class PropertyClassEnum(private val value: String) {
        FARE("FARE"),
        ANCILLARY("ANCILLARY");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): PropertyClassEnum? {
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
     * class of this fare part. Could be FARE or ANCILLARY
     * @return propertyClass
     */
    @get:Schema(description = "class of this fare part. Could be FARE or ANCILLARY")
    @JsonProperty("class")
    var propertyClass = PropertyClassEnum.FARE

    /**
     * The minimum price, in the same currency as amount. Place in `amount` the most likely value.
     * minimum: 0
     * @return minimumAmount
     */
    @get:Schema(
        example = "9",
        description = "The minimum price, in the same currency as amount. Place in `amount` the most likely value."
    )
    @JsonProperty("minimumAmount")
    var minimumAmount: Float? = null

    /**
     * The minimum price, in the same currency as amount. Place in `amount` the most likely value.
     * minimum: 0
     * @return maximumAmount
     */
    @get:Schema(
        example = "11",
        description = "The minimum price, in the same currency as amount. Place in `amount` the most likely value."
    )
    @JsonProperty("maximumAmount")
    var maximumAmount: Float? = null

    @JsonProperty("meta")
    private var meta: @Valid MutableMap<String, Any>? = null
    fun type(type: TypeEnum?): FarePart {
        this.type = type
        return this
    }

    fun kind(kind: KindEnum?): FarePart {
        this.kind = kind
        return this
    }

    fun unitType(unitType: UnitTypeEnum?): FarePart {
        this.unitType = unitType
        return this
    }

    fun units(units: Float?): FarePart {
        this.units = units
        return this
    }

    fun scaleFrom(scaleFrom: Float?): FarePart {
        this.scaleFrom = scaleFrom
        return this
    }

    fun scaleTo(scaleTo: Float?): FarePart {
        this.scaleTo = scaleTo
        return this
    }

    fun scaleType(scaleType: ScaleTypeEnum?): FarePart {
        this.scaleType = scaleType
        return this
    }

    fun name(name: String?): FarePart {
        this.name = name
        return this
    }

    fun propertyClass(propertyClass: PropertyClassEnum): FarePart {
        this.propertyClass = propertyClass
        return this
    }

    fun minimumAmount(minimumAmount: Float?): FarePart {
        this.minimumAmount = minimumAmount
        return this
    }

    fun maximumAmount(maximumAmount: Float?): FarePart {
        this.maximumAmount = maximumAmount
        return this
    }

    fun meta(meta: Map<String, Any>?): FarePart {
        this.meta = meta
        return this
    }

    fun putMetaItem(key: String, metaItem: Any): FarePart {
        if (meta == null) {
            meta = HashMap()
        }
        meta!![key] = metaItem
        return this
    }

    /**
     * Get meta
     * @return meta
     */
    @Schema(description = "")
    fun getMeta(): Map<String, Any>? {
        return meta
    }

    fun setMeta(meta: Map<String, Any>?) {
        this.meta = meta
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val farePart = o as FarePart
        return type == farePart.type && kind == farePart.kind && unitType == farePart.unitType && units == farePart.units && scaleFrom == farePart.scaleFrom && scaleTo == farePart.scaleTo && scaleType == farePart.scaleType && name == farePart.name && propertyClass == farePart.propertyClass && minimumAmount == farePart.minimumAmount && maximumAmount == farePart.maximumAmount && meta == farePart.meta &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            type,
            kind,
            unitType,
            units,
            scaleFrom,
            scaleTo,
            scaleType,
            name,
            propertyClass,
            minimumAmount,
            maximumAmount,
            meta,
            super.hashCode()
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class FarePart {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    kind: ").append(toIndentedString(kind)).append("\n")
        sb.append("    unitType: ").append(toIndentedString(unitType)).append("\n")
        sb.append("    units: ").append(toIndentedString(units)).append("\n")
        sb.append("    scaleFrom: ").append(toIndentedString(scaleFrom)).append("\n")
        sb.append("    scaleTo: ").append(toIndentedString(scaleTo)).append("\n")
        sb.append("    scaleType: ").append(toIndentedString(scaleType)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    propertyClass: ").append(toIndentedString(propertyClass)).append("\n")
        sb.append("    minimumAmount: ").append(toIndentedString(minimumAmount)).append("\n")
        sb.append("    maximumAmount: ").append(toIndentedString(maximumAmount)).append("\n")
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n")
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
