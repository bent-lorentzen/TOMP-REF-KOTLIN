package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.LocalDate
import java.util.Objects
import javax.annotation.Generated

/**
 * Any kind of card that isn&#x27;t a license, only provide the cards that are required
 */
@Schema(description = "Any kind of card that isn't a license, only provide the cards that are required")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Card : CardType() {
    /**
     * description of the card
     * @return cardDescription
     */
    @get:Schema(description = "description of the card")
    @JsonProperty("cardDescription")
    var cardDescription: String? = null

    /**
     * number of the card, like ID number, credit card or bank account number
     * @return cardNumber
     */
    @JvmField
    @get:Schema(
        required = true,
        description = "number of the card, like ID number, credit card or bank account number"
    )
    @JsonProperty("cardNumber")
    var cardNumber: String? = null

    /**
     * additional number, like CVC code or IBAN code
     * @return cardAdditionalNumber
     */
    @get:Schema(description = "additional number, like CVC code or IBAN code")
    @JsonProperty("cardAdditionalNumber")
    var cardAdditionalNumber: String? = null

    /**
     * Get validUntil
     * @return validUntil
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("validUntil")
    var validUntil: LocalDate? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return country
     */
    @JvmField
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("country")
    var country: String? = null
    fun cardDescription(cardDescription: String?): Card {
        this.cardDescription = cardDescription
        return this
    }

    fun cardNumber(cardNumber: String?): Card {
        this.cardNumber = cardNumber
        return this
    }

    fun cardAdditionalNumber(cardAdditionalNumber: String?): Card {
        this.cardAdditionalNumber = cardAdditionalNumber
        return this
    }

    fun validUntil(validUntil: LocalDate?): Card {
        this.validUntil = validUntil
        return this
    }

    fun country(country: String?): Card {
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
        val card = o as Card
        return cardDescription == card.cardDescription && cardNumber == card.cardNumber && cardAdditionalNumber == card.cardAdditionalNumber && validUntil == card.validUntil && country == card.country &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(cardDescription, cardNumber, cardAdditionalNumber, validUntil, country, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Card {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    cardDescription: ").append(toIndentedString(cardDescription)).append("\n")
        sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n")
        sb.append("    cardAdditionalNumber: ").append(toIndentedString(cardAdditionalNumber)).append("\n")
        sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n")
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
