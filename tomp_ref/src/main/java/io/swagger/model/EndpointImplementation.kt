package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * a complete endpoint description, containing all endpoints, their status, but also the served scenarios and implemented process flows. The identifiers for the process flows can be found at https://github.com/TOMP-WG/TOMP-API/wiki/ProcessIdentifiers
 */
@Schema(description = "a complete endpoint description, containing all endpoints, their status, but also the served scenarios and implemented process flows. The identifiers for the process flows can be found at https://github.com/TOMP-WG/TOMP-API/wiki/ProcessIdentifiers")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class EndpointImplementation {
    /**
     * Get version
     * @return version
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("version")
    var version: String? = null

    /**
     * Get baseUrl
     * @return baseUrl
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("baseUrl")
    var baseUrl: String? = null

    @JsonProperty("endpoints")
    private var endpoints: @Valid MutableList<Endpoint>? = ArrayList()

    @JsonProperty("scenarios")
    private var scenarios: @Valid MutableList<Scenario>? = ArrayList()

    /**
     * Get processIdentifiers
     * @return processIdentifiers
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("processIdentifiers")
    var processIdentifiers: ProcessIdentifiers? = null
    fun version(version: String?): EndpointImplementation {
        this.version = version
        return this
    }

    fun baseUrl(baseUrl: String?): EndpointImplementation {
        this.baseUrl = baseUrl
        return this
    }

    fun endpoints(endpoints: List<Endpoint>?): EndpointImplementation {
        this.endpoints = endpoints
        return this
    }

    fun addEndpointsItem(endpointsItem: Endpoint): EndpointImplementation {
        endpoints!!.add(endpointsItem)
        return this
    }

    /**
     * Get endpoints
     * @return endpoints
     */
    @Schema(required = true, description = "")
    fun getEndpoints(): @NotNull @Valid MutableList<Endpoint>? {
        return endpoints
    }

    fun setEndpoints(endpoints: List<Endpoint>?) {
        this.endpoints = endpoints
    }

    fun scenarios(scenarios: List<Scenario>?): EndpointImplementation {
        this.scenarios = scenarios
        return this
    }

    fun addScenariosItem(scenariosItem: Scenario): EndpointImplementation {
        scenarios!!.add(scenariosItem)
        return this
    }

    /**
     * Get scenarios
     * @return scenarios
     */
    @Schema(required = true, description = "")
    fun getScenarios(): @NotNull @Valid MutableList<Scenario>? {
        return scenarios
    }

    fun setScenarios(scenarios: List<Scenario>?) {
        this.scenarios = scenarios
    }

    fun processIdentifiers(processIdentifiers: ProcessIdentifiers?): EndpointImplementation {
        this.processIdentifiers = processIdentifiers
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val endpointImplementation = o as EndpointImplementation
        return version == endpointImplementation.version && baseUrl == endpointImplementation.baseUrl && endpoints == endpointImplementation.endpoints && scenarios == endpointImplementation.scenarios && processIdentifiers == endpointImplementation.processIdentifiers
    }

    override fun hashCode(): Int {
        return Objects.hash(version, baseUrl, endpoints, scenarios, processIdentifiers)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class EndpointImplementation {\n")
        sb.append("    version: ").append(toIndentedString(version)).append("\n")
        sb.append("    baseUrl: ").append(toIndentedString(baseUrl)).append("\n")
        sb.append("    endpoints: ").append(toIndentedString(endpoints)).append("\n")
        sb.append("    scenarios: ").append(toIndentedString(scenarios)).append("\n")
        sb.append("    processIdentifiers: ").append(toIndentedString(processIdentifiers)).append("\n")
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
