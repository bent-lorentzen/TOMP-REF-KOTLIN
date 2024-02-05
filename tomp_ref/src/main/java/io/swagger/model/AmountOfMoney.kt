package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * AmountOfMoney
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
open class AmountOfMoney {
    /**
     * This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT
     * minimum: 0
     * @return amount
     */
    @JvmField
    @get:Schema(
        example = "9.95",
        description = "This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT"
    )
    @JsonProperty("amount")
    var amount: Float? = null

    /**
     * Get amountExVat
     * minimum: 0
     * @return amountExVat
     */
    @JvmField
    @get:Schema(example = "8.95", description = "")
    @JsonProperty("amountExVat")
    var amountExVat: Float? = null

    /**
     * ISO 4217 currency code
     * @return currencyCode
     */
    @JvmField
    @get:Schema(description = "ISO 4217 currency code")
    @JsonProperty("currencyCode")
    var currencyCode: String? = null

    /**
     * value added tax rate (percentage of amount)
     * minimum: 0
     * @return vatRate
     */
    @JvmField
    @get:Schema(example = "21", description = "value added tax rate (percentage of amount)")
    @JsonProperty("vatRate")
    var vatRate: Float? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return vatCountryCode
     */
    @JvmField
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("vatCountryCode")
    var vatCountryCode: String? = null
    fun amount(amount: Float?): AmountOfMoney {
        this.amount = amount
        return this
    }

    fun amountExVat(amountExVat: Float?): AmountOfMoney {
        this.amountExVat = amountExVat
        return this
    }

    fun currencyCode(currencyCode: String?): AmountOfMoney {
        this.currencyCode = currencyCode
        return this
    }

    fun vatRate(vatRate: Float?): AmountOfMoney {
        this.vatRate = vatRate
        return this
    }

    fun vatCountryCode(vatCountryCode: String?): AmountOfMoney {
        this.vatCountryCode = vatCountryCode
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val amountOfMoney = o as AmountOfMoney
        return amount == amountOfMoney.amount && amountExVat == amountOfMoney.amountExVat && currencyCode == amountOfMoney.currencyCode && vatRate == amountOfMoney.vatRate && vatCountryCode == amountOfMoney.vatCountryCode
    }

    override fun hashCode(): Int {
        return Objects.hash(amount, amountExVat, currencyCode, vatRate, vatCountryCode)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class AmountOfMoney {\n")
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n")
        sb.append("    amountExVat: ").append(toIndentedString(amountExVat)).append("\n")
        sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n")
        sb.append("    vatRate: ").append(toIndentedString(vatRate)).append("\n")
        sb.append("    vatCountryCode: ").append(toIndentedString(vatCountryCode)).append("\n")
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
