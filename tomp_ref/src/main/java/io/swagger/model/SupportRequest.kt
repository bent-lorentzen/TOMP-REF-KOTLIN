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
 * request for support
 */
@Schema(description = "request for support")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
open class SupportRequest {
    /**
     * the booking id
     * @return id
     */
    @get:Schema(description = "the booking id")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Gets or Sets supportType
     */
    enum class SupportTypeEnum(private val value: String) {
        BROKEN_DOWN("BROKEN_DOWN"),
        NOT_AT_LOCATION("NOT_AT_LOCATION"),
        MISSING_AFTER_PAUSE("MISSING_AFTER_PAUSE"),
        NOT_CLEAN("NOT_CLEAN"),
        NOT_AVAILABLE("NOT_AVAILABLE"),
        UNABLE_TO_OPEN("UNABLE_TO_OPEN"),
        UNABLE_TO_CLOSE("UNABLE_TO_CLOSE"),
        API_TECHNICAL("API_TECHNICAL"),
        API_FUNCTIONAL("API_FUNCTIONAL"),
        ACCIDENT("ACCIDENT"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): SupportTypeEnum? {
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
     * Get supportType
     * @return supportType
     */
    @get:Schema(description = "")
    @JsonProperty("supportType")
    var supportType: SupportTypeEnum? = null

    /**
     * Get location
     * @return location
     */
    @get:Schema(description = "")
    @JsonProperty("location")
    var location: Place? = null

    /**
     * Get time
     * @return time
     */
    @get:Schema(description = "")
    @JsonProperty("time")
    var time: OffsetDateTime? = null

    /**
     * the priority of the support request.
     */
    enum class PriorityEnum(private val value: String) {
        ERROR_CANNOT_CONTINUE("ERROR_CANNOT_CONTINUE"),
        ERROR_CAN_CONTINUE("ERROR_CAN_CONTINUE"),
        DISTURBING_ISSUE("DISTURBING_ISSUE"),
        QUESTION("QUESTION"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): PriorityEnum? {
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
     * the priority of the support request.
     * @return priority
     */
    @get:Schema(description = "the priority of the support request.")
    @JsonProperty("priority")
    var priority: PriorityEnum? = null

    /**
     * contact information of the end user in case of direct response requests, like phone number
     * @return contactInformationEndUser
     */
    @get:Schema(description = "contact information of the end user in case of direct response requests, like phone number")
    @JsonProperty("contactInformationEndUser")
    var contactInformationEndUser: String? = null

    /**
     * Get comment
     * @return comment
     */
    @get:Schema(description = "")
    @JsonProperty("comment")
    open var comment: String? = null

    /**
     * time to respond in minutes.
     * minimum: 0
     * @return requestedResponseTime
     */
    @get:Schema(description = "time to respond in minutes.")
    @JsonProperty("requestedResponseTime")
    var requestedResponseTime: Float? = null
    fun id(id: String?): SupportRequest {
        this.id = id
        return this
    }

    fun supportType(supportType: SupportTypeEnum?): SupportRequest {
        this.supportType = supportType
        return this
    }

    fun location(location: Place?): SupportRequest {
        this.location = location
        return this
    }

    fun time(time: OffsetDateTime?): SupportRequest {
        this.time = time
        return this
    }

    fun priority(priority: PriorityEnum?): SupportRequest {
        this.priority = priority
        return this
    }

    fun contactInformationEndUser(contactInformationEndUser: String?): SupportRequest {
        this.contactInformationEndUser = contactInformationEndUser
        return this
    }

    open fun comment(comment: String?): SupportRequest {
        this.comment = comment
        return this
    }

    fun requestedResponseTime(requestedResponseTime: Float?): SupportRequest {
        this.requestedResponseTime = requestedResponseTime
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val supportRequest = o as SupportRequest
        return id == supportRequest.id && supportType == supportRequest.supportType && location == supportRequest.location && time == supportRequest.time && priority == supportRequest.priority && contactInformationEndUser == supportRequest.contactInformationEndUser && comment == supportRequest.comment && requestedResponseTime == supportRequest.requestedResponseTime
    }

    override fun hashCode(): Int {
        return Objects.hash(id, supportType, location, time, priority, contactInformationEndUser, comment, requestedResponseTime)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SupportRequest {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    supportType: ").append(toIndentedString(supportType)).append("\n")
        sb.append("    location: ").append(toIndentedString(location)).append("\n")
        sb.append("    time: ").append(toIndentedString(time)).append("\n")
        sb.append("    priority: ").append(toIndentedString(priority)).append("\n")
        sb.append("    contactInformationEndUser: ").append(toIndentedString(contactInformationEndUser)).append("\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
        sb.append("    requestedResponseTime: ").append(toIndentedString(requestedResponseTime)).append("\n")
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
