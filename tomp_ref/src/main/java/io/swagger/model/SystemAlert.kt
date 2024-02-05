package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * SystemAlert
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class SystemAlert {
    /**
     * a unique identifier for this alert
     * @return alertId
     */
    @get:Schema(required = true, description = "a unique identifier for this alert")
    @JsonProperty("alertId")
    var alertId: String? = null

    /**
     * Gets or Sets alertType
     */
    enum class AlertTypeEnum(private val value: String) {
        SYSTEM_CLOSURE("SYSTEM_CLOSURE"),
        STATION_CLOSURE("STATION_CLOSURE"),
        STATION_MOVE("STATION_MOVE"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): AlertTypeEnum? {
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
     * Get alertType
     * @return alertType
     */
    @get:Schema(required = true, description = "")
    @JsonProperty("alertType")
    var alertType: AlertTypeEnum? = null

    @JsonProperty("startAndEndTimes")
    private var startAndEndTimes: @Valid MutableList<List<OffsetDateTime>>? = null

    @JsonProperty("stationIds")
    private var stationIds: @Valid MutableList<String>? = null

    @JsonProperty("regionId")
    private var regionId: @Valid MutableList<String>? = null

    /**
     * URL where the customer can learn more information about this alert, if there is one
     * @return url
     */
    @get:Schema(
        example = "http://www.rentmyfreebike.com/alerts",
        description = "URL where the customer can learn more information about this alert, if there is one"
    )
    @JsonProperty("url")
    var url: String? = null

    /**
     * A short summary of this alert to be displayed to the customer, should match Content-Language
     * @return summary
     */
    @get:Schema(
        example = "station closed",
        required = true,
        description = "A short summary of this alert to be displayed to the customer, should match Content-Language"
    )
    @JsonProperty("summary")
    var summary: String? = null

    /**
     * Detailed text description of the alert, should match Content-Language
     * @return description
     */
    @get:Schema(
        example = "station closed indefinitely due to vandalism",
        description = "Detailed text description of the alert, should match Content-Language"
    )
    @JsonProperty("description")
    var description: String? = null

    /**
     * Get lastUpdated
     * @return lastUpdated
     */
    @get:Schema(description = "")
    @JsonProperty("lastUpdated")
    var lastUpdated: OffsetDateTime? = null
    fun alertId(alertId: String?): SystemAlert {
        this.alertId = alertId
        return this
    }

    fun alertType(alertType: AlertTypeEnum?): SystemAlert {
        this.alertType = alertType
        return this
    }

    fun startAndEndTimes(startAndEndTimes: List<List<OffsetDateTime>>?): SystemAlert {
        this.startAndEndTimes = startAndEndTimes
        return this
    }

    fun addStartAndEndTimesItem(startAndEndTimesItem: List<OffsetDateTime>): SystemAlert {
        if (startAndEndTimes == null) {
            startAndEndTimes = ArrayList()
        }
        startAndEndTimes!!.add(startAndEndTimesItem)
        return this
    }

    /**
     * Array of hashes with the keys \"start\" and \"end\" indicating when the alert is in effect (e.g. when the system or station is actually closed, or when it is scheduled to be moved). If this array is omitted then the alert should be displayed as long as it is in the feed.
     * @return startAndEndTimes
     */
    @Schema(description = "Array of hashes with the keys \"start\" and \"end\" indicating when the alert is in effect (e.g. when the system or station is actually closed, or when it is scheduled to be moved). If this array is omitted then the alert should be displayed as long as it is in the feed.")
    fun getStartAndEndTimes(): @Valid MutableList<List<OffsetDateTime>>? {
        return startAndEndTimes
    }

    fun setStartAndEndTimes(startAndEndTimes: List<List<OffsetDateTime>>?) {
        this.startAndEndTimes = startAndEndTimes
    }

    fun stationIds(stationIds: List<String>?): SystemAlert {
        this.stationIds = stationIds
        return this
    }

    fun addStationIdsItem(stationIdsItem: String): SystemAlert {
        if (stationIds == null) {
            stationIds = ArrayList()
        }
        stationIds!!.add(stationIdsItem)
        return this
    }

    /**
     * Array of strings - If this is an alert that affects one or more stations, include their ids, otherwise omit this field. If both stationIDs and regionIDs are omitted, assume this alert affects the entire system
     * @return stationIds
     */
    @Schema(
        example = "[\"stationID0001\"]",
        description = "Array of strings - If this is an alert that affects one or more stations, include their ids, otherwise omit this field. If both stationIDs and regionIDs are omitted, assume this alert affects the entire system"
    )
    fun getStationIds(): List<String>? {
        return stationIds
    }

    fun setStationIds(stationIds: List<String>?) {
        this.stationIds = stationIds
    }

    fun regionId(regionId: List<String>?): SystemAlert {
        this.regionId = regionId
        return this
    }

    fun addRegionIdItem(regionIdItem: String): SystemAlert {
        if (regionId == null) {
            regionId = ArrayList()
        }
        regionId!!.add(regionIdItem)
        return this
    }

    /**
     * Array of strings - If this system has regions, and if this alert only affects certain regions, include their ids, otherwise, omit this field. If both stationIDs and regionIDs are omitted, assume this alert affects the entire system
     * @return regionId
     */
    @Schema(
        example = "[\"regionID0001\"]",
        description = "Array of strings - If this system has regions, and if this alert only affects certain regions, include their ids, otherwise, omit this field. If both stationIDs and regionIDs are omitted, assume this alert affects the entire system"
    )
    fun getRegionId(): List<String>? {
        return regionId
    }

    fun setRegionId(regionId: List<String>?) {
        this.regionId = regionId
    }

    fun url(url: String?): SystemAlert {
        this.url = url
        return this
    }

    fun summary(summary: String?): SystemAlert {
        this.summary = summary
        return this
    }

    fun description(description: String?): SystemAlert {
        this.description = description
        return this
    }

    fun lastUpdated(lastUpdated: OffsetDateTime?): SystemAlert {
        this.lastUpdated = lastUpdated
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemAlert = o as SystemAlert
        return alertId == systemAlert.alertId && alertType == systemAlert.alertType && startAndEndTimes == systemAlert.startAndEndTimes && stationIds == systemAlert.stationIds && regionId == systemAlert.regionId && url == systemAlert.url && summary == systemAlert.summary && description == systemAlert.description && lastUpdated == systemAlert.lastUpdated
    }

    override fun hashCode(): Int {
        return Objects.hash(alertId, alertType, startAndEndTimes, stationIds, regionId, url, summary, description, lastUpdated)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemAlert {\n")
        sb.append("    alertId: ").append(toIndentedString(alertId)).append("\n")
        sb.append("    alertType: ").append(toIndentedString(alertType)).append("\n")
        sb.append("    startAndEndTimes: ").append(toIndentedString(startAndEndTimes)).append("\n")
        sb.append("    stationIds: ").append(toIndentedString(stationIds)).append("\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n")
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
