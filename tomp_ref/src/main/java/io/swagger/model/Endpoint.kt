package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * a formal description of an endpoint.
 */
@Schema(description = "a formal description of an endpoint.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class Endpoint {
    /**
     * Gets or Sets method
     */
    enum class MethodEnum(private val value: String) {
        POST("POST"),
        PUT("PUT"),
        GET("GET"),
        DELETE("DELETE"),
        PATCH("PATCH");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): MethodEnum? {
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
     * Get method
     * @return method
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("method")
    var method: MethodEnum? = null

    /**
     * the exact path of the endpoint, starting after the base URL
     * @return path
     */
    @get:Schema(
        example = "/plannings/",
        required = true,
        description = "the exact path of the endpoint, starting after the base URL"
    )
    @JsonProperty("path")
    var path: String? = null

    /**
     * in case the path is ending in /events, the event type/operator enum should be added here.
     */
    enum class EventTypeEnum(private val value: String) {
        PREPARE("PREPARE"),
        ASSIGN_ASSET("ASSIGN_ASSET"),
        SET_IN_USE("SET_IN_USE"),
        PAUSE("PAUSE"),
        START_FINISHING("START_FINISHING"),
        FINISH("FINISH"),
        ISSUE("ISSUE"),
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
            fun fromValue(text: String): EventTypeEnum? {
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
     * in case the path is ending in /events, the event type/operator enum should be added here.
     * @return eventType
     */
    @get:Schema(description = "in case the path is ending in /events, the event type/operator enum should be added here.")
    @JsonProperty("eventType")
    var eventType: EventTypeEnum? = null

    /**
     * Gets or Sets status
     */
    enum class StatusEnum(private val value: String) {
        NOT_IMPLEMENTED("NOT_IMPLEMENTED"),
        DIALECT("DIALECT"),
        IMPLEMENTED("IMPLEMENTED");

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
    @get:Schema(required = true, description = "")
    @JsonProperty("status")
    var status: StatusEnum? = null

    /**
     * does this endpoint support paging? In that case this endpoint can be accessed using query parameters offset=x and limit=y. Only allowed at endpoints that have specified these query parameters.
     * @return supportsPaging
     */
    @get:Schema(description = "does this endpoint support paging? In that case this endpoint can be accessed using query parameters offset=x and limit=y. Only allowed at endpoints that have specified these query parameters.")
    @JsonProperty("supportsPaging")
    var isSupportsPaging = false

    /**
     * the maximum size of the pages (only valid when supportsPaging=true). If the limit-parameter of the request is above this amount, a http code 400 will be returned.
     * minimum: 1
     * @return maxPageSize
     */
    @get:Schema(description = "the maximum size of the pages (only valid when supportsPaging=true). If the limit-parameter of the request is above this amount, a http code 400 will be returned.")
    @JsonProperty("maxPageSize")
    var maxPageSize: Int? = null
    fun method(method: MethodEnum?): Endpoint {
        this.method = method
        return this
    }

    fun path(path: String?): Endpoint {
        this.path = path
        return this
    }

    fun eventType(eventType: EventTypeEnum?): Endpoint {
        this.eventType = eventType
        return this
    }

    fun status(status: StatusEnum?): Endpoint {
        this.status = status
        return this
    }

    fun supportsPaging(supportsPaging: Boolean): Endpoint {
        isSupportsPaging = supportsPaging
        return this
    }

    fun maxPageSize(maxPageSize: Int?): Endpoint {
        this.maxPageSize = maxPageSize
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val endpoint = o as Endpoint
        return method == endpoint.method && path == endpoint.path && eventType == endpoint.eventType && status == endpoint.status && isSupportsPaging == endpoint.isSupportsPaging && maxPageSize == endpoint.maxPageSize
    }

    override fun hashCode(): Int {
        return Objects.hash(method, path, eventType, status, isSupportsPaging, maxPageSize)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Endpoint {\n")
        sb.append("    method: ").append(toIndentedString(method)).append("\n")
        sb.append("    path: ").append(toIndentedString(path)).append("\n")
        sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n")
        sb.append("    status: ").append(toIndentedString(status)).append("\n")
        sb.append("    supportsPaging: ").append(toIndentedString(isSupportsPaging)).append("\n")
        sb.append("    maxPageSize: ").append(toIndentedString(maxPageSize)).append("\n")
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
