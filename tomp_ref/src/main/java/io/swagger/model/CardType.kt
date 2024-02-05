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
 * A generic description of a card, asset class and acceptors is only allowed for DISCOUNT/TRAVEL/OTHER cards
 */
@Schema(description = "A generic description of a card, asset class and acceptors is only allowed for DISCOUNT/TRAVEL/OTHER cards")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
open class CardType {
    /**
     * The broad category of card
     */
    enum class TypeEnum(private val value: String) {
        ID("ID"),
        DISCOUNT("DISCOUNT"),
        TRAVEL("TRAVEL"),
        BANK("BANK"),
        CREDIT("CREDIT"),
        PASSPORT("PASSPORT"),
        OTHER("OTHER");

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
     * The broad category of card
     * @return type
     */
    @JvmField
    @get:Schema(required = true, description = "The broad category of card")
    @JsonProperty("type")
    var type: TypeEnum? = null

    /**
     * For use in case of OTHER. Can be used in bilateral agreements.
     * @return subType
     */
    @get:Schema(description = "For use in case of OTHER. Can be used in bilateral agreements.")
    @JsonProperty("subType")
    var subType: String? = null

    /**
     * Get assetClass
     * @return assetClass
     */
    @get:Schema(description = "")
    @JsonProperty("assetClass")
    var assetClass: AssetClass? = null

    @JsonProperty("acceptors")
    private var acceptors: @Valid MutableList<String>? = null
    fun type(type: TypeEnum?): CardType {
        this.type = type
        return this
    }

    fun subType(subType: String?): CardType {
        this.subType = subType
        return this
    }

    fun assetClass(assetClass: AssetClass?): CardType {
        this.assetClass = assetClass
        return this
    }

    fun acceptors(acceptors: List<String>?): CardType {
        this.acceptors = acceptors
        return this
    }

    fun addAcceptorsItem(acceptorsItem: String): CardType {
        if (acceptors == null) {
            acceptors = ArrayList()
        }
        acceptors!!.add(acceptorsItem)
        return this
    }

    /**
     * references to accepting parties, only if applicable
     * @return acceptors
     */
    @Schema(description = "references to accepting parties, only if applicable")
    fun getAcceptors(): List<String>? {
        return acceptors
    }

    fun setAcceptors(acceptors: List<String>?) {
        this.acceptors = acceptors
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val cardType = o as CardType
        return type == cardType.type && subType == cardType.subType && assetClass == cardType.assetClass && acceptors == cardType.acceptors
    }

    override fun hashCode(): Int {
        return Objects.hash(type, subType, assetClass, acceptors)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CardType {\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    subType: ").append(toIndentedString(subType)).append("\n")
        sb.append("    assetClass: ").append(toIndentedString(assetClass)).append("\n")
        sb.append("    acceptors: ").append(toIndentedString(acceptors)).append("\n")
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
