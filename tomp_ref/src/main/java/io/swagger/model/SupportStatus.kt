package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * the current status of support
 */
@Schema(description = "the current status of support")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class SupportStatus : SupportRequest() {
    /**
     * Gets or Sets status
     */
    enum class StatusEnum(private val value: String) {
        PROCESSING("PROCESSING"),
        UPDATE_REQUESTED("UPDATE_REQUESTED"),
        RESOLVED("RESOLVED"),
        CANCELLED("CANCELLED");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): StatusEnum? {
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
     * Get status
     * @return status
     */
    @get:Schema(example = "PROCESSING", description = "")
    @JsonProperty("status")
    var status: StatusEnum? = null

    /**
     * time in minutes to expected resolution of support request
     * @return timeToResolution
     */
    @get:Schema(example = "9", description = "time in minutes to expected resolution of support request")
    @JsonProperty("timeToResolution")
    var timeToResolution: Int? = null

    /**
     * the sequence number of status of the support issue
     * minimum: 0
     * @return order
     */
    @get:Schema(description = "the sequence number of status of the support issue")
    @JsonProperty("order")
    var order: Int? = null

    /**
     * free text to send to the end user.
     * @return comment
     */
    @get:Schema(description = "free text to send to the end user.")
    @JsonProperty("comment")
    override var comment: String? = null
    fun status(status: StatusEnum?): SupportStatus {
        this.status = status
        return this
    }

    fun timeToResolution(timeToResolution: Int?): SupportStatus {
        this.timeToResolution = timeToResolution
        return this
    }

    fun order(order: Int?): SupportStatus {
        this.order = order
        return this
    }

    override fun comment(comment: String?): SupportStatus {
        this.comment = comment
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val supportStatus = o as SupportStatus
        return status == supportStatus.status && timeToResolution == supportStatus.timeToResolution && order == supportStatus.order && this.comment == supportStatus.comment &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(status, timeToResolution, order, comment, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SupportStatus {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    status: ").append(toIndentedString(status)).append("\n")
        sb.append("    timeToResolution: ").append(toIndentedString(timeToResolution)).append("\n")
        sb.append("    order: ").append(toIndentedString(order)).append("\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
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
