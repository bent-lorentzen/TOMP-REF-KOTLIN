package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.LocalDate
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * A MaaS user that wishes to make a booking, only use the fields required by booking conditions
 */
@Schema(description = "A MaaS user that wishes to make a booking, only use the fields required by booking conditions")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Customer : Traveler() {
    /**
     * The identifier MaaS uses to identify the customer
     * @return id
     */
    @get:Schema(
        example = "A0-123456",
        required = true,
        description = "The identifier MaaS uses to identify the customer"
    )
    @JsonProperty("id")
    var id: String? = null

    /**
     * optional reference field to the travelers in the planning request.
     * @return travelerReference
     */
    @get:Schema(description = "optional reference field to the travelers in the planning request.")
    @JsonProperty("travelerReference")
    var travelerReference: String? = null

    /**
     * Get initials
     * @return initials
     */
    @get:Schema(description = "")
    @JsonProperty("initials")
    var initials: String? = null

    /**
     * First name of the customer
     * @return firstName
     */
    @JvmField
    @get:Schema(example = "John", description = "First name of the customer")
    @JsonProperty("firstName")
    var firstName: String? = null

    /**
     * Last name of the customer
     * @return lastName
     */
    @JvmField
    @get:Schema(example = "Doe", description = "Last name of the customer")
    @JsonProperty("lastName")
    var lastName: String? = null

    /**
     * Middle name of the customer
     * @return middleName
     */
    @get:Schema(example = "von", description = "Middle name of the customer")
    @JsonProperty("middleName")
    var middleName: String? = null

    /**
     * prefix of the customer, like titles
     * @return prefix
     */
    @get:Schema(description = "prefix of the customer, like titles")
    @JsonProperty("prefix")
    var prefix: String? = null

    /**
     * postfix of the customer, like titles
     * @return postfix
     */
    @get:Schema(description = "postfix of the customer, like titles")
    @JsonProperty("postfix")
    var postfix: String? = null

    @JsonProperty("phones")
    private var phones: @Valid MutableList<Phone>? = null

    /**
     * the email address of the customer
     * @return email
     */
    @JvmField
    @get:Schema(description = "the email address of the customer")
    @JsonProperty("email")
    var email: String? = null

    /**
     * Get birthDate
     * @return birthDate
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("birthDate")
    var birthDate: LocalDate? = null

    /**
     * Get address
     * @return address
     */
    @get:Schema(description = "")
    @JsonProperty("address")
    var address: Address? = null

    /**
     * base64 encoded
     * @return photo
     */
    @get:Schema(description = "base64 encoded")
    @JsonProperty("photo")
    var photo: ByteArray? = null

    @JsonProperty("cards")
    private var cards: @Valid MutableList<Card>? = null

    @JsonProperty("licenses")
    private var licenses: @Valid MutableList<License>? = null
    fun id(id: String?): Customer {
        this.id = id
        return this
    }

    fun travelerReference(travelerReference: String?): Customer {
        this.travelerReference = travelerReference
        return this
    }

    fun initials(initials: String?): Customer {
        this.initials = initials
        return this
    }

    fun firstName(firstName: String?): Customer {
        this.firstName = firstName
        return this
    }

    fun lastName(lastName: String?): Customer {
        this.lastName = lastName
        return this
    }

    fun middleName(middleName: String?): Customer {
        this.middleName = middleName
        return this
    }

    fun prefix(prefix: String?): Customer {
        this.prefix = prefix
        return this
    }

    fun postfix(postfix: String?): Customer {
        this.postfix = postfix
        return this
    }

    fun phones(phones: List<Phone>?): Customer {
        this.phones = phones
        return this
    }

    fun addPhonesItem(phonesItem: Phone): Customer {
        if (phones == null) {
            phones = ArrayList()
        }
        phones!!.add(phonesItem)
        return this
    }

    /**
     * Get phones
     * @return phones
     */
    @Schema(description = "")
    fun getPhones(): @Valid MutableList<Phone>? {
        return phones
    }

    fun setPhones(phones: List<Phone>?) {
        this.phones = phones
    }

    fun email(email: String?): Customer {
        this.email = email
        return this
    }

    fun birthDate(birthDate: LocalDate?): Customer {
        this.birthDate = birthDate
        return this
    }

    fun address(address: Address?): Customer {
        this.address = address
        return this
    }

    fun photo(photo: ByteArray?): Customer {
        this.photo = photo
        return this
    }

    fun cards(cards: List<Card>?): Customer {
        this.cards = cards
        return this
    }

    fun addCardsItem(cardsItem: Card): Customer {
        if (cards == null) {
            cards = ArrayList()
        }
        cards!!.add(cardsItem)
        return this
    }

    /**
     * Get cards
     * @return cards
     */
    @Schema(description = "")
    fun getCards(): @Valid MutableList<Card>? {
        return cards
    }

    fun setCards(cards: List<Card>?) {
        this.cards = cards
    }

    fun licenses(licenses: List<License>?): Customer {
        this.licenses = licenses
        return this
    }

    fun addLicensesItem(licensesItem: License): Customer {
        if (licenses == null) {
            licenses = ArrayList()
        }
        licenses!!.add(licensesItem)
        return this
    }

    /**
     * Get licenses
     * @return licenses
     */
    @Schema(description = "")
    fun getLicenses(): @Valid MutableList<License>? {
        return licenses
    }

    fun setLicenses(licenses: List<License>?) {
        this.licenses = licenses
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val customer = o as Customer
        return id == customer.id && travelerReference == customer.travelerReference && initials == customer.initials && firstName == customer.firstName && lastName == customer.lastName && middleName == customer.middleName && prefix == customer.prefix && postfix == customer.postfix && phones == customer.phones && email == customer.email && birthDate == customer.birthDate && address == customer.address && photo == customer.photo && cards == customer.cards && licenses == customer.licenses &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            id,
            travelerReference,
            initials,
            firstName,
            lastName,
            middleName,
            prefix,
            postfix,
            phones,
            email,
            birthDate,
            address,
            photo,
            cards,
            licenses,
            super.hashCode()
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Customer {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    travelerReference: ").append(toIndentedString(travelerReference)).append("\n")
        sb.append("    initials: ").append(toIndentedString(initials)).append("\n")
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n")
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n")
        sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n")
        sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n")
        sb.append("    postfix: ").append(toIndentedString(postfix)).append("\n")
        sb.append("    phones: ").append(toIndentedString(phones)).append("\n")
        sb.append("    email: ").append(toIndentedString(email)).append("\n")
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n")
        sb.append("    address: ").append(toIndentedString(address)).append("\n")
        sb.append("    photo: ").append(toIndentedString(photo)).append("\n")
        sb.append("    cards: ").append(toIndentedString(cards)).append("\n")
        sb.append("    licenses: ").append(toIndentedString(licenses)).append("\n")
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
