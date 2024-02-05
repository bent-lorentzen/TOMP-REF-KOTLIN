package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * BankAccount
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class BankAccount {
    /**
     * account name
     * @return name
     */
    @get:Schema(description = "account name")
    @JsonProperty("name")
    var name: String? = null

    /**
     * account number
     * @return number
     */
    @get:Schema(description = "account number")
    @JsonProperty("number")
    var number: String? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return country
     */
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("country")
    var country: String? = null

    /**
     * bank identification, like BIC code
     * @return bankIdentification
     */
    @get:Schema(description = "bank identification, like BIC code")
    @JsonProperty("bankIdentification")
    var bankIdentification: String? = null
    fun name(name: String?): BankAccount {
        this.name = name
        return this
    }

    fun number(number: String?): BankAccount {
        this.number = number
        return this
    }

    fun country(country: String?): BankAccount {
        this.country = country
        return this
    }

    fun bankIdentification(bankIdentification: String?): BankAccount {
        this.bankIdentification = bankIdentification
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val bankAccount = o as BankAccount
        return name == bankAccount.name && number == bankAccount.number && country == bankAccount.country && bankIdentification == bankAccount.bankIdentification
    }

    override fun hashCode(): Int {
        return Objects.hash(name, number, country, bankIdentification)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class BankAccount {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    country: ").append(toIndentedString(country)).append("\n")
        sb.append("    bankIdentification: ").append(toIndentedString(bankIdentification)).append("\n")
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
