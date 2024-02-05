package org.tomp.api.controllers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.api.PlanningsApiController
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.tomp.api.planning.PlanningProvider
import org.tomp.api.utils.HeaderValidator
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class PlanningOptionsController(private val objectMapper: ObjectMapper, request: HttpServletRequest) : PlanningsApiController(
    objectMapper, request
) {
    private val request: HttpServletRequest

    @Autowired
    private val planningProvider: PlanningProvider? = null

    init {
        this.objectMapper.setVisibility(objectMapper.visibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.ANY))
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.request = request
    }

    override fun planningsPost(
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
            description = "Specifies whether IDs should be returned for the leg options that can be referred to when booking",
            schema = Schema(defaultValue = "false")
        ) @RequestParam(value = "booking-intent", required = false, defaultValue = "false") bookingIntent: @Valid Boolean?,
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid PlanningRequest?
    ): ResponseEntity<Planning?> {
        HeaderValidator.Companion.validateHeader(request)
        val options = planningProvider!!.getOptions(body, acceptLanguage, bookingIntent!!)
        return ResponseEntity(options, HttpStatus.CREATED)
    }
}
