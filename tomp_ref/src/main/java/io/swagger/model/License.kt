package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.LocalDate
import java.util.Objects
import javax.annotation.Generated

/**
 * driver or usage license for a specific user. Contains the number and the assetType you&#x27;re allowed to operate (e.g. driver license for CAR)
 */
@Schema(description = "driver or usage license for a specific user. Contains the number and the assetType you're allowed to operate (e.g. driver license for CAR)")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class License : LicenseType() {
    /**
     * Get number
     * @return number
     */
    @get:Schema(example = "1287948792", description = "")
    @JsonProperty("number")
    var number: String? = null

    /**
     * in most countries a driver license has also a code. As TO you can exactly verify, based on this code if the license allows to operate it's assets, if the assetType too generic.
     * @return licenseCode
     */
    @get:Schema(
        example = "D4",
        description = "in most countries a driver license has also a code. As TO you can exactly verify, based on this code if the license allows to operate it's assets, if the assetType too generic."
    )
    @JsonProperty("licenseCode")
    var licenseCode: String? = null

    /**
     * Get validUntil
     * @return validUntil
     */
    @get:Schema(description = "")
    @JsonProperty("validUntil")
    var validUntil: LocalDate? = null
    fun number(number: String?): License {
        this.number = number
        return this
    }

    fun licenseCode(licenseCode: String?): License {
        this.licenseCode = licenseCode
        return this
    }

    fun validUntil(validUntil: LocalDate?): License {
        this.validUntil = validUntil
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val license = o as License
        return number == license.number && licenseCode == license.licenseCode && validUntil == license.validUntil &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(number, licenseCode, validUntil, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class License {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    licenseCode: ").append(toIndentedString(licenseCode)).append("\n")
        sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n")
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
