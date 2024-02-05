package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * Axa EKey information
 */
@Schema(description = "Axa EKey information")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class TokenEKey : TokenData(), OneOftokenTokenData {
    /**
     * Get ekey
     * @return ekey
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("ekey")
    var ekey: TokenEKeyEkey? = null

    /**
     * Get lock
     * @return lock
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("lock")
    var lock: TokenEKeyLock? = null
    fun ekey(ekey: TokenEKeyEkey?): TokenEKey {
        this.ekey = ekey
        return this
    }

    fun lock(lock: TokenEKeyLock?): TokenEKey {
        this.lock = lock
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tokenEKey = o as TokenEKey
        return ekey == tokenEKey.ekey && lock == tokenEKey.lock &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(ekey, lock, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TokenEKey {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    ekey: ").append(toIndentedString(ekey)).append("\n")
        sb.append("    lock: ").append(toIndentedString(lock)).append("\n")
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
