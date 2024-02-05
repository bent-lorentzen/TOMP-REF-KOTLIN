package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * ProcessIdentifiers
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class ProcessIdentifiers {
    @JsonProperty("operatorInformation")
    private var operatorInformation: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("planning")
    private var planning: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("booking")
    private var booking: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("tripExecution")
    private var tripExecution: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("support")
    private var support: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("payment")
    private var payment: @Valid MutableList<String>? = ArrayList()

    @JsonProperty("general")
    private var general: @Valid MutableList<String>? = ArrayList()
    fun operatorInformation(operatorInformation: List<String>?): ProcessIdentifiers {
        this.operatorInformation = operatorInformation
        return this
    }

    fun addOperatorInformationItem(operatorInformationItem: String): ProcessIdentifiers {
        operatorInformation!!.add(operatorInformationItem)
        return this
    }

    /**
     * Get operatorInformation
     * @return operatorInformation
     */
    @Schema(required = true, description = "")
    fun getOperatorInformation(): @NotNull MutableList<String>? {
        return operatorInformation
    }

    fun setOperatorInformation(operatorInformation: List<String>?) {
        this.operatorInformation = operatorInformation
    }

    fun planning(planning: List<String>?): ProcessIdentifiers {
        this.planning = planning
        return this
    }

    fun addPlanningItem(planningItem: String): ProcessIdentifiers {
        planning!!.add(planningItem)
        return this
    }

    /**
     * Get planning
     * @return planning
     */
    @Schema(required = true, description = "")
    fun getPlanning(): @NotNull MutableList<String>? {
        return planning
    }

    fun setPlanning(planning: List<String>?) {
        this.planning = planning
    }

    fun booking(booking: List<String>?): ProcessIdentifiers {
        this.booking = booking
        return this
    }

    fun addBookingItem(bookingItem: String): ProcessIdentifiers {
        booking!!.add(bookingItem)
        return this
    }

    /**
     * Get booking
     * @return booking
     */
    @Schema(required = true, description = "")
    fun getBooking(): @NotNull MutableList<String>? {
        return booking
    }

    fun setBooking(booking: List<String>?) {
        this.booking = booking
    }

    fun tripExecution(tripExecution: List<String>?): ProcessIdentifiers {
        this.tripExecution = tripExecution
        return this
    }

    fun addTripExecutionItem(tripExecutionItem: String): ProcessIdentifiers {
        tripExecution!!.add(tripExecutionItem)
        return this
    }

    /**
     * Get tripExecution
     * @return tripExecution
     */
    @Schema(required = true, description = "")
    fun getTripExecution(): @NotNull MutableList<String>? {
        return tripExecution
    }

    fun setTripExecution(tripExecution: List<String>?) {
        this.tripExecution = tripExecution
    }

    fun support(support: List<String>?): ProcessIdentifiers {
        this.support = support
        return this
    }

    fun addSupportItem(supportItem: String): ProcessIdentifiers {
        support!!.add(supportItem)
        return this
    }

    /**
     * Get support
     * @return support
     */
    @Schema(required = true, description = "")
    fun getSupport(): @NotNull MutableList<String>? {
        return support
    }

    fun setSupport(support: List<String>?) {
        this.support = support
    }

    fun payment(payment: List<String>?): ProcessIdentifiers {
        this.payment = payment
        return this
    }

    fun addPaymentItem(paymentItem: String): ProcessIdentifiers {
        payment!!.add(paymentItem)
        return this
    }

    /**
     * Get payment
     * @return payment
     */
    @Schema(required = true, description = "")
    fun getPayment(): @NotNull MutableList<String>? {
        return payment
    }

    fun setPayment(payment: List<String>?) {
        this.payment = payment
    }

    fun general(general: List<String>?): ProcessIdentifiers {
        this.general = general
        return this
    }

    fun addGeneralItem(generalItem: String): ProcessIdentifiers {
        general!!.add(generalItem)
        return this
    }

    /**
     * Get general
     * @return general
     */
    @Schema(required = true, description = "")
    fun getGeneral(): @NotNull MutableList<String>? {
        return general
    }

    fun setGeneral(general: List<String>?) {
        this.general = general
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val processIdentifiers = o as ProcessIdentifiers
        return operatorInformation == processIdentifiers.operatorInformation && planning == processIdentifiers.planning && booking == processIdentifiers.booking && tripExecution == processIdentifiers.tripExecution && support == processIdentifiers.support && payment == processIdentifiers.payment && general == processIdentifiers.general
    }

    override fun hashCode(): Int {
        return Objects.hash(operatorInformation, planning, booking, tripExecution, support, payment, general)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ProcessIdentifiers {\n")
        sb.append("    operatorInformation: ").append(toIndentedString(operatorInformation)).append("\n")
        sb.append("    planning: ").append(toIndentedString(planning)).append("\n")
        sb.append("    booking: ").append(toIndentedString(booking)).append("\n")
        sb.append("    tripExecution: ").append(toIndentedString(tripExecution)).append("\n")
        sb.append("    support: ").append(toIndentedString(support)).append("\n")
        sb.append("    payment: ").append(toIndentedString(payment)).append("\n")
        sb.append("    general: ").append(toIndentedString(general)).append("\n")
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
