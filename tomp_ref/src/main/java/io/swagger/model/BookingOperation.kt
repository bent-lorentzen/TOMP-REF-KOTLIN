package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * operation on the bookingOption
 */
@Schema(description = "operation on the bookingOption")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class BookingOperation {
    /**
     * Gets or Sets operation
     */
    enum class OperationEnum(private val value: String) {
        CANCEL("CANCEL"),
        EXPIRE("EXPIRE"),
        DENY("DENY"),
        COMMIT("COMMIT");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): OperationEnum? {
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
     * Get operation
     * @return operation
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("operation")
    var operation: OperationEnum? = null

    /**
     * This operation can be done on behalf of another party. The MP can act on behalf of the END_USER (cancel this booking for me); to override the default origin. In case this field is missing, it must be assumed that the events the MP is sending, this field should contain \"MP\". And in case the TO is sending, \"TO\".
     */
    enum class OriginEnum(private val value: String) {
        TO("TO"),
        MP("MP"),
        END_USER("END_USER"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): OriginEnum? {
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
     * This operation can be done on behalf of another party. The MP can act on behalf of the END_USER (cancel this booking for me); to override the default origin. In case this field is missing, it must be assumed that the events the MP is sending, this field should contain \"MP\". And in case the TO is sending, \"TO\".
     * @return origin
     */
    @get:Schema(description = "This operation can be done on behalf of another party. The MP can act on behalf of the END_USER (cancel this booking for me); to override the default origin. In case this field is missing, it must be assumed that the events the MP is sending, this field should contain \"MP\". And in case the TO is sending, \"TO\".")
    @JsonProperty("origin")
    var origin: OriginEnum? = null
    fun operation(operation: OperationEnum?): BookingOperation {
        this.operation = operation
        return this
    }

    fun origin(origin: OriginEnum?): BookingOperation {
        this.origin = origin
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val bookingOperation = o as BookingOperation
        return operation == bookingOperation.operation && origin == bookingOperation.origin
    }

    override fun hashCode(): Int {
        return Objects.hash(operation, origin)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class BookingOperation {\n")
        sb.append("    operation: ").append(toIndentedString(operation)).append("\n")
        sb.append("    origin: ").append(toIndentedString(origin)).append("\n")
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
