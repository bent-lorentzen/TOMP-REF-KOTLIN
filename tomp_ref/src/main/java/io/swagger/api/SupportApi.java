/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.30).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.SupportRequest;
import io.swagger.model.SupportStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-26T08:47:05.979Z[GMT]")
@Validated
public interface SupportApi {

    @Operation(summary = "", description = "Gets the status report of the support request. Last status (highest order number) is the current status", security = {
        @SecurityRequirement(name = "ApiKeyAuth"),
@SecurityRequirement(name = "BasicAuth"),
@SecurityRequirement(name = "BearerAuth"),
@SecurityRequirement(name = "OAuth", scopes = {
                    }),
@SecurityRequirement(name = "OpenId")    }, tags={ "support", "TO" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "support status delivered", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SupportStatus.class)))),
        
        @ApiResponse(responseCode = "400", description = "Bad request. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for further explanation of error code.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "Although the HTTP standard specifies \"unauthorized\", semantically this response means \"unauthenticated\". That is, the client must authenticate itself to get the requested response.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "The client does not have access rights to the content, i.e. they are unauthorized, so server is rejecting to give proper response. Unlike 401, the client's identity is known to the server.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The requested resources does not exist or the requester is not authorized to see it or know it exists.") })
    @RequestMapping(value = "/support/{id}/status",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<SupportStatus>> supportIdStatusGet(@Parameter(in = ParameterIn.HEADER, description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information" ,required=true,schema=@Schema()) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage, @Parameter(in = ParameterIn.HEADER, description = "API description, can be TOMP or maybe other (specific/derived) API definitions" ,required=true,schema=@Schema()) @RequestHeader(value="Api", required=true) String api, @Parameter(in = ParameterIn.HEADER, description = "Version of the API." ,required=true,schema=@Schema()) @RequestHeader(value="Api-Version", required=true) String apiVersion, @Parameter(in = ParameterIn.HEADER, description = "The ID of the sending maas operator" ,required=true,schema=@Schema()) @RequestHeader(value="maas-id", required=true) String maasId, @Parameter(in = ParameterIn.PATH, description = "Booking identifier", required=true, schema=@Schema()) @PathVariable("id") String id, @Parameter(in = ParameterIn.HEADER, description = "The ID of the maas operator that has to receive this message" ,schema=@Schema()) @RequestHeader(value="addressed-to", required=false) String addressedTo);


    @Operation(summary = "", description = "creates a request for support from end user via MP", security = {
        @SecurityRequirement(name = "ApiKeyAuth"),
@SecurityRequirement(name = "BasicAuth"),
@SecurityRequirement(name = "BearerAuth"),
@SecurityRequirement(name = "OAuth", scopes = {
                    }),
@SecurityRequirement(name = "OpenId")    }, tags={ "support", "TO" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "support request acknowledged", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupportStatus.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad request. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for further explanation of error code.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "Although the HTTP standard specifies \"unauthorized\", semantically this response means \"unauthenticated\". That is, the client must authenticate itself to get the requested response.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "The client does not have access rights to the content, i.e. they are unauthorized, so server is rejecting to give proper response. Unlike 401, the client's identity is known to the server.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The requested resources does not exist or the requester is not authorized to see it or know it exists.") })
    @RequestMapping(value = "/support/",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SupportStatus> supportPost(@Parameter(in = ParameterIn.HEADER, description = "A list of the languages/localizations the user would like to see the results in. For user privacy and ease of use on the TO side, this list should be kept as short as possible, ideally just one language tag from the list in operator/information" ,required=true,schema=@Schema()) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage, @Parameter(in = ParameterIn.HEADER, description = "API description, can be TOMP or maybe other (specific/derived) API definitions" ,required=true,schema=@Schema()) @RequestHeader(value="Api", required=true) String api, @Parameter(in = ParameterIn.HEADER, description = "Version of the API." ,required=true,schema=@Schema()) @RequestHeader(value="Api-Version", required=true) String apiVersion, @Parameter(in = ParameterIn.HEADER, description = "The ID of the sending maas operator" ,required=true,schema=@Schema()) @RequestHeader(value="maas-id", required=true) String maasId, @Parameter(in = ParameterIn.HEADER, description = "The ID of the maas operator that has to receive this message" ,schema=@Schema()) @RequestHeader(value="addressed-to", required=false) String addressedTo, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody SupportRequest body);

}

