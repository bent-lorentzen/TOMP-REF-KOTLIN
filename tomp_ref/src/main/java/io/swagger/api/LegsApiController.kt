package io.swagger.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.Asset
import io.swagger.model.ConfirmationRequest
import io.swagger.model.Leg
import io.swagger.model.LegEvent
import io.swagger.model.LegProgress
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.annotation.Generated
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.Min

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
@RestController
open class LegsApiController @Autowired constructor(private val objectMapper: ObjectMapper, private val request: HttpServletRequest) : LegsApi {
    override fun legsIdAncillariesCategoryNumberDelete(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "ancillary category",
            required = true,
            schema = Schema
        ) @PathVariable("category") category: String?,
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "ancillary number",
            required = true,
            schema = Schema
        ) @PathVariable("number") number: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Leg> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"departureTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"allAssetAccessData\" : [ null, null ],\n  \"progressGeometry\" : [ [ 6.169639, 52.253279 ], [ 6.05623, 52.63473 ] ],\n  \"distance\" : 7250,\n  \"ticket\" : {\n    \"tokenData\" : \"\",\n    \"validUntil\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"validFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"tokenType\" : \"tokenDefault\"\n  },\n  \"legSequenceNumber\" : 6,\n  \"travelerReferenceNumbers\" : [ \"travelerReferenceNumbers\", \"travelerReferenceNumbers\" ],\n  \"suboperator\" : {\n    \"maasId\" : \"maasId\",\n    \"contact\" : \"contact\",\n    \"name\" : \"name\",\n    \"description\" : \"description\"\n  },\n  \"departureDelay\" : 11112,\n  \"assetType\" : {\n    \"assets\" : [ {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    }, {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    } ],\n    \"applicablePricings\" : [ {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    }, {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    } ],\n    \"nrAvailable\" : 0,\n    \"id\" : \"id\",\n    \"assetClass\" : \"AIR\",\n    \"assetSubClass\" : \"assetSubClass\",\n    \"conditions\" : [ \"\", \"\" ],\n    \"stationId\" : \"stationId\",\n    \"sharedProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    }\n  },\n  \"arrivalTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"from\" : {\n    \"name\" : \"name\",\n    \"coordinates\" : {\n      \"lng\" : 6.169639,\n      \"alt\" : 0.14658129,\n      \"lat\" : 52.253279\n    },\n    \"physicalAddress\" : {\n      \"areaReference\" : \"Smallcity, Pinetree county\",\n      \"province\" : \"province\",\n      \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n      \"city\" : \"city\",\n      \"street\" : \"street\",\n      \"postalCode\" : \"postalCode\",\n      \"houseNumber\" : 0,\n      \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n      \"state\" : \"state\",\n      \"houseNumberAddition\" : \"houseNumberAddition\"\n    },\n    \"stopReference\" : [ {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    }, {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    } ],\n    \"stationId\" : \"stationId\",\n    \"extraInfo\" : \"\"\n  },\n  \"id\" : \"id\",\n  \"state\" : \"NOT_STARTED\",\n  \"conditions\" : [ \"\", \"\" ]\n}",
                        Leg::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdAncillariesCategoryNumberPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "ancillary category",
            required = true,
            schema = Schema
        ) @PathVariable("category") category: String?,
        @Parameter(
            `in` = ParameterIn.PATH,
            description = "ancillary number",
            required = true,
            schema = Schema
        ) @PathVariable("number") number: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Leg> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"departureTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"allAssetAccessData\" : [ null, null ],\n  \"progressGeometry\" : [ [ 6.169639, 52.253279 ], [ 6.05623, 52.63473 ] ],\n  \"distance\" : 7250,\n  \"ticket\" : {\n    \"tokenData\" : \"\",\n    \"validUntil\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"validFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"tokenType\" : \"tokenDefault\"\n  },\n  \"legSequenceNumber\" : 6,\n  \"travelerReferenceNumbers\" : [ \"travelerReferenceNumbers\", \"travelerReferenceNumbers\" ],\n  \"suboperator\" : {\n    \"maasId\" : \"maasId\",\n    \"contact\" : \"contact\",\n    \"name\" : \"name\",\n    \"description\" : \"description\"\n  },\n  \"departureDelay\" : 11112,\n  \"assetType\" : {\n    \"assets\" : [ {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    }, {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    } ],\n    \"applicablePricings\" : [ {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    }, {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    } ],\n    \"nrAvailable\" : 0,\n    \"id\" : \"id\",\n    \"assetClass\" : \"AIR\",\n    \"assetSubClass\" : \"assetSubClass\",\n    \"conditions\" : [ \"\", \"\" ],\n    \"stationId\" : \"stationId\",\n    \"sharedProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    }\n  },\n  \"arrivalTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"from\" : {\n    \"name\" : \"name\",\n    \"coordinates\" : {\n      \"lng\" : 6.169639,\n      \"alt\" : 0.14658129,\n      \"lat\" : 52.253279\n    },\n    \"physicalAddress\" : {\n      \"areaReference\" : \"Smallcity, Pinetree county\",\n      \"province\" : \"province\",\n      \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n      \"city\" : \"city\",\n      \"street\" : \"street\",\n      \"postalCode\" : \"postalCode\",\n      \"houseNumber\" : 0,\n      \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n      \"state\" : \"state\",\n      \"houseNumberAddition\" : \"houseNumberAddition\"\n    },\n    \"stopReference\" : [ {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    }, {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    } ],\n    \"stationId\" : \"stationId\",\n    \"extraInfo\" : \"\"\n  },\n  \"id\" : \"id\",\n  \"state\" : \"NOT_STARTED\",\n  \"conditions\" : [ \"\", \"\" ]\n}",
                        Leg::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdAvailableAssetsGet(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
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
    ): ResponseEntity<List<Asset>> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"overriddenProperties\" : {\n    \"pets\" : true,\n    \"airConditioning\" : true,\n    \"other\" : \"other\",\n    \"fuel\" : \"NONE\",\n    \"travelAbroad\" : true,\n    \"energyLabel\" : \"A\",\n    \"winterTires\" : true,\n    \"undergroundParking\" : true,\n    \"ancillaries\" : [ {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    }, {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    } ],\n    \"smoking\" : true,\n    \"towingHook\" : true,\n    \"model\" : \"model\",\n    \"gearbox\" : \"MANUAL\",\n    \"cargo\" : \"cargo\",\n    \"brand\" : \"brand\",\n    \"gears\" : 7,\n    \"cabrio\" : true,\n    \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n    \"buildingYear\" : 2,\n    \"stateOfCharge\" : 36,\n    \"co2PerKm\" : 0.5637377,\n    \"helmetRequired\" : false,\n    \"propulsion\" : \"MUSCLE\",\n    \"maxSpeed\" : 2,\n    \"nrOfDoors\" : 4,\n    \"infantSeat\" : true,\n    \"persons\" : 1,\n    \"colour\" : \"colour\",\n    \"easyAccessibility\" : \"LIFT\",\n    \"meta\" : \"\",\n    \"name\" : \"name\",\n    \"location\" : {\n      \"name\" : \"name\",\n      \"coordinates\" : {\n        \"lng\" : 6.169639,\n        \"alt\" : 0.14658129,\n        \"lat\" : 52.253279\n      },\n      \"physicalAddress\" : {\n        \"areaReference\" : \"Smallcity, Pinetree county\",\n        \"province\" : \"province\",\n        \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n        \"city\" : \"city\",\n        \"street\" : \"street\",\n        \"postalCode\" : \"postalCode\",\n        \"houseNumber\" : 0,\n        \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n        \"state\" : \"state\",\n        \"houseNumberAddition\" : \"houseNumberAddition\"\n      },\n      \"stopReference\" : [ {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      }, {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      } ],\n      \"stationId\" : \"stationId\",\n      \"extraInfo\" : \"\"\n    },\n    \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n  },\n  \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n  \"licensePlate\" : \"licensePlate\",\n  \"isReserved\" : true,\n  \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"id\" : \"id\",\n  \"isDisabled\" : true,\n  \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n  \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n  \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"mileage\" : 0.6027456\n}, {\n  \"overriddenProperties\" : {\n    \"pets\" : true,\n    \"airConditioning\" : true,\n    \"other\" : \"other\",\n    \"fuel\" : \"NONE\",\n    \"travelAbroad\" : true,\n    \"energyLabel\" : \"A\",\n    \"winterTires\" : true,\n    \"undergroundParking\" : true,\n    \"ancillaries\" : [ {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    }, {\n      \"number\" : \"number\",\n      \"variable-number\" : 0,\n      \"memo\" : \"memo\",\n      \"source\" : \"source\",\n      \"category\" : \"category\",\n      \"type\" : \"type\",\n      \"applicable-days\" : [ \"MO\", \"MO\" ]\n    } ],\n    \"smoking\" : true,\n    \"towingHook\" : true,\n    \"model\" : \"model\",\n    \"gearbox\" : \"MANUAL\",\n    \"cargo\" : \"cargo\",\n    \"brand\" : \"brand\",\n    \"gears\" : 7,\n    \"cabrio\" : true,\n    \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n    \"buildingYear\" : 2,\n    \"stateOfCharge\" : 36,\n    \"co2PerKm\" : 0.5637377,\n    \"helmetRequired\" : false,\n    \"propulsion\" : \"MUSCLE\",\n    \"maxSpeed\" : 2,\n    \"nrOfDoors\" : 4,\n    \"infantSeat\" : true,\n    \"persons\" : 1,\n    \"colour\" : \"colour\",\n    \"easyAccessibility\" : \"LIFT\",\n    \"meta\" : \"\",\n    \"name\" : \"name\",\n    \"location\" : {\n      \"name\" : \"name\",\n      \"coordinates\" : {\n        \"lng\" : 6.169639,\n        \"alt\" : 0.14658129,\n        \"lat\" : 52.253279\n      },\n      \"physicalAddress\" : {\n        \"areaReference\" : \"Smallcity, Pinetree county\",\n        \"province\" : \"province\",\n        \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n        \"city\" : \"city\",\n        \"street\" : \"street\",\n        \"postalCode\" : \"postalCode\",\n        \"houseNumber\" : 0,\n        \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n        \"state\" : \"state\",\n        \"houseNumberAddition\" : \"houseNumberAddition\"\n      },\n      \"stopReference\" : [ {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      }, {\n        \"country\" : \"NL\",\n        \"id\" : \"id\",\n        \"type\" : \"GTFS_STOP_ID\"\n      } ],\n      \"stationId\" : \"stationId\",\n      \"extraInfo\" : \"\"\n    },\n    \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n  },\n  \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n  \"licensePlate\" : \"licensePlate\",\n  \"isReserved\" : true,\n  \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"id\" : \"id\",\n  \"isDisabled\" : true,\n  \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n  \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n  \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"mileage\" : 0.6027456\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdConfirmationPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid ConfirmationRequest?
    ): ResponseEntity<Boolean> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("true", Boolean::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdEventsPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid LegEvent?
    ): ResponseEntity<Leg?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"departureTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"allAssetAccessData\" : [ null, null ],\n  \"progressGeometry\" : [ [ 6.169639, 52.253279 ], [ 6.05623, 52.63473 ] ],\n  \"distance\" : 7250,\n  \"ticket\" : {\n    \"tokenData\" : \"\",\n    \"validUntil\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"validFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"tokenType\" : \"tokenDefault\"\n  },\n  \"legSequenceNumber\" : 6,\n  \"travelerReferenceNumbers\" : [ \"travelerReferenceNumbers\", \"travelerReferenceNumbers\" ],\n  \"suboperator\" : {\n    \"maasId\" : \"maasId\",\n    \"contact\" : \"contact\",\n    \"name\" : \"name\",\n    \"description\" : \"description\"\n  },\n  \"departureDelay\" : 11112,\n  \"assetType\" : {\n    \"assets\" : [ {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    }, {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    } ],\n    \"applicablePricings\" : [ {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    }, {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    } ],\n    \"nrAvailable\" : 0,\n    \"id\" : \"id\",\n    \"assetClass\" : \"AIR\",\n    \"assetSubClass\" : \"assetSubClass\",\n    \"conditions\" : [ \"\", \"\" ],\n    \"stationId\" : \"stationId\",\n    \"sharedProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    }\n  },\n  \"arrivalTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"from\" : {\n    \"name\" : \"name\",\n    \"coordinates\" : {\n      \"lng\" : 6.169639,\n      \"alt\" : 0.14658129,\n      \"lat\" : 52.253279\n    },\n    \"physicalAddress\" : {\n      \"areaReference\" : \"Smallcity, Pinetree county\",\n      \"province\" : \"province\",\n      \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n      \"city\" : \"city\",\n      \"street\" : \"street\",\n      \"postalCode\" : \"postalCode\",\n      \"houseNumber\" : 0,\n      \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n      \"state\" : \"state\",\n      \"houseNumberAddition\" : \"houseNumberAddition\"\n    },\n    \"stopReference\" : [ {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    }, {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    } ],\n    \"stationId\" : \"stationId\",\n    \"extraInfo\" : \"\"\n  },\n  \"id\" : \"id\",\n  \"state\" : \"NOT_STARTED\",\n  \"conditions\" : [ \"\", \"\" ]\n}",
                        Leg::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdGet(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Leg> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"departureTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"allAssetAccessData\" : [ null, null ],\n  \"progressGeometry\" : [ [ 6.169639, 52.253279 ], [ 6.05623, 52.63473 ] ],\n  \"distance\" : 7250,\n  \"ticket\" : {\n    \"tokenData\" : \"\",\n    \"validUntil\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"validFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"tokenType\" : \"tokenDefault\"\n  },\n  \"legSequenceNumber\" : 6,\n  \"travelerReferenceNumbers\" : [ \"travelerReferenceNumbers\", \"travelerReferenceNumbers\" ],\n  \"suboperator\" : {\n    \"maasId\" : \"maasId\",\n    \"contact\" : \"contact\",\n    \"name\" : \"name\",\n    \"description\" : \"description\"\n  },\n  \"departureDelay\" : 11112,\n  \"assetType\" : {\n    \"assets\" : [ {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    }, {\n      \"overriddenProperties\" : {\n        \"pets\" : true,\n        \"airConditioning\" : true,\n        \"other\" : \"other\",\n        \"fuel\" : \"NONE\",\n        \"travelAbroad\" : true,\n        \"energyLabel\" : \"A\",\n        \"winterTires\" : true,\n        \"undergroundParking\" : true,\n        \"ancillaries\" : [ {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        }, {\n          \"number\" : \"number\",\n          \"variable-number\" : 0,\n          \"memo\" : \"memo\",\n          \"source\" : \"source\",\n          \"category\" : \"category\",\n          \"type\" : \"type\",\n          \"applicable-days\" : [ \"MO\", \"MO\" ]\n        } ],\n        \"smoking\" : true,\n        \"towingHook\" : true,\n        \"model\" : \"model\",\n        \"gearbox\" : \"MANUAL\",\n        \"cargo\" : \"cargo\",\n        \"brand\" : \"brand\",\n        \"gears\" : 7,\n        \"cabrio\" : true,\n        \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n        \"buildingYear\" : 2,\n        \"stateOfCharge\" : 36,\n        \"co2PerKm\" : 0.5637377,\n        \"helmetRequired\" : false,\n        \"propulsion\" : \"MUSCLE\",\n        \"maxSpeed\" : 2,\n        \"nrOfDoors\" : 4,\n        \"infantSeat\" : true,\n        \"persons\" : 1,\n        \"colour\" : \"colour\",\n        \"easyAccessibility\" : \"LIFT\",\n        \"meta\" : \"\",\n        \"name\" : \"name\",\n        \"location\" : {\n          \"name\" : \"name\",\n          \"coordinates\" : {\n            \"lng\" : 6.169639,\n            \"alt\" : 0.14658129,\n            \"lat\" : 52.253279\n          },\n          \"physicalAddress\" : {\n            \"areaReference\" : \"Smallcity, Pinetree county\",\n            \"province\" : \"province\",\n            \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n            \"city\" : \"city\",\n            \"street\" : \"street\",\n            \"postalCode\" : \"postalCode\",\n            \"houseNumber\" : 0,\n            \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n            \"state\" : \"state\",\n            \"houseNumberAddition\" : \"houseNumberAddition\"\n          },\n          \"stopReference\" : [ {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          }, {\n            \"country\" : \"NL\",\n            \"id\" : \"id\",\n            \"type\" : \"GTFS_STOP_ID\"\n          } ],\n          \"stationId\" : \"stationId\",\n          \"extraInfo\" : \"\"\n        },\n        \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n      },\n      \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n      \"licensePlate\" : \"licensePlate\",\n      \"isReserved\" : true,\n      \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"id\" : \"id\",\n      \"isDisabled\" : true,\n      \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n      \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n      \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"mileage\" : 0.6027456\n    } ],\n    \"applicablePricings\" : [ {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    }, {\n      \"fare\" : {\n        \"estimated\" : true,\n        \"parts\" : [ \"\", \"\" ],\n        \"description\" : \"description\",\n        \"class\" : \"class\"\n      },\n      \"regionId\" : \"regionId\",\n      \"name\" : \"Free Plan\",\n      \"isTaxable\" : true,\n      \"description\" : \"Unlimited plan for free bikes, as long as you don't break them!\",\n      \"planId\" : \"freeplan1\",\n      \"url\" : \"https://www.rentmyfreebike.com/freeplan\",\n      \"stationId\" : \"stationId\"\n    } ],\n    \"nrAvailable\" : 0,\n    \"id\" : \"id\",\n    \"assetClass\" : \"AIR\",\n    \"assetSubClass\" : \"assetSubClass\",\n    \"conditions\" : [ \"\", \"\" ],\n    \"stationId\" : \"stationId\",\n    \"sharedProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    }\n  },\n  \"arrivalTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"from\" : {\n    \"name\" : \"name\",\n    \"coordinates\" : {\n      \"lng\" : 6.169639,\n      \"alt\" : 0.14658129,\n      \"lat\" : 52.253279\n    },\n    \"physicalAddress\" : {\n      \"areaReference\" : \"Smallcity, Pinetree county\",\n      \"province\" : \"province\",\n      \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n      \"city\" : \"city\",\n      \"street\" : \"street\",\n      \"postalCode\" : \"postalCode\",\n      \"houseNumber\" : 0,\n      \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n      \"state\" : \"state\",\n      \"houseNumberAddition\" : \"houseNumberAddition\"\n    },\n    \"stopReference\" : [ {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    }, {\n      \"country\" : \"NL\",\n      \"id\" : \"id\",\n      \"type\" : \"GTFS_STOP_ID\"\n    } ],\n    \"stationId\" : \"stationId\",\n    \"extraInfo\" : \"\"\n  },\n  \"id\" : \"id\",\n  \"state\" : \"NOT_STARTED\",\n  \"conditions\" : [ \"\", \"\" ]\n}",
                        Leg::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdProgressGet(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "Specifies if only the location should be returned",
            schema = Schema(defaultValue = "false")
        ) @RequestParam(value = "location-only", required = false, defaultValue = "false") locationOnly: @Valid Boolean?
    ): ResponseEntity<LegProgress> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue(
                        "{\n  \"duration\" : 11112,\n  \"distance\" : 7250,\n  \"coordinates\" : {\n    \"lng\" : 6.169639,\n    \"alt\" : 0.14658129,\n    \"lat\" : 52.253279\n  }\n}",
                        LegProgress::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdProgressPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid LegProgress?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun legsIdPut(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.DEFAULT,
            description = "changed leg (e.g. with different duration or destination)",
            required = true,
            schema = Schema
        ) @RequestBody body: @Valid Leg?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    companion object {
        private val log = LoggerFactory.getLogger(LegsApiController::class.java)
    }
}
