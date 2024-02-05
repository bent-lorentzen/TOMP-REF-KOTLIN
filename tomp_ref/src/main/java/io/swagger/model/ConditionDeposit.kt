package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * in case the TO demands a deposit before usage. Requesting and refunding should be done using the /payment/claim-extra-costs endpoint.
 */
@Schema(description = "in case the TO demands a deposit before usage. Requesting and refunding should be done using the /payment/claim-extra-costs endpoint.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class ConditionDeposit : Condition(), OneOfassetTypeConditionsItems, OneOflegConditionsItems {
    /**
     * This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT
     * minimum: 0
     * @return amount
     */
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
    @get:Schema(example = "8.95", description = "")
    @JsonProperty("amountExVat")
    var amountExVat: Float? = null

    /**
     * ISO 4217 currency code
     * @return currencyCode
     */
    @get:Schema(description = "ISO 4217 currency code")
    @JsonProperty("currencyCode")
    var currencyCode: String? = null

    /**
     * value added tax rate (percentage of amount)
     * minimum: 0
     * @return vatRate
     */
    @get:Schema(example = "21", description = "value added tax rate (percentage of amount)")
    @JsonProperty("vatRate")
    var vatRate: Float? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return vatCountryCode
     */
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("vatCountryCode")
    var vatCountryCode: String? = null
    fun amount(amount: Float?): ConditionDeposit {
        this.amount = amount
        return this
    }

    fun amountExVat(amountExVat: Float?): ConditionDeposit {
        this.amountExVat = amountExVat
        return this
    }

    fun currencyCode(currencyCode: String?): ConditionDeposit {
        this.currencyCode = currencyCode
        return this
    }

    fun vatRate(vatRate: Float?): ConditionDeposit {
        this.vatRate = vatRate
        return this
    }

    fun vatCountryCode(vatCountryCode: String?): ConditionDeposit {
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
        val conditionDeposit = o as ConditionDeposit
        return amount == conditionDeposit.amount && amountExVat == conditionDeposit.amountExVat && currencyCode == conditionDeposit.currencyCode && vatRate == conditionDeposit.vatRate && vatCountryCode == conditionDeposit.vatCountryCode &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(amount, amountExVat, currencyCode, vatRate, vatCountryCode, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ConditionDeposit {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
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
