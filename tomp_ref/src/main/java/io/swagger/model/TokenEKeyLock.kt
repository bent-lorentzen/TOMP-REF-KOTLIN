package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * TokenEKeyLock
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class TokenEKeyLock {
    /**
     * physical address
     * @return bdAddress
     */
    @get:Schema(description = "physical address")
    @JsonProperty("bdAddress")
    var bdAddress: String? = null

    /**
     * how it advertises itself
     * @return deviceName
     */
    @get:Schema(description = "how it advertises itself")
    @JsonProperty("deviceName")
    var deviceName: String? = null
    fun bdAddress(bdAddress: String?): TokenEKeyLock {
        this.bdAddress = bdAddress
        return this
    }

    fun deviceName(deviceName: String?): TokenEKeyLock {
        this.deviceName = deviceName
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenEKeyLock = o as TokenEKeyLock
        return bdAddress == tokenEKeyLock.bdAddress && deviceName == tokenEKeyLock.deviceName
    }

    override fun hashCode(): Int {
        return Objects.hash(bdAddress, deviceName)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenEKeyLock {\n")
        sb.append("    bdAddress: ").append(toIndentedString(bdAddress)).append("\n")
        sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("\n")
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
