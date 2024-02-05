package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated

/**
 * JournalEntry
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class JournalEntry : AmountOfMoney() {
    /**
     * Get category
     * @return category
     */
    @get:Schema(description = "")
    @JsonProperty("category")
    var category: JournalCategory? = null

    /**
     * id of the entry, leg id can be reused
     * @return journalId
     */
    @JvmField
    @get:Schema(description = "id of the entry, leg id can be reused")
    @JsonProperty("journalId")
    var journalId: String? = null

    /**
     * sequence id of the entry, in combination with journalId unique from TO perspective.
     * @return journalSequenceId
     */
    @JvmField
    @get:Schema(description = "sequence id of the entry, in combination with journalId unique from TO perspective.")
    @JsonProperty("journalSequenceId")
    var journalSequenceId: String? = null

    /**
     * the number of the invoice. Should be filled in when invoiced.
     * @return invoiceId
     */
    @get:Schema(description = "the number of the invoice. Should be filled in when invoiced.")
    @JsonProperty("invoiceId")
    var invoiceId: String? = null

    /**
     * Get invoiceDate
     * @return invoiceDate
     */
    @get:Schema(description = "")
    @JsonProperty("invoiceDate")
    var invoiceDate: OffsetDateTime? = null

    /**
     * Get state
     * @return state
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("state")
    var state: JournalState? = null

    /**
     * Get expirationDate
     * @return expirationDate
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("expirationDate")
    var expirationDate: OffsetDateTime? = null

    /**
     * Get comment
     * @return comment
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("comment")
    var comment: String? = null

    /**
     * the travelled distance. Only if applicable.
     * minimum: 0
     * @return distance
     */
    @JvmField
    @get:Schema(description = "the travelled distance. Only if applicable.")
    @JsonProperty("distance")
    var distance: Float? = null

    /**
     * Gets or Sets distanceType
     */
    enum class DistanceTypeEnum(private val value: String) {
        KM("KM"),
        MILE("MILE");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): DistanceTypeEnum? {
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
     * Get distanceType
     * @return distanceType
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("distanceType")
    var distanceType: DistanceTypeEnum? = null

    /**
     * the time in seconds that the assed is used. Only if applicable.
     * minimum: 0
     * @return usedTime
     */
    @JvmField
    @get:Schema(description = "the time in seconds that the assed is used. Only if applicable.")
    @JsonProperty("usedTime")
    var usedTime: Int? = null

    /**
     * the mileage at the start of the rental. 'DistanceType' field is also applicable here
     * minimum: 0
     * @return rentalStartMileage
     */
    @get:Schema(description = "the mileage at the start of the rental. 'DistanceType' field is also applicable here")
    @JsonProperty("rentalStartMileage")
    var rentalStartMileage: Float? = null

    /**
     * Get bankAccount
     * @return bankAccount
     */
    @get:Schema(description = "")
    @JsonProperty("bankAccount")
    var bankAccount: BankAccount? = null

    /**
     * the specification of the amount; how is it composed.
     * @return details
     */
    @JvmField
    @get:Schema(description = "the specification of the amount; how is it composed.")
    @JsonProperty("details")
    var details: Any? = null
    fun category(category: JournalCategory?): JournalEntry {
        this.category = category
        return this
    }

    fun journalId(journalId: String?): JournalEntry {
        this.journalId = journalId
        return this
    }

    fun journalSequenceId(journalSequenceId: String?): JournalEntry {
        this.journalSequenceId = journalSequenceId
        return this
    }

    fun invoiceId(invoiceId: String?): JournalEntry {
        this.invoiceId = invoiceId
        return this
    }

    fun invoiceDate(invoiceDate: OffsetDateTime?): JournalEntry {
        this.invoiceDate = invoiceDate
        return this
    }

    fun state(state: JournalState?): JournalEntry {
        this.state = state
        return this
    }

    fun expirationDate(expirationDate: OffsetDateTime?): JournalEntry {
        this.expirationDate = expirationDate
        return this
    }

    fun comment(comment: String?): JournalEntry {
        this.comment = comment
        return this
    }

    fun distance(distance: Float?): JournalEntry {
        this.distance = distance
        return this
    }

    fun distanceType(distanceType: DistanceTypeEnum?): JournalEntry {
        this.distanceType = distanceType
        return this
    }

    fun usedTime(usedTime: Int?): JournalEntry {
        this.usedTime = usedTime
        return this
    }

    fun rentalStartMileage(rentalStartMileage: Float?): JournalEntry {
        this.rentalStartMileage = rentalStartMileage
        return this
    }

    fun bankAccount(bankAccount: BankAccount?): JournalEntry {
        this.bankAccount = bankAccount
        return this
    }

    fun details(details: Any?): JournalEntry {
        this.details = details
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val journalEntry = o as JournalEntry
        return this.category == journalEntry.category && journalId == journalEntry.journalId && journalSequenceId == journalEntry.journalSequenceId && invoiceId == journalEntry.invoiceId && invoiceDate == journalEntry.invoiceDate && state == journalEntry.state && expirationDate == journalEntry.expirationDate && comment == journalEntry.comment && distance == journalEntry.distance && distanceType == journalEntry.distanceType && usedTime == journalEntry.usedTime && rentalStartMileage == journalEntry.rentalStartMileage && bankAccount == journalEntry.bankAccount && details == journalEntry.details &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            category,
            journalId,
            journalSequenceId,
            invoiceId,
            invoiceDate,
            state,
            expirationDate,
            comment,
            distance,
            distanceType,
            usedTime,
            rentalStartMileage,
            bankAccount,
            details,
            super.hashCode()
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class JournalEntry {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    category: ").append(toIndentedString(category)).append("\n")
        sb.append("    journalId: ").append(toIndentedString(journalId)).append("\n")
        sb.append("    journalSequenceId: ").append(toIndentedString(journalSequenceId)).append("\n")
        sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n")
        sb.append("    invoiceDate: ").append(toIndentedString(invoiceDate)).append("\n")
        sb.append("    state: ").append(toIndentedString(state)).append("\n")
        sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
        sb.append("    distance: ").append(toIndentedString(distance)).append("\n")
        sb.append("    distanceType: ").append(toIndentedString(distanceType)).append("\n")
        sb.append("    usedTime: ").append(toIndentedString(usedTime)).append("\n")
        sb.append("    rentalStartMileage: ").append(toIndentedString(rentalStartMileage)).append("\n")
        sb.append("    bankAccount: ").append(toIndentedString(bankAccount)).append("\n")
        sb.append("    details: ").append(toIndentedString(details)).append("\n")
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
