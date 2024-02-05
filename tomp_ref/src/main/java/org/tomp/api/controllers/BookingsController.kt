package org.tomp.api.controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.api.BookingsApiController
import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingRequest
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.tomp.api.booking.BookingProvider
import org.tomp.api.booking.SharedCarBookingProvider
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.model.PostPonedResult
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.utils.HeaderValidator
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class BookingsController(private val objectMapper: ObjectMapper, request: HttpServletRequest) : BookingsApiController(
    objectMapper, request
) {
    @Autowired
    private val provider: BookingProvider? = null
    private val request: HttpServletRequest

    @Autowired
    private val config: ExternalConfiguration? = null

    @Autowired
    private val repository: DefaultRepository? = null

    init {
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.request = request
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
        HeaderValidator.Companion.validateHeader(request)
        val booking = provider!!.addNewBooking(body, acceptLanguage)
        return ResponseEntity(booking, HttpStatus.OK)
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
        HeaderValidator.Companion.validateHeader(request)
        provider!!.setRequest(request)
        val booking = provider.addNewBookingEvent(body, acceptLanguage, id)
        return ResponseEntity(booking, HttpStatus.OK)
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
        HeaderValidator.Companion.validateHeader(request)
        return ResponseEntity(provider!!.getBooking(id), HttpStatus.OK)
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
        provider!!.subscribeToBookings(acceptLanguage, api, apiVersion, id)
        return ResponseEntity(HttpStatus.OK)
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
        provider!!.unsubscribeToBookings(acceptLanguage, api, apiVersion, id)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/postponed/{id}")
    @ResponseBody
    fun respondToPostponedBooking(@PathVariable("id") id: String): String? {
        if (provider is SharedCarBookingProvider) {
            var externalUrl = config.getExternalUrl()
            if (externalUrl!!.endsWith("/")) {
                externalUrl = externalUrl!!.substring(0, externalUrl!!.length - 1)
            }
            return provider.getPostponedBookingHtml(id, "$externalUrl/postponed/")
        }
        return ""
    }

    @PostMapping("/postponed/")
    fun postPostponedBooking(@ModelAttribute result: PostPonedResult): String? {
        if (provider is SharedCarBookingProvider) {
            provider.saveResult(
                result.id, result.choice == 1,
                result.remark
            )
        }
        return try {
            objectMapper.writeValueAsString(repository!!.getBooking(result.id))
        } catch (e: JsonProcessingException) {
            e.message
        }
    }
}
