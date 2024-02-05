package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * A booking requested by the MP
 */
@Schema(description = "A booking requested by the MP")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T07:58:28.459Z[GMT]")
open class BookingRequest {
    /**
     * A unique identifier for the TO to know this booking by
     * @return id
     */
    @JvmField
    @get:Schema(description = "A unique identifier for the TO to know this booking by")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get from
     * @return from
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("from")
    var from: Place? = null

    /**
     * The callback URL of the Maas Provider, to use as base url for callback, f.x. the POST legs/{id}/events and POST /bookings/{id}/events. Only to be provided when this deviates from standard or agreed URL.
     * @return callbackUrl
     */
    @get:Schema(description = "The callback URL of the Maas Provider, to use as base url for callback, f.x. the POST legs/{id}/events and POST /bookings/{id}/events. Only to be provided when this deviates from standard or agreed URL.")
    @JsonProperty("callbackUrl")
    var callbackUrl: String? = null

    /**
     * Get to
     * @return to
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("to")
    var to: Place? = null

    /**
     * Get customer
     * @return customer
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("customer")
    var customer: Customer? = null
    fun id(id: String?): BookingRequest {
        this.id = id
        return this
    }

    fun from(from: Place?): BookingRequest {
        this.from = from
        return this
    }

    fun callbackUrl(callbackUrl: String?): BookingRequest {
        this.callbackUrl = callbackUrl
        return this
    }

    fun to(to: Place?): BookingRequest {
        this.to = to
        return this
    }

    fun customer(customer: Customer?): BookingRequest {
        this.customer = customer
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val bookingRequest = o as BookingRequest
        return id == bookingRequest.id && from == bookingRequest.from && callbackUrl == bookingRequest.callbackUrl && to == bookingRequest.to && customer == bookingRequest.customer
    }

    override fun hashCode(): Int {
        return Objects.hash(id, from, callbackUrl, to, customer)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class BookingRequest {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    from: ").append(toIndentedString(from)).append("\n")
        sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n")
        sb.append("    to: ").append(toIndentedString(to)).append("\n")
        sb.append("    customer: ").append(toIndentedString(customer)).append("\n")
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
