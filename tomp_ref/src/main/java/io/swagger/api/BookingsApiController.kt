package io.swagger.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingRequest
import io.swagger.model.BookingState
import io.swagger.model.Notification
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
import javax.validation.constraints.NotNull

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
@RestController
open class BookingsApiController @Autowired constructor(private val objectMapper: ObjectMapper, private val request: HttpServletRequest) :
    BookingsApi {
    override fun bookingsGet(
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
        @Parameter(`in` = ParameterIn.QUERY, description = "", required = true, schema = Schema) @RequestParam(
            value = "state",
            required = true
        ) state: @NotNull @Valid BookingState?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<List<Booking>> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue<List<*>>("[ \"\", \"\" ]", MutableList::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdEventsPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Leg identifier", required = true, schema = Schema) @PathVariable("id") id: String,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid BookingOperation?
    ): ResponseEntity<Booking?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("\"\"", Booking::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdGet(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Booking?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("\"\"", Booking::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdNotificationsGet(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<List<Notification>> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(
                    objectMapper.readValue<List<*>>(
                        "[ {\n  \"minutes\" : 0,\n  \"comment\" : \"comment\",\n  \"type\" : \"VEHICLE_NOT_AVAILABLE\",\n  \"asset\" : {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  }\n}, {\n  \"minutes\" : 0,\n  \"comment\" : \"comment\",\n  \"type\" : \"VEHICLE_NOT_AVAILABLE\",\n  \"asset\" : {\n    \"overriddenProperties\" : {\n      \"pets\" : true,\n      \"airConditioning\" : true,\n      \"other\" : \"other\",\n      \"fuel\" : \"NONE\",\n      \"travelAbroad\" : true,\n      \"energyLabel\" : \"A\",\n      \"winterTires\" : true,\n      \"undergroundParking\" : true,\n      \"ancillaries\" : [ {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      }, {\n        \"number\" : \"number\",\n        \"variable-number\" : 0,\n        \"memo\" : \"memo\",\n        \"source\" : \"source\",\n        \"category\" : \"category\",\n        \"type\" : \"type\",\n        \"applicable-days\" : [ \"MO\", \"MO\" ]\n      } ],\n      \"smoking\" : true,\n      \"towingHook\" : true,\n      \"model\" : \"model\",\n      \"gearbox\" : \"MANUAL\",\n      \"cargo\" : \"cargo\",\n      \"brand\" : \"brand\",\n      \"gears\" : 7,\n      \"cabrio\" : true,\n      \"image\" : \"https://files.fietsersbond.nl/app/uploads/2014/10/30151126/ST2_Men_Side_CityKit-Stromer.jpg\",\n      \"buildingYear\" : 2,\n      \"stateOfCharge\" : 36,\n      \"co2PerKm\" : 0.5637377,\n      \"helmetRequired\" : false,\n      \"propulsion\" : \"MUSCLE\",\n      \"maxSpeed\" : 2,\n      \"nrOfDoors\" : 4,\n      \"infantSeat\" : true,\n      \"persons\" : 1,\n      \"colour\" : \"colour\",\n      \"easyAccessibility\" : \"LIFT\",\n      \"meta\" : \"\",\n      \"name\" : \"name\",\n      \"location\" : {\n        \"name\" : \"name\",\n        \"coordinates\" : {\n          \"lng\" : 6.169639,\n          \"alt\" : 0.14658129,\n          \"lat\" : 52.253279\n        },\n        \"physicalAddress\" : {\n          \"areaReference\" : \"Smallcity, Pinetree county\",\n          \"province\" : \"province\",\n          \"streetAddress\" : \"example street 18, 2nd floor, 18-B33\",\n          \"city\" : \"city\",\n          \"street\" : \"street\",\n          \"postalCode\" : \"postalCode\",\n          \"houseNumber\" : 0,\n          \"addressAdditionalInfo\" : \"addressAdditionalInfo\",\n          \"state\" : \"state\",\n          \"houseNumberAddition\" : \"houseNumberAddition\"\n        },\n        \"stopReference\" : [ {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        }, {\n          \"country\" : \"NL\",\n          \"id\" : \"id\",\n          \"type\" : \"GTFS_STOP_ID\"\n        } ],\n        \"stationId\" : \"stationId\",\n        \"extraInfo\" : \"\"\n      },\n      \"accessMethods\" : [ \"DEEPLINK\", \"DEEPLINK\" ]\n    },\n    \"rentalUrl\" : \"https://www.rentmyfreebike.com/app?sid=1234567890\",\n    \"licensePlate\" : \"licensePlate\",\n    \"isReserved\" : true,\n    \"isReservedFrom\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"id\" : \"id\",\n    \"isDisabled\" : true,\n    \"rentalUrlAndroid\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=android\",\n    \"rentalUrlIOS\" : \"https://www.rentmyfreebike.com/app?sid=1234567890&platform=ios\",\n    \"isReservedTo\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"mileage\" : 0.6027456\n  }\n} ]",
                        MutableList::class.java
                    ), HttpStatus.NOT_IMPLEMENTED
                )
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdNotificationsPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid Notification?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdPut(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "changed booking", required = true, schema = Schema) @RequestBody body: @Valid Booking?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Booking> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("\"\"", Booking::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdSubscriptionDelete(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsIdSubscriptionPost(
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
        @Parameter(`in` = ParameterIn.PATH, description = "Booking identifier", required = true, schema = Schema) @PathVariable("id") id: String?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Void> {
        val accept = request.getHeader("Accept")
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun bookingsPost(
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
            `in` = ParameterIn.DEFAULT,
            description = "One of available booking options, returned by /plannings, with an ID.",
            required = true,
            schema = Schema
        ) @RequestBody body: @Valid BookingRequest?,
        @Parameter(
            `in` = ParameterIn.HEADER,
            description = "The ID of the maas operator that has to receive this message",
            schema = Schema
        ) @RequestHeader(value = "addressed-to", required = false) addressedTo: String?
    ): ResponseEntity<Booking?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("\"\"", Booking::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    companion object {
        private val log = LoggerFactory.getLogger(BookingsApiController::class.java)
    }
}
