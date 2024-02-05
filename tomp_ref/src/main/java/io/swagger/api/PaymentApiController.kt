package io.swagger.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.ExtraCosts
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
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
import org.threeten.bp.OffsetDateTime
import java.io.IOException
import javax.annotation.Generated
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.Min

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
@RestController
open class PaymentApiController @Autowired constructor(private val objectMapper: ObjectMapper, private val request: HttpServletRequest) : PaymentApi {
    override fun paymentIdClaimExtraCostsPost(
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
        @Parameter(`in` = ParameterIn.DEFAULT, description = "", schema = Schema) @RequestBody body: @Valid ExtraCosts?
    ): ResponseEntity<JournalEntry?> {
        val accept = request.getHeader("Accept")
        return if (accept != null && accept.contains("application/json")) {
            try {
                ResponseEntity(objectMapper.readValue("\"\"", JournalEntry::class.java), HttpStatus.NOT_IMPLEMENTED)
            } catch (e: IOException) {
                log.error("Couldn't serialize response for content type application/json", e)
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun paymentJournalEntryGet(
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
        @Parameter(`in` = ParameterIn.QUERY, description = "start of the selection", schema = Schema) @RequestParam(
            value = "from",
            required = false
        ) from: @Valid OffsetDateTime?,
        @Parameter(`in` = ParameterIn.QUERY, description = "end of the selection", schema = Schema) @RequestParam(
            value = "to",
            required = false
        ) to: @Valid OffsetDateTime?,
        @Parameter(`in` = ParameterIn.QUERY, description = "", schema = Schema) @RequestParam(
            value = "state",
            required = false
        ) state: @Valid JournalState?,
        @Parameter(`in` = ParameterIn.QUERY, description = "", schema = Schema) @RequestParam(value = "id", required = false) id: @Valid String?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "type of booking line (e.g. fare, addition costs, fines, ...)",
            schema = Schema(allowableValues = ["ALL", "DAMAGE", "LOSS", "STOLEN", "EXTRA_USAGE", "REFUND", "FINE", "OTHER_ASSET_USED", "CREDIT", "VOUCHER", "DEPOSIT", "OTHER"])
        ) @RequestParam(value = "category", required = false) category: @Valid String?,
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
    ): ResponseEntity<List<JournalEntry?>?> {
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

    companion object {
        private val log = LoggerFactory.getLogger(PaymentApiController::class.java)
    }
}
