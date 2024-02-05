package io.swagger.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.AssetType
import io.swagger.model.EndpointImplementation
import io.swagger.model.StationInformation
import io.swagger.model.SystemAlert
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
import java.io.IOException
import javax.annotation.Generated
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
@RestController
open class OperatorApiController @Autowired constructor(private val objectMapper: ObjectMapper, private val request: HttpServletRequest) :
    OperatorApi {
    override fun operatorAlertsGet(
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
    ): ResponseEntity<List<SystemAlert>> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"summary\" : \"station closed\",\n  \"lastUpdated\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"alertType\" : \"SYSTEM_CLOSURE\",\n  \"startAndEndTimes\" : [ [ \"2000-01-23T04:56:07.000+00:00\", \"2000-01-23T04:56:07.000+00:00\" ], [ \"2000-01-23T04:56:07.000+00:00\", \"2000-01-23T04:56:07.000+00:00\" ] ],\n  \"stationIds\" : [ \"stationID0001\" ],\n  \"regionId\" : [ \"regionID0001\" ],\n  \"description\" : \"station closed indefinitely due to vandalism\",\n  \"alertId\" : \"alertId\",\n  \"url\" : \"http://www.rentmyfreebike.com/alerts\"\n}, {\n  \"summary\" : \"station closed\",\n  \"lastUpdated\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"alertType\" : \"SYSTEM_CLOSURE\",\n  \"startAndEndTimes\" : [ [ \"2000-01-23T04:56:07.000+00:00\", \"2000-01-23T04:56:07.000+00:00\" ], [ \"2000-01-23T04:56:07.000+00:00\", \"2000-01-23T04:56:07.000+00:00\" ] ],\n  \"stationIds\" : [ \"stationID0001\" ],\n  \"regionId\" : [ \"regionID0001\" ],\n  \"description\" : \"station closed indefinitely due to vandalism\",\n  \"alertId\" : \"alertId\",\n  \"url\" : \"http://www.rentmyfreebike.com/alerts\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"assets\" : [ {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  }, {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  } ],\n  \"applicablePricings\" : [ {\n    \"fare\" : {\n      \"estimated\" : true,\n      \"parts\" : [ \"\", \"\" ],\n      \"description\" : \"description\",\n      \"class\" : \"class\"\n    },\n    \"regionId\" : \"regionId\",\n    \"name\" : \"Free Plan\",\n    \"isTaxable\" : true,\n    \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n    \"planId\" : \"freeplan1\",\n    \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n    \"stationId\" : \"stationId\"\n  }, {\n    \"fare\" : {\n      \"estimated\" : true,\n      \"parts\" : [ \"\", \"\" ],\n      \"description\" : \"description\",\n      \"class\" : \"class\"\n    },\n    \"regionId\" : \"regionId\",\n    \"name\" : \"Free Plan\",\n    \"isTaxable\" : true,\n    \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n    \"planId\" : \"freeplan1\",\n    \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n    \"stationId\" : \"stationId\"\n  } ],\n  \"nrAvailable\" : 0,\n  \"id\" : \"id\",\n  \"assetClass\" : \"AIR\",\n  \"assetSubClass\" : \"assetSubClass\",\n  \"conditions\" : [ \"\", \"\" ],\n  \"stationId\" : \"stationId\",\n  \"sharedProperties\" : {\n    \"pets\" : true,\n    \"airConditioning\" : true,\n    \"other\" : \"other\",\n    \"fuel\" : \"NONE\",\n    \"travelAbroad\" : true,\n    \"energyLabel\" : \"A\",\n    \"winterTires\" : true,\n    \"undergroundParking\" : true,\n    \"ancillaries\" : [ {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    }, {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    } ],\n    \"smoking\" : true,\n    \"towingHook\" : true,\n    \"model\" : \"model\",\n    \"gearbox\" : \"MANUAL\",\n    \"cargo\" : \"cargo\",\n    \"brand\" : \"brand\",\n    \"gears\" : 7,\n    \"cabrio\" : true,\n    \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n    \"buildingYear\" : 2,\n    \"stateOfCharge\" : 36,\n    \"co2PerKm\" : 0.5637377,\n    \"helmetRequired\" : false,\n    \"propulsion\" : \"MUSCLE\",\n    \"maxSpeed\" : 2,\n    \"nrOfDoors\" : 4,\n    \"infantSeat\" : true,\n    \"persons\" : 1,\n    \"colour\" : \"colour\",\n    \"easyAccessibility\" : \"LIFT\",\n    \"meta\" : \"\",\n    \"name\" : \"name\",\n    \"location\" : {\n      \"name\" : \"name\",\n      \"coordinates\" : {\n        \"lng\" : 6.169639,\n        \"alt\" : 0.14658129,\n        \"lat\" : 52.253279\n      },\n      \"physicalAddress\" : {\n        \"areaReference\" : \"Smallcity, Pinetree county\",\n        \"province\" : \"province\",\n        \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n        \"city\" : \"city\",\n        \"street\" : \"street\",\n        \"postalCode\" : \"postalCode\",\n        \"houseNumber\" : 0,\n        \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n        \"state\" : \"state\",\n        \"houseNumberAddition\" : \"houseNumberAddition\"\n      },\n      \"stopReference\" : [ {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      }, {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      } ],\n      \"stationId\" : \"stationId\",\n      \"extraInfo\" : \"\"\n    },\n    \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n  }\n}, {\n  \"assets\" : [ {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  }, {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  } ],\n  \"applicablePricings\" : [ {\n    \"fare\" : {\n      \"estimated\" : true,\n      \"parts\" : [ \"\", \"\" ],\n      \"description\" : \"description\",\n      \"class\" : \"class\"\n    },\n    \"regionId\" : \"regionId\",\n    \"name\" : \"Free Plan\",\n    \"isTaxable\" : true,\n    \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n    \"planId\" : \"freeplan1\",\n    \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n    \"stationId\" : \"stationId\"\n  }, {\n    \"fare\" : {\n      \"estimated\" : true,\n      \"parts\" : [ \"\", \"\" ],\n      \"description\" : \"description\",\n      \"class\" : \"class\"\n    },\n    \"regionId\" : \"regionId\",\n    \"name\" : \"Free Plan\",\n    \"isTaxable\" : true,\n    \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n    \"planId\" : \"freeplan1\",\n    \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n    \"stationId\" : \"stationId\"\n  } ],\n  \"nrAvailable\" : 0,\n  \"id\" : \"id\",\n  \"assetClass\" : \"AIR\",\n  \"assetSubClass\" : \"assetSubClass\",\n  \"conditions\" : [ \"\", \"\" ],\n  \"stationId\" : \"stationId\",\n  \"sharedProperties\" : {\n    \"pets\" : true,\n    \"airConditioning\" : true,\n    \"other\" : \"other\",\n    \"fuel\" : \"NONE\",\n    \"travelAbroad\" : true,\n    \"energyLabel\" : \"A\",\n    \"winterTires\" : true,\n    \"undergroundParking\" : true,\n    \"ancillaries\" : [ {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    }, {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    } ],\n    \"smoking\" : true,\n    \"towingHook\" : true,\n    \"model\" : \"model\",\n    \"gearbox\" : \"MANUAL\",\n    \"cargo\" : \"cargo\",\n    \"brand\" : \"brand\",\n    \"gears\" : 7,\n    \"cabrio\" : true,\n    \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n    \"buildingYear\" : 2,\n    \"stateOfCharge\" : 36,\n    \"co2PerKm\" : 0.5637377,\n    \"helmetRequired\" : false,\n    \"propulsion\" : \"MUSCLE\",\n    \"maxSpeed\" : 2,\n    \"nrOfDoors\" : 4,\n    \"infantSeat\" : true,\n    \"persons\" : 1,\n    \"colour\" : \"colour\",\n    \"easyAccessibility\" : \"LIFT\",\n    \"meta\" : \"\",\n    \"name\" : \"name\",\n    \"location\" : {\n      \"name\" : \"name\",\n      \"coordinates\" : {\n        \"lng\" : 6.169639,\n        \"alt\" : 0.14658129,\n        \"lat\" : 52.253279\n      },\n      \"physicalAddress\" : {\n        \"areaReference\" : \"Smallcity, Pinetree county\",\n        \"province\" : \"province\",\n        \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n        \"city\" : \"city\",\n        \"street\" : \"street\",\n        \"postalCode\" : \"postalCode\",\n        \"houseNumber\" : 0,\n        \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n        \"state\" : \"state\",\n        \"houseNumberAddition\" : \"houseNumberAddition\"\n      },\n      \"stopReference\" : [ {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      }, {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      } ],\n      \"stationId\" : \"stationId\",\n      \"extraInfo\" : \"\"\n    },\n    \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n  }\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"licenseUrl\" : \"https://www.rentmyfreebike.com/license\",\n  \"systemId\" : \"XXTO0001\",\n  \"discoveryUriAndroid\" : \"discoveryUriAndroid\",\n  \"timezone\" : \"IST\",\n  \"language\" : [ \"fr-FR\", \"fr-FR\" ],\n  \"storeUriAndroid\" : \"https://play.google.com/store/apps/details?id=com.rentmyfreebike.android\",\n  \"discoveryUriIOS\" : \"discoveryUriIOS\",\n  \"storeUriIOS\" : \"itms-apps://itunes.apple.com/app/idcom.rentmyfreebike.ios\",\n  \"operator\" : \"FreeBike\",\n  \"url\" : \"https://www.rentmyfreebike.com\",\n  \"chamberOfCommerceInfo\" : {\n    \"number\" : \"number\",\n    \"place\" : \"place\"\n  },\n  \"purchaseUrl\" : \"https://www.rentmyfreebike.com/purchase\",\n  \"typeOfSystem\" : \"FREE_FLOATING\",\n  \"phoneNumber\" : \"555-12345\",\n  \"name\" : \"FreeBike\",\n  \"feedContactEmail\" : \"\",\n  \"shortName\" : \"FB\",\n  \"conditions\" : \"conditions\",\n  \"assetClasses\" : [ \"AIR\", \"AIR\" ],\n  \"startDate\" : \"2000-01-23\",\n  \"email\" : \"rent@freebike.com\",\n  \"productType\" : \"RENTAL\"\n}",
                        SystemInformation::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun operatorMetaGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the sending maas operator",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "maas-id", required = true) maasId: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<List<EndpointImplementation?>?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"baseUrl\" : \"baseUrl\",\n  \"endpoints\" : [ {\n    \"withoutPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/booking/{id}/events\",\n      \"eventType\" : \"COMMIT\",\n      \"status\" : \"IMPLEMENTED\"\n    },\n    \"withPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/operator/stations\",\n      \"status\" : \"IMPLEMENTED\",\n      \"supportsPaging\" : true,\n      \"maxPageSize\" : 100\n    }\n  }, {\n    \"withoutPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/booking/{id}/events\",\n      \"eventType\" : \"COMMIT\",\n      \"status\" : \"IMPLEMENTED\"\n    },\n    \"withPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/operator/stations\",\n      \"status\" : \"IMPLEMENTED\",\n      \"supportsPaging\" : true,\n      \"maxPageSize\" : 100\n    }\n  } ],\n  \"processIdentifiers\" : {\n    \"planning\" : [ \"planning\", \"planning\" ],\n    \"general\" : [ \"general\", \"general\" ],\n    \"operatorInformation\" : [ \"operatorInformation\", \"operatorInformation\" ],\n    \"booking\" : [ \"booking\", \"booking\" ],\n    \"tripExecution\" : [ \"tripExecution\", \"tripExecution\" ],\n    \"payment\" : [ \"payment\", \"payment\" ],\n    \"support\" : [ \"support\", \"support\" ]\n  },\n  \"scenarios\" : [ \"POSTPONED_COMMIT\", \"POSTPONED_COMMIT\" ],\n  \"version\" : \"version\"\n}, {\n  \"baseUrl\" : \"baseUrl\",\n  \"endpoints\" : [ {\n    \"withoutPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/booking/{id}/events\",\n      \"eventType\" : \"COMMIT\",\n      \"status\" : \"IMPLEMENTED\"\n    },\n    \"withPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/operator/stations\",\n      \"status\" : \"IMPLEMENTED\",\n      \"supportsPaging\" : true,\n      \"maxPageSize\" : 100\n    }\n  }, {\n    \"withoutPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/booking/{id}/events\",\n      \"eventType\" : \"COMMIT\",\n      \"status\" : \"IMPLEMENTED\"\n    },\n    \"withPaging\" : {\n      \"method\" : \"POST\",\n      \"path\" : \"/operator/stations\",\n      \"status\" : \"IMPLEMENTED\",\n      \"supportsPaging\" : true,\n      \"maxPageSize\" : 100\n    }\n  } ],\n  \"processIdentifiers\" : {\n    \"planning\" : [ \"planning\", \"planning\" ],\n    \"general\" : [ \"general\", \"general\" ],\n    \"operatorInformation\" : [ \"operatorInformation\", \"operatorInformation\" ],\n    \"booking\" : [ \"booking\", \"booking\" ],\n    \"tripExecution\" : [ \"tripExecution\", \"tripExecution\" ],\n    \"payment\" : [ \"payment\", \"payment\" ],\n    \"support\" : [ \"support\", \"support\" ]\n  },\n  \"scenarios\" : [ \"POSTPONED_COMMIT\", \"POSTPONED_COMMIT\" ],\n  \"version\" : \"version\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"regionId\" : \"regionId\",\n  \"startMonth\" : 1,\n  \"startDay\" : 1,\n  \"endDay\" : 31,\n  \"startYear\" : 2019,\n  \"endMonth\" : 12,\n  \"endYear\" : 2099,\n  \"stationId\" : \"stationId\"\n}, {\n  \"regionId\" : \"regionId\",\n  \"startMonth\" : 1,\n  \"startDay\" : 1,\n  \"endDay\" : 31,\n  \"startYear\" : 2019,\n  \"endMonth\" : 12,\n  \"endYear\" : 2099,\n  \"stationId\" : \"stationId\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun operatorOperatingHoursGet(
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
    ): ResponseEntity<List<SystemHours?>?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"regionId\" : \"regionId\",\n  \"days\" : [ \"MON\", \"MON\" ],\n  \"startTime\" : \"startTime\",\n  \"userType\" : \"MEMBER\",\n  \"endTime\" : \"endTime\",\n  \"stationId\" : \"stationId\"\n}, {\n  \"regionId\" : \"regionId\",\n  \"days\" : [ \"MON\", \"MON\" ],\n  \"startTime\" : \"startTime\",\n  \"userType\" : \"MEMBER\",\n  \"endTime\" : \"endTime\",\n  \"stationId\" : \"stationId\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun operatorPingGet(
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information",
            required = true,
            schema = Schema
        ) @RequestHeader(value = "Accept-Language", required = true) acceptLanguage: String?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"fare\" : {\n    \"estimated\" : true,\n    \"parts\" : [ \"\", \"\" ],\n    \"description\" : \"description\",\n    \"class\" : \"class\"\n  },\n  \"regionId\" : \"regionId\",\n  \"name\" : \"Free Plan\",\n  \"isTaxable\" : true,\n  \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n  \"planId\" : \"freeplan1\",\n  \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n  \"stationId\" : \"stationId\"\n}, {\n  \"fare\" : {\n    \"estimated\" : true,\n    \"parts\" : [ \"\", \"\" ],\n    \"description\" : \"description\",\n    \"class\" : \"class\"\n  },\n  \"regionId\" : \"regionId\",\n  \"name\" : \"Free Plan\",\n  \"isTaxable\" : true,\n  \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n  \"planId\" : \"freeplan1\",\n  \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n  \"stationId\" : \"stationId\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"serviceArea\" : [ [ [ 1, 1 ], [ 0, 1 ], [ 0, 0 ], [ 1, 0 ], [ 1, 1 ] ] ],\n  \"regionId\" : \"BikeRegion\",\n  \"name\" : \"BikeTown\",\n  \"type\" : \"OPERATING\"\n}, {\n  \"serviceArea\" : [ [ [ 1, 1 ], [ 0, 1 ], [ 0, 0 ], [ 1, 0 ], [ 1, 1 ] ] ],\n  \"regionId\" : \"BikeRegion\",\n  \"name\" : \"BikeTown\",\n  \"type\" : \"OPERATING\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
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
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"rentalUrl\" : \"https://www.rentmyfreebike.com\",\n  \"regionId\" : \"regionId\",\n  \"name\" : \"Island Central\",\n  \"coordinates\" : {\n    \"lng\" : 6.169639,\n    \"alt\" : 0.14658129,\n    \"lat\" : 52.253279\n  },\n  \"physicalAddress\" : {\n    \"areaReference\" : \"Smallcity, Pinetree county\",\n    \"province\" : \"province\",\n    \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n    \"city\" : \"city\",\n    \"street\" : \"street\",\n    \"postalCode\" : \"postalCode\",\n    \"houseNumber\" : 0,\n    \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n    \"state\" : \"state\",\n    \"houseNumberAddition\" : \"houseNumberAddition\"\n  },\n  \"rentalMethods\" : [ \"CREDITCARD\", \"PAYPASS\", \"APPLEPAY\" ],\n  \"crossStreet\" : \"on the corner with Secondary Road\",\n  \"rentalUrlAndroid\" : \"https://www.rentmyfreebikecom/app?sid=1234567890&platform=android\",\n  \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n  \"stationId\" : \"XX:Y:12345678\"\n}, {\n  \"rentalUrl\" : \"https://www.rentmyfreebike.com\",\n  \"regionId\" : \"regionId\",\n  \"name\" : \"Island Central\",\n  \"coordinates\" : {\n    \"lng\" : 6.169639,\n    \"alt\" : 0.14658129,\n    \"lat\" : 52.253279\n  },\n  \"physicalAddress\" : {\n    \"areaReference\" : \"Smallcity, Pinetree county\",\n    \"province\" : \"province\",\n    \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n    \"city\" : \"city\",\n    \"street\" : \"street\",\n    \"postalCode\" : \"postalCode\",\n    \"houseNumber\" : 0,\n    \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n    \"state\" : \"state\",\n    \"houseNumberAddition\" : \"houseNumberAddition\"\n  },\n  \"rentalMethods\" : [ \"CREDITCARD\", \"PAYPASS\", \"APPLEPAY\" ],\n  \"crossStreet\" : \"on the corner with Secondary Road\",\n  \"rentalUrlAndroid\" : \"https://www.rentmyfreebikecom/app?sid=1234567890&platform=android\",\n  \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n  \"stationId\" : \"XX:Y:12345678\"\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    companion object {
        private val log = LoggerFactory.getLogger(OperatorApiController::class.java)
    }
}
