package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * A category of license to use a certain asset class
 */
@Schema(description = "A category of license to use a certain asset class")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
open class LicenseType {
    /**
     * Get assetClass
     * @return assetClass
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("assetClass")
    var assetClass: AssetClass? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return issuingCountry
     */
    @get:Schema(example = "NL", description = "two-letter country codes according to ISO 3166-1")
    @JsonProperty("issuingCountry")
    var issuingCountry: String? = null
    fun assetClass(assetClass: AssetClass?): LicenseType {
        this.assetClass = assetClass
        return this
    }

    fun issuingCountry(issuingCountry: String?): LicenseType {
        this.issuingCountry = issuingCountry
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val licenseType = o as LicenseType
        return assetClass == licenseType.assetClass && issuingCountry == licenseType.issuingCountry
    }

    override fun hashCode(): Int {
        return Objects.hash(assetClass, issuingCountry)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class LicenseType {\n")
        sb.append("    assetClass: ").append(toIndentedString(assetClass)).append("\n")
        sb.append("    issuingCountry: ").append(toIndentedString(issuingCountry)).append("\n")
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
