package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * A generic description of a traveler, not including any identifying information
 */
@Schema(description = "A generic description of a traveler, not including any identifying information")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
open class Traveler {
    /**
     * Whether this traveler's identity and properties have been verified by the MaaS provider
     * @return isValidated
     */
    @get:Schema(description = "Whether this traveler's identity and properties have been verified by the MaaS provider")
    @JsonProperty("isValidated")
    var isIsValidated: Boolean? = null
        private set

    /**
     * Age of the traveler, may be approximate
     * @return age
     */
    @get:Schema(description = "Age of the traveler, may be approximate")
    @JsonProperty("age")
    var age: Int? = null

    /**
     * reference number of the traveler. This number could be used to refer to in the planning result.
     * @return referenceNumber
     */
    @get:Schema(description = "reference number of the traveler. This number could be used to refer to in the planning result.")
    @JsonProperty("referenceNumber")
    var referenceNumber: String? = null

    @JsonProperty("cardTypes")
    private var cardTypes: @Valid MutableList<CardType>? = null

    @JsonProperty("licenseTypes")
    private var licenseTypes: @Valid MutableList<LicenseType>? = null

    @JsonProperty("requirements")
    private var requirements = Requirements()

    /**
     * identifier for this traveler in the personal data store. This identifier can be used to get personal information from the provider specified in the \"knownIdentifierProvider\"
     * @return knownIdentifier
     */
    @get:Schema(description = "identifier for this traveler in the personal data store. This identifier can be used to get personal information from the provider specified in the \"knownIdentifierProvider\"")
    @JsonProperty("knownIdentifier")
    var knownIdentifier: String? = null

    /**
     * provider for personal information. Can be a URI or identifier.
     * @return knownIdentifierProvider
     */
    @get:Schema(description = "provider for personal information. Can be a URI or identifier.")
    @JsonProperty("knownIdentifierProvider")
    var knownIdentifierProvider: String? = null
    fun isValidated(isValidated: Boolean?): Traveler {
        isIsValidated = isValidated
        return this
    }

    fun setIsValidated(isValidated: Boolean?) {
        isIsValidated = isValidated
    }

    fun age(age: Int?): Traveler {
        this.age = age
        return this
    }

    fun referenceNumber(referenceNumber: String?): Traveler {
        this.referenceNumber = referenceNumber
        return this
    }

    fun cardTypes(cardTypes: List<CardType>?): Traveler {
        this.cardTypes = cardTypes
        return this
    }

    fun addCardTypesItem(cardTypesItem: CardType): Traveler {
        if (cardTypes == null) {
            cardTypes = ArrayList()
        }
        cardTypes!!.add(cardTypesItem)
        return this
    }

    /**
     * The kind of cards this traveler possesses
     * @return cardTypes
     */
    @Schema(description = "The kind of cards this traveler possesses")
    fun getCardTypes(): @Valid MutableList<CardType>? {
        return cardTypes
    }

    fun setCardTypes(cardTypes: List<CardType>?) {
        this.cardTypes = cardTypes
    }

    fun licenseTypes(licenseTypes: List<LicenseType>?): Traveler {
        this.licenseTypes = licenseTypes
        return this
    }

    fun addLicenseTypesItem(licenseTypesItem: LicenseType): Traveler {
        if (licenseTypes == null) {
            licenseTypes = ArrayList()
        }
        licenseTypes!!.add(licenseTypesItem)
        return this
    }

    /**
     * The kind of licenses this traveler possesses
     * @return licenseTypes
     */
    @Schema(description = "The kind of licenses this traveler possesses")
    fun getLicenseTypes(): @Valid MutableList<LicenseType>? {
        return licenseTypes
    }

    fun setLicenseTypes(licenseTypes: List<LicenseType>?) {
        this.licenseTypes = licenseTypes
    }

    fun requirements(requirements: Requirements): Traveler {
        this.requirements = requirements
        return this
    }

    /**
     * Requirements from the end user side.
     * @return requirements
     */
    @Schema(description = "Requirements from the end user side.")
    fun getRequirements(): @Valid Requirements? {
        return requirements
    }

    fun setRequirements(requirements: Requirements) {
        this.requirements = requirements
    }

    fun knownIdentifier(knownIdentifier: String?): Traveler {
        this.knownIdentifier = knownIdentifier
        return this
    }

    fun knownIdentifierProvider(knownIdentifierProvider: String?): Traveler {
        this.knownIdentifierProvider = knownIdentifierProvider
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val traveler = o as Traveler
        return isIsValidated == traveler.isIsValidated && age == traveler.age && referenceNumber == traveler.referenceNumber && cardTypes == traveler.cardTypes && licenseTypes == traveler.licenseTypes && requirements == traveler.requirements && knownIdentifier == traveler.knownIdentifier && knownIdentifierProvider == traveler.knownIdentifierProvider
    }

    override fun hashCode(): Int {
        return Objects.hash(isIsValidated, age, referenceNumber, cardTypes, licenseTypes, requirements, knownIdentifier, knownIdentifierProvider)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Traveler {\n")
        sb.append("    isValidated: ").append(toIndentedString(isIsValidated)).append("\n")
        sb.append("    age: ").append(toIndentedString(age)).append("\n")
        sb.append("    referenceNumber: ").append(toIndentedString(referenceNumber)).append("\n")
        sb.append("    cardTypes: ").append(toIndentedString(cardTypes)).append("\n")
        sb.append("    licenseTypes: ").append(toIndentedString(licenseTypes)).append("\n")
        sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n")
        sb.append("    knownIdentifier: ").append(toIndentedString(knownIdentifier)).append("\n")
        sb.append("    knownIdentifierProvider: ").append(toIndentedString(knownIdentifierProvider)).append("\n")
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
