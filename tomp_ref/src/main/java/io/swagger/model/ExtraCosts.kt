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
 * Costs that the TO is charging the MP; credits are negative. Other amounts should be positive
 */
@Schema(description = "Costs that the TO is charging the MP; credits are negative. Other amounts should be positive")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class ExtraCosts : AmountOfMoney() {
    /**
     * Get category
     * @return category
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("category")
    var category: JournalCategory? = null

    /**
     * free text to describe the extra costs. Mandatory in case of 'OTHER', should match Content-Language
     * @return description
     */
    @JvmField
    @get:Schema(
        required = true,
        description = "free text to describe the extra costs. Mandatory in case of 'OTHER', should match Content-Language"
    )
    @JsonProperty("description")
    var description: String? = null

    /**
     * e.g. number of litres, number of kilowatthour, etc
     * minimum: 0
     * @return number
     */
    @get:Schema(description = "e.g. number of litres, number of kilowatthour, etc")
    @JsonProperty("number")
    var number: Float? = null

    /**
     * Gets or Sets numberType
     */
    enum class NumberTypeEnum(private val value: String) {
        LITER("LITER"),
        KILOWATTHOUR("KILOWATTHOUR"),
        CO2_COMPENSATION("CO2_COMPENSATION"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): NumberTypeEnum? {
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
     * Get numberType
     * @return numberType
     */
    @get:Schema(description = "")
    @JsonProperty("numberType")
    var numberType: NumberTypeEnum? = null

    /**
     * Get account
     * @return account
     */
    @get:Schema(description = "")
    @JsonProperty("account")
    var account: BankAccount? = null

    @JsonProperty("meta")
    private var meta: @Valid MutableMap<String, Any>? = null
    fun category(category: JournalCategory?): ExtraCosts {
        this.category = category
        return this
    }

    fun description(description: String?): ExtraCosts {
        this.description = description
        return this
    }

    fun number(number: Float?): ExtraCosts {
        this.number = number
        return this
    }

    fun numberType(numberType: NumberTypeEnum?): ExtraCosts {
        this.numberType = numberType
        return this
    }

    fun account(account: BankAccount?): ExtraCosts {
        this.account = account
        return this
    }

    fun meta(meta: Map<String, Any>?): ExtraCosts {
        this.meta = meta
        return this
    }

    fun putMetaItem(key: String, metaItem: Any): ExtraCosts {
        if (meta == null) {
            meta = HashMap()
        }
        meta!![key] = metaItem
        return this
    }

    /**
     * Arbitrary metadata that a TO can add, like voucher codes
     * @return meta
     */
    @Schema(description = "Arbitrary metadata that a TO can add, like voucher codes")
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
        val extraCosts = o as ExtraCosts
        return this.category == extraCosts.category && description == extraCosts.description && number == extraCosts.number && numberType == extraCosts.numberType && account == extraCosts.account && meta == extraCosts.meta &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(category, description, number, numberType, account, meta, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ExtraCosts {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    category: ").append(toIndentedString(category)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    numberType: ").append(toIndentedString(numberType)).append("\n")
        sb.append("    account: ").append(toIndentedString(account)).append("\n")
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
