package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.LocalDate
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * SystemInformation
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class SystemInformation {
    /**
     * identifier for this transport system. This should be globally unique (even between different systems)
     * @return systemId
     */
    @JvmField
    @get:Schema(
        example = "XXTO0001",
        required = true,
        description = "identifier for this transport system. This should be globally unique (even between different systems)"
    )
    @JsonProperty("systemId")
    var systemId: String? = null

    @JsonProperty("language")
    private var language: @Valid MutableList<String>? = ArrayList()

    /**
     * Full name of the system to be displayed to customers, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(
        example = "FreeBike",
        required = true,
        description = "Full name of the system to be displayed to customers, could match Content-Language"
    )
    @JsonProperty("name")
    var name: String? = null

    /**
     * Optional abbreviation for a system
     * @return shortName
     */
    @get:Schema(example = "FB", description = "Optional abbreviation for a system")
    @JsonProperty("shortName")
    var shortName: String? = null

    /**
     * Name of the operator of the system, could match Content-Language
     * @return operator
     */
    @JvmField
    @get:Schema(
        example = "FreeBike",
        description = "Name of the operator of the system, could match Content-Language"
    )
    @JsonProperty("operator")
    var operator: String? = null

    /**
     * The URL of the transport operator. The value must be a fully qualified URL that includes http:// or https://, and any special characters in the URL must be correctly escaped.
     * @return url
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com",
        description = "The URL of the transport operator. The value must be a fully qualified URL that includes http:// or https://, and any special characters in the URL must be correctly escaped."
    )
    @JsonProperty("url")
    var url: String? = null

    /**
     * A fully qualified URL where a customer can purchase a membership or learn more about purchasing memberships
     * @return purchaseUrl
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/purchase",
        description = "A fully qualified URL where a customer can purchase a membership or learn more about purchasing memberships"
    )
    @JsonProperty("purchaseUrl")
    var purchaseUrl: String? = null

    /**
     * Uri to detect if the app is available at the mobile.
     * @return discoveryUriAndroid
     */
    @get:Schema(description = "Uri to detect if the app is available at the mobile.")
    @JsonProperty("discoveryUriAndroid")
    var discoveryUriAndroid: String? = null

    /**
     * Uri to detect if the app is available at the mobile.
     * @return discoveryUriIOS
     */
    @get:Schema(description = "Uri to detect if the app is available at the mobile.")
    @JsonProperty("discoveryUriIOS")
    var discoveryUriIOS: String? = null

    /**
     * Uri to the app in the store.
     * @return storeUriAndroid
     */
    @get:Schema(
        example = "https://play.google.com/store/apps/details?id=com.rentmyfreebike.android",
        description = "Uri to the app in the store."
    )
    @JsonProperty("storeUriAndroid")
    var storeUriAndroid: String? = null

    /**
     * Uri to the app in the store.
     * @return storeUriIOS
     */
    @get:Schema(
        example = "itms-apps://itunes.apple.com/app/idcom.rentmyfreebike.ios",
        description = "Uri to the app in the store."
    )
    @JsonProperty("storeUriIOS")
    var storeUriIOS: String? = null

    /**
     * Get startDate
     * @return startDate
     */
    @get:Schema(description = "")
    @JsonProperty("startDate")
    var startDate: LocalDate? = null

    /**
     * A single voice telephone number for the specified system. This field is a string value that presents the telephone number as typical for the system's service area. It can and should contain punctuation marks to group the digits of the number.
     * @return phoneNumber
     */
    @JvmField
    @get:Schema(
        example = "555-12345",
        description = "A single voice telephone number for the specified system. This field is a string value that presents the telephone number as typical for the system's service area. It can and should contain punctuation marks to group the digits of the number."
    )
    @JsonProperty("phoneNumber")
    var phoneNumber: String? = null

    /**
     * A single contact email address for customers to address questions about the system
     * @return email
     */
    @JvmField
    @get:Schema(
        example = "rent@freebike.com",
        description = "A single contact email address for customers to address questions about the system"
    )
    @JsonProperty("email")
    var email: String? = null

    /**
     * A single contact email address for consumers of this feed to report technical issues.
     * @return feedContactEmail
     */
    @get:Schema(description = "A single contact email address for consumers of this feed to report technical issues.")
    @JsonProperty("feedContactEmail")
    var feedContactEmail: String? = null

    /**
     * The time zone where the system is located. Time zone names never contain the space character but may contain an underscore. Please refer to the \"TZ\" value in https://en.wikipedia.org/wiki/List_of_tz_database_time_zones for a list of valid values
     * @return timezone
     */
    @get:Schema(
        example = "IST",
        required = true,
        description = "The time zone where the system is located. Time zone names never contain the space character but may contain an underscore. Please refer to the \"TZ\" value in https://en.wikipedia.org/wiki/List_of_tz_database_time_zones for a list of valid values"
    )
    @JsonProperty("timezone")
    var timezone: String? = null

    /**
     * A fully qualified URL of a page that defines the license terms for the GBFS data for this system, as well as any other license terms the system would like to define (including the use of corporate trademarks, etc)
     * @return licenseUrl
     */
    @get:Schema(
        example = "https://www.rentmyfreebike.com/license",
        description = "A fully qualified URL of a page that defines the license terms for the GBFS data for this system, as well as any other license terms the system would like to define (including the use of corporate trademarks, etc)"
    )
    @JsonProperty("licenseUrl")
    var licenseUrl: String? = null

    /**
     * Describes the type of system
     */
    enum class TypeOfSystemEnum(private val value: String) {
        FREE_FLOATING("FREE_FLOATING"),
        STATION_BASED("STATION_BASED"),
        VIRTUAL_STATION_BASED("VIRTUAL_STATION_BASED");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): TypeOfSystemEnum? {
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
     * Describes the type of system
     * @return typeOfSystem
     */
    @get:Schema(example = "FREE_FLOATING", required = true, description = "Describes the type of system")
    @JsonProperty("typeOfSystem")
    var typeOfSystem: TypeOfSystemEnum? = null

    /**
     * Get chamberOfCommerceInfo
     * @return chamberOfCommerceInfo
     */
    @get:Schema(description = "")
    @JsonProperty("chamberOfCommerceInfo")
    var chamberOfCommerceInfo: ChamberOfCommerceInfo? = null

    /**
     * Added to include possibility to communicatie general rental conditions like minimum age, max. reservation time etc. [amended]
     * @return conditions
     */
    @get:Schema(description = "Added to include possibility to communicatie general rental conditions like minimum age, max. reservation time etc. [amended]")
    @JsonProperty("conditions")
    var conditions: String? = null

    /**
     * the type of product offered. SHARING should also be used for public transport.
     */
    enum class ProductTypeEnum(private val value: String) {
        RENTAL("RENTAL"),
        SHARING("SHARING"),
        PARKING("PARKING"),
        CHARGING("CHARGING");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): ProductTypeEnum? {
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
     * the type of product offered. SHARING should also be used for public transport.
     * @return productType
     */
    @get:Schema(description = "the type of product offered. SHARING should also be used for public transport.")
    @JsonProperty("productType")
    var productType: ProductTypeEnum? = null

    @JsonProperty("assetClasses")
    private var assetClasses: @Valid MutableList<AssetClass>? = null
    fun systemId(systemId: String?): SystemInformation {
        this.systemId = systemId
        return this
    }

    fun language(language: List<String>?): SystemInformation {
        this.language = language
        return this
    }

    fun addLanguageItem(languageItem: String): SystemInformation {
        language!!.add(languageItem)
        return this
    }

    /**
     * The languages supported by this operator for user-facing text. These can be requested using the Accept-Language header and should then be returned in Content-Language
     * @return language
     */
    @Schema(
        required = true,
        description = "The languages supported by this operator for user-facing text. These can be requested using the Accept-Language header and should then be returned in Content-Language"
    )
    fun getLanguage(): @NotNull MutableList<String>? {
        return language
    }

    fun setLanguage(language: List<String>?) {
        this.language = language
    }

    fun name(name: String?): SystemInformation {
        this.name = name
        return this
    }

    fun shortName(shortName: String?): SystemInformation {
        this.shortName = shortName
        return this
    }

    fun operator(operator: String?): SystemInformation {
        this.operator = operator
        return this
    }

    fun url(url: String?): SystemInformation {
        this.url = url
        return this
    }

    fun purchaseUrl(purchaseUrl: String?): SystemInformation {
        this.purchaseUrl = purchaseUrl
        return this
    }

    fun discoveryUriAndroid(discoveryUriAndroid: String?): SystemInformation {
        this.discoveryUriAndroid = discoveryUriAndroid
        return this
    }

    fun discoveryUriIOS(discoveryUriIOS: String?): SystemInformation {
        this.discoveryUriIOS = discoveryUriIOS
        return this
    }

    fun storeUriAndroid(storeUriAndroid: String?): SystemInformation {
        this.storeUriAndroid = storeUriAndroid
        return this
    }

    fun storeUriIOS(storeUriIOS: String?): SystemInformation {
        this.storeUriIOS = storeUriIOS
        return this
    }

    fun startDate(startDate: LocalDate?): SystemInformation {
        this.startDate = startDate
        return this
    }

    fun phoneNumber(phoneNumber: String?): SystemInformation {
        this.phoneNumber = phoneNumber
        return this
    }

    fun email(email: String?): SystemInformation {
        this.email = email
        return this
    }

    fun feedContactEmail(feedContactEmail: String?): SystemInformation {
        this.feedContactEmail = feedContactEmail
        return this
    }

    fun timezone(timezone: String?): SystemInformation {
        this.timezone = timezone
        return this
    }

    fun licenseUrl(licenseUrl: String?): SystemInformation {
        this.licenseUrl = licenseUrl
        return this
    }

    fun typeOfSystem(typeOfSystem: TypeOfSystemEnum?): SystemInformation {
        this.typeOfSystem = typeOfSystem
        return this
    }

    fun chamberOfCommerceInfo(chamberOfCommerceInfo: ChamberOfCommerceInfo?): SystemInformation {
        this.chamberOfCommerceInfo = chamberOfCommerceInfo
        return this
    }

    fun conditions(conditions: String?): SystemInformation {
        this.conditions = conditions
        return this
    }

    fun productType(productType: ProductTypeEnum?): SystemInformation {
        this.productType = productType
        return this
    }

    fun assetClasses(assetClasses: List<AssetClass>?): SystemInformation {
        this.assetClasses = assetClasses
        return this
    }

    fun addAssetClassesItem(assetClassesItem: AssetClass): SystemInformation {
        if (assetClasses == null) {
            assetClasses = ArrayList()
        }
        assetClasses!!.add(assetClassesItem)
        return this
    }

    /**
     * Get assetClasses
     * @return assetClasses
     */
    @Schema(description = "")
    fun getAssetClasses(): @Valid MutableList<AssetClass>? {
        return assetClasses
    }

    fun setAssetClasses(assetClasses: List<AssetClass>?) {
        this.assetClasses = assetClasses
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemInformation = o as SystemInformation
        return systemId == systemInformation.systemId && language == systemInformation.language && name == systemInformation.name && shortName == systemInformation.shortName && operator == systemInformation.operator && url == systemInformation.url && purchaseUrl == systemInformation.purchaseUrl && discoveryUriAndroid == systemInformation.discoveryUriAndroid && discoveryUriIOS == systemInformation.discoveryUriIOS && storeUriAndroid == systemInformation.storeUriAndroid && storeUriIOS == systemInformation.storeUriIOS && startDate == systemInformation.startDate && phoneNumber == systemInformation.phoneNumber && email == systemInformation.email && feedContactEmail == systemInformation.feedContactEmail && timezone == systemInformation.timezone && licenseUrl == systemInformation.licenseUrl && typeOfSystem == systemInformation.typeOfSystem && chamberOfCommerceInfo == systemInformation.chamberOfCommerceInfo && conditions == systemInformation.conditions && productType == systemInformation.productType && assetClasses == systemInformation.assetClasses
    }

    override fun hashCode(): Int {
        return Objects.hash(
            systemId,
            language,
            name,
            shortName,
            operator,
            url,
            purchaseUrl,
            discoveryUriAndroid,
            discoveryUriIOS,
            storeUriAndroid,
            storeUriIOS,
            startDate,
            phoneNumber,
            email,
            feedContactEmail,
            timezone,
            licenseUrl,
            typeOfSystem,
            chamberOfCommerceInfo,
            conditions,
            productType,
            assetClasses
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemInformation {\n")
        sb.append("    systemId: ").append(toIndentedString(systemId)).append("\n")
        sb.append("    language: ").append(toIndentedString(language)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n")
        sb.append("    operator: ").append(toIndentedString(operator)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    purchaseUrl: ").append(toIndentedString(purchaseUrl)).append("\n")
        sb.append("    discoveryUriAndroid: ").append(toIndentedString(discoveryUriAndroid)).append("\n")
        sb.append("    discoveryUriIOS: ").append(toIndentedString(discoveryUriIOS)).append("\n")
        sb.append("    storeUriAndroid: ").append(toIndentedString(storeUriAndroid)).append("\n")
        sb.append("    storeUriIOS: ").append(toIndentedString(storeUriIOS)).append("\n")
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n")
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n")
        sb.append("    email: ").append(toIndentedString(email)).append("\n")
        sb.append("    feedContactEmail: ").append(toIndentedString(feedContactEmail)).append("\n")
        sb.append("    timezone: ").append(toIndentedString(timezone)).append("\n")
        sb.append("    licenseUrl: ").append(toIndentedString(licenseUrl)).append("\n")
        sb.append("    typeOfSystem: ").append(toIndentedString(typeOfSystem)).append("\n")
        sb.append("    chamberOfCommerceInfo: ").append(toIndentedString(chamberOfCommerceInfo)).append("\n")
        sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n")
        sb.append("    productType: ").append(toIndentedString(productType)).append("\n")
        sb.append("    assetClasses: ").append(toIndentedString(assetClasses)).append("\n")
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
