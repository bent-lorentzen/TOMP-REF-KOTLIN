package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * SystemHours
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class SystemHours {
    /**
     * This indicates that this set of rental hours applies to either members or non-members only.
     */
    enum class UserTypeEnum(private val value: String) {
        MEMBER("MEMBER"),
        NON_MEMBERS("NON_MEMBERS");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): UserTypeEnum? {
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
     * This indicates that this set of rental hours applies to either members or non-members only.
     * @return userType
     */
    @JvmField
    @get:Schema(
        example = "MEMBER",
        description = "This indicates that this set of rental hours applies to either members or non-members only."
    )
    @JsonProperty("userType")
    var userType: UserTypeEnum? = null

    /**
     * If this parameter is present, it means that startTime and endTime correspond to the opening and closing hours of the station. (GET /operator/stations)
     * @return stationId
     */
    @get:Schema(description = "If this parameter is present, it means that startTime and endTime correspond to the opening and closing hours of the station. (GET /operator/stations)")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * If this parameter is present, it means that startTime and endTime correspond to the opening and closing hours for the region. (GET /operator/regions)
     * @return regionId
     */
    @get:Schema(description = "If this parameter is present, it means that startTime and endTime correspond to the opening and closing hours for the region. (GET /operator/regions)")
    @JsonProperty("regionId")
    var regionId: String? = null

    /**
     * Get startTime
     * @return startTime
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("startTime")
    var startTime: String? = null

    /**
     * Get endTime
     * @return endTime
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("endTime")
    var endTime: String? = null

    @JsonProperty("days")
    private var days: @Valid MutableList<Day>? = ArrayList()
    fun userType(userType: UserTypeEnum?): SystemHours {
        this.userType = userType
        return this
    }

    fun stationId(stationId: String?): SystemHours {
        this.stationId = stationId
        return this
    }

    fun regionId(regionId: String?): SystemHours {
        this.regionId = regionId
        return this
    }

    fun startTime(startTime: String?): SystemHours {
        this.startTime = startTime
        return this
    }

    fun endTime(endTime: String?): SystemHours {
        this.endTime = endTime
        return this
    }

    fun days(days: List<Day>?): SystemHours {
        this.days = days
        return this
    }

    fun addDaysItem(daysItem: Day): SystemHours {
        days!!.add(daysItem)
        return this
    }

    /**
     * An array of abbreviations (first 3 letters) of English names of the days of the week that this hour object applies to (i.e. [\"mon\", \"tue\"]). Each day can only appear once within all of the hours objects in this feed.
     * @return days
     */
    @Schema(
        required = true,
        description = "An array of abbreviations (first 3 letters) of English names of the days of the week that this hour object applies to (i.e. [\"mon\", \"tue\"]). Each day can only appear once within all of the hours objects in this feed."
    )
    fun getDays(): @NotNull @Valid MutableList<Day>? {
        return days
    }

    fun setDays(days: List<Day>?) {
        this.days = days
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemHours = o as SystemHours
        return userType == systemHours.userType && stationId == systemHours.stationId && regionId == systemHours.regionId && startTime == systemHours.startTime && endTime == systemHours.endTime && days == systemHours.days
    }

    override fun hashCode(): Int {
        return Objects.hash(userType, stationId, regionId, startTime, endTime, days)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemHours {\n")
        sb.append("    userType: ").append(toIndentedString(userType)).append("\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n")
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n")
        sb.append("    days: ").append(toIndentedString(days)).append("\n")
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
