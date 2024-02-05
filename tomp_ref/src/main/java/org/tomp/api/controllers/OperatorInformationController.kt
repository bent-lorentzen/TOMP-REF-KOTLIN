package org.tomp.api.controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.api.OperatorApiController
import io.swagger.model.AssetType
import io.swagger.model.EndpointImplementation
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.tomp.api.operatorinformation.OperatorInformationProvider
import org.tomp.api.utils.HeaderValidator
import org.tomp.api.utils.RouterUtil
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min

@RestController
class OperatorInformationController(private val objectMapper: ObjectMapper, request: HttpServletRequest) : OperatorApiController(
    objectMapper, request
) {
    @Autowired
    private val provider: OperatorInformationProvider? = null

    @Autowired
    private val routerUtil: RouterUtil? = null
    private val request: HttpServletRequest

    init {
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.request = request
    }

    override fun operatorAvailableAssetsGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "start of the selection",
            schema = Schema(allowableValues = [], defaultValue = "0")
        ) @RequestParam(value = "offset", required = false, defaultValue = "0") offset: @Min(0) @Valid Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "count of the selection",
            schema = Schema(allowableValues = [])
        ) @RequestParam(value = "limit", required = false) limit: @Min(0) @Valid Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the region to use in the filter (/operator/regions)",
            schema = Schema
        ) @RequestParam(value = "regionId", required = false) regionId: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the station to use in the filter (/operator/stations)",
            schema = Schema
        ) @RequestParam(value = "stationId", required = false) stationId: @Valid String?
    ): ResponseEntity<List<AssetType?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val list = provider!!.getAvailableAssetTypes(acceptLanguage)
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/available-assets", null,
                request.getHeader("MPID")
            )
            ResponseEntity(list, headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorAvailableAssetsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorInformationGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<SystemInformation?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/information", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getOperatorInformation(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorInformationGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorStationsGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "start of the selection",
            schema = Schema(allowableValues = [], defaultValue = "0")
        ) @RequestParam(value = "offset", required = false, defaultValue = "0") offset: @Min(0) @Valid Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "count of the selection",
            schema = Schema(allowableValues = [])
        ) @RequestParam(value = "limit", required = false) limit: @Min(0) @Valid Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the region to use in the filter (/operator/regions)",
            schema = Schema
        ) @RequestParam(value = "regionId", required = false) regionId: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "the longitude of the search location (WGS84)",
            schema = Schema
        ) @RequestParam(value = "lon", required = false) lon: @DecimalMin("0") @Valid Float?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "the latitude of the search location (WGS84)",
            schema = Schema
        ) @RequestParam(value = "lat", required = false) lat: @DecimalMin("0") @Valid Float?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "the range in meters from the search location to look for stations",
            schema = Schema
        ) @RequestParam(value = "radius", required = false) radius: @DecimalMin("0") @Valid Float?
    ): ResponseEntity<List<StationInformation?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/stations", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getStations(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorStationsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorRegionsGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "start of the selection",
            schema = Schema(allowableValues = [], defaultValue = "0")
        ) @RequestParam(value = "offset", required = false, defaultValue = "0") offset: @Min(0) @Valid Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "count of the selection",
            schema = Schema(allowableValues = [])
        ) @RequestParam(value = "limit", required = false) limit: @Min(0) @Valid Int?
    ): ResponseEntity<List<SystemRegion?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/regions", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getRegions(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorRegionsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorPricingPlansGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the region to use in the filter (/operator/regions)",
            schema = Schema
        ) @RequestParam(value = "regionId", required = false) regionId: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the station to use in the filter (/operator/stations)",
            schema = Schema
        ) @RequestParam(value = "stationId", required = false) stationId: @Valid String?
    ): ResponseEntity<List<SystemPricingPlan?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/pricing-plans", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getPricingPlans(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorRegionsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorOperatingHoursGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = false,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = false,
            schema = Schema
        ) @RequestHeader(value = "Api", required = false) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = false) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = false
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the region to use in the filter (/operator/regions)",
            schema = Schema
        ) @RequestParam(value = "regionId", required = false) regionId: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the station to use in the filter (/operator/stations)",
            schema = Schema
        ) @RequestParam(value = "stationId", required = false) stationId: @Valid String?
    ): ResponseEntity<List<SystemHours?>?> {
        log.info("GET /operator/operating-hours")
        // HeaderValidator.validateHeader(request);
        log.info("GET /operator/operating-hours - headers validated")
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/operating-hours", null,
                request.getHeader("MPID")
            )
            log.info("GET /operator/operating-hours - return headers created")
            ResponseEntity(provider!!.getHours(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorRegionsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorOperatingCalendarGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "API description, can be TOMP or maybe other (specific/derived) API definitions",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api", required = true) api: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "Version of the API.",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Api-Version", required = true) apiVersion: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the region to use in the filter (/operator/regions)",
            schema = Schema
        ) @RequestParam(value = "regionId", required = false) regionId: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "optional id of the station to use in the filter (/operator/stations)",
            schema = Schema
        ) @RequestParam(value = "stationId", required = false) stationId: @Valid String?
    ): ResponseEntity<List<SystemCalendar?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/operating-calendar", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getCalendar(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorRegionsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    override fun operatorMetaGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(`in` = ParameterIn.HEADER, description = "The ID of the sending maas operator", required = true, schema = Schema) @RequestHeader(
            value = "maas-id",
            required = true
        ) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<List<EndpointImplementation?>?> {
        HeaderValidator.Companion.validateHeader(request)
        return try {
            val headers = routerUtil!!.createHeadersToMP(
                "GET", "/operator/meta", null,
                request.getHeader("MPID")
            )
            ResponseEntity(provider!!.getMeta(acceptLanguage), headers, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("operatorRegionsGet", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(OperatorInformationController::class.java)
    }
}
