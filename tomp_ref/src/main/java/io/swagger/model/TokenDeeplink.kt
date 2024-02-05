package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * deeplink info
 */
@Schema(description = "deeplink info")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class TokenDeeplink : TokenData(), OneOftokenTokenData {
    /**
     * the base deeplink url for the MP app. Can be extended by the 'knownParamaters'. Including the scheme.
     * @return url
     */
    @get:Schema(
        example = "mp1.app://something/?auth=sdfkjhrkjsdf003df38=dfsdf",
        description = "the base deeplink url for the MP app. Can be extended by the 'knownParamaters'. Including the scheme."
    )
    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("knownParameters")
    private var knownParameters: @Valid MutableList<String>? = null
    fun url(url: String?): TokenDeeplink {
        this.url = url
        return this
    }

    fun knownParameters(knownParameters: List<String>?): TokenDeeplink {
        this.knownParameters = knownParameters
        return this
    }

    fun addKnownParametersItem(knownParametersItem: String): TokenDeeplink {
        if (knownParameters == null) {
            knownParameters = ArrayList()
        }
        knownParameters!!.add(knownParametersItem)
        return this
    }

    /**
     * Get knownParameters
     * @return knownParameters
     */
    @Schema(example = "[\"return-url\",\"error-url\",\"error-code\",\"error-description\"]", description = "")
    fun getKnownParameters(): List<String>? {
        return knownParameters
    }

    fun setKnownParameters(knownParameters: List<String>?) {
        this.knownParameters = knownParameters
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenDeeplink = o as TokenDeeplink
        return url == tokenDeeplink.url && knownParameters == tokenDeeplink.knownParameters &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(url, knownParameters, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenDeeplink {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    knownParameters: ").append(toIndentedString(knownParameters)).append("\n")
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
