package org.tomp.api.controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.api.LegsApiController
import io.swagger.model.Leg
import io.swagger.model.LegEvent
import io.swagger.model.LegEvent.EventEnum
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.tomp.api.tripexecution.TripExecutionProvider
import org.tomp.api.utils.HeaderValidator
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class TripExecutionController(private val objectMapper: ObjectMapper, request: HttpServletRequest) : LegsApiController(
    objectMapper, request
) {
    @Autowired
    var provider: TripExecutionProvider? = null
    private val request: HttpServletRequest

    init {
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.request = request
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
        HeaderValidator.Companion.validateHeader(request)
        var leg: Leg? = null
        when (body!!.event) {
            EventEnum.ASSIGN_ASSET -> leg = provider!!.assignAsset(body, acceptLanguage, id, maasId)
            EventEnum.FINISH -> leg = provider!!.finish(body, acceptLanguage, id, maasId)
            EventEnum.PAUSE -> leg = provider!!.pause(body, acceptLanguage, id, maasId)
            EventEnum.PREPARE -> leg = provider!!.prepare(body, acceptLanguage, id, maasId)
            EventEnum.SET_IN_USE -> leg = provider!!.setInUse(body, acceptLanguage, id, maasId)
            EventEnum.START_FINISHING -> leg = provider!!.startFinishing(body, acceptLanguage, id, maasId)
            EventEnum.TIME_EXTEND, EventEnum.TIME_POSTPONE -> {}
            else -> {}
        }
        return ResponseEntity(leg, HttpStatus.CREATED)
    }
}
