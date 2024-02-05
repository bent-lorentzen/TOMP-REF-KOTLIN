package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * ConditionRequireBookingData
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
class ConditionRequireBookingData : Condition(), OneOfassetTypeConditionsItems, OneOflegConditionsItems {
    /**
     * Gets or Sets requiredFields
     */
    enum class RequiredFieldsEnum(private val value: String) {
        FROM_ADDRESS("FROM_ADDRESS"),
        TO_ADDRESS("TO_ADDRESS"),
        BIRTHDATE("BIRTHDATE"),
        EMAIL("EMAIL"),
        PERSONAL_ADDRESS("PERSONAL_ADDRESS"),
        PHONE_NUMBERS("PHONE_NUMBERS"),
        LICENSES("LICENSES"),
        BANK_CARDS("BANK_CARDS"),
        DISCOUNT_CARDS("DISCOUNT_CARDS"),
        TRAVEL_CARDS("TRAVEL_CARDS"),
        ID_CARDS("ID_CARDS"),
        CREDIT_CARDS("CREDIT_CARDS"),
        NAME("NAME"),
        AGE("AGE"),
        BLOCKCHAIN_CLAIMS("BLOCKCHAIN_CLAIMS");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): RequiredFieldsEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    @JsonProperty("requiredFields")
    private var requiredFields: @Valid MutableList<RequiredFieldsEnum>? = ArrayList()

    @JsonProperty("claims")
    private var claims: @Valid MutableList<String>? = null
    fun requiredFields(requiredFields: List<RequiredFieldsEnum>?): ConditionRequireBookingData {
        this.requiredFields = requiredFields
        return this
    }

    fun addRequiredFieldsItem(requiredFieldsItem: RequiredFieldsEnum): ConditionRequireBookingData {
        requiredFields!!.add(requiredFieldsItem)
        return this
    }

    /**
     * Get requiredFields
     * @return requiredFields
     */
    @Schema(required = true, description = "")
    fun getRequiredFields(): @NotNull MutableList<RequiredFieldsEnum>? {
        return requiredFields
    }

    fun setRequiredFields(requiredFields: List<RequiredFieldsEnum>?) {
        this.requiredFields = requiredFields
    }

    fun claims(claims: List<String>?): ConditionRequireBookingData {
        this.claims = claims
        return this
    }

    fun addClaimsItem(claimsItem: String): ConditionRequireBookingData {
        if (claims == null) {
            claims = ArrayList()
        }
        claims!!.add(claimsItem)
        return this
    }

    /**
     * when in the 'requiredFields' array 'BLOCKCHAIN_CLAIMS' is specified, in this array claims can be specified. On the WIKI page, the known ones are enlisted, but this list isn't finalized yet. https://github.com/TOMP-WG/TOMP-API/wiki/Blockchain---Verifiable-credentials
     * @return claims
     */
    @Schema(description = "when in the 'requiredFields' array 'BLOCKCHAIN_CLAIMS' is specified, in this array claims can be specified. On the WIKI page, the known ones are enlisted, but this list isn't finalized yet. https://github.com/TOMP-WG/TOMP-API/wiki/Blockchain---Verifiable-credentials")
    fun getClaims(): List<String>? {
        return claims
    }

    fun setClaims(claims: List<String>?) {
        this.claims = claims
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val conditionRequireBookingData = o as ConditionRequireBookingData
        return requiredFields == conditionRequireBookingData.requiredFields && claims == conditionRequireBookingData.claims &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(requiredFields, claims, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ConditionRequireBookingData {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    requiredFields: ").append(toIndentedString(requiredFields)).append("\n")
        sb.append("    claims: ").append(toIndentedString(claims)).append("\n")
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
