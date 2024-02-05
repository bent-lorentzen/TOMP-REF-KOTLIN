package org.tomp.api.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.client.ApiClient
import io.swagger.client.ApiException
import io.swagger.client.Pair
import io.swagger.client.ProgressRequestBody.ProgressRequestListener
import io.swagger.model.GeojsonPolygon
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ExternalFileService
import java.io.IOException
import javax.annotation.PostConstruct

@Component
class LookupService @Autowired constructor(private val configuration: ExternalConfiguration, private val mapper: ObjectMapper) {
    private val cache = HashMap<String?, MaasOperator?>()

    @Autowired
    var fileService: ExternalFileService? = null
    fun getMaasOperator(id: String?): MaasOperator? {
        if (configuration.isAllowUnknownOperators) {
            return null
        }
        var operator = cache[id]
        if (operator == null && !cache.containsKey(id)) {
            operator = callEndpoint("GET", "/operators/$id", null, MaasOperator::class.java)
            cache[id] = operator
        }
        return operator
    }

    fun validate(maasId: String, token: String): Boolean {
        if (!configuration.isAuthenticationRequired) {
            return true
        }
        val operator = callEndpoint(
            "POST", "/operators/authenticate?id=$maasId&token=$token", "",
            MaasOperator::class.java
        )
        return operator != null
    }

    @Throws(JsonProcessingException::class)
    fun findOperators(area: GeojsonPolygon?): Array<MaasOperator> {
        val body: Any = "{\"area\": " + mapper.writeValueAsString(area) + "}"
        return callEndpoint("POST", "/operators", body, Array<MaasOperator>::class.java)!!
    }

    @PostConstruct
    @Throws(IOException::class)
    fun configureLookUp() {
        if (configuration.maasId == "none") {
            registrateOperator()
        } else {
            refreshMetaInformation()
        }
    }

    @Throws(IOException::class)
    private fun refreshMetaInformation() {
        if (configuration.isRefreshOnStartUp) {
            val body = body(configuration.maasId)
            val registered = callEndpoint(
                "PUT",
                "/operators/" + configuration.maasId + "?token=" + configuration.maasId, body,
                MaasOperator::class.java
            )
            if (registered != null) {
                configuration.maasId = registered.id
            }
        }
    }

    @Throws(IOException::class)
    private fun registrateOperator() {
        val body = body("")
        val registered = callEndpoint("POST", "/operators/registrate", body, MaasOperator::class.java)
        if (registered != null) {
            log.info("Assigned id: {}", registered.id)
            configuration.maasId = registered.id
        }
    }

    @Throws(IOException::class)
    private fun body(id: String?): String {
        val area = fileService.getArea()
        val type = configuration.environmentType
        var json = mapper.writeValueAsString(area).replace("\"", "\\\"")
        json = ("{" + "  \"id\": \"" + id + "\"," + "  \"type\": " + mapper.writeValueAsString(type) + ","
                + "  \"name\": \"" + configuration.appName + "\"," + "  \"url\": \""
                + configuration.externalUrl + "\"," + "  \"supportedVersions\": " + fileService.getVersions()
                + ",  \"validationToken\": \"" + configuration.maasId + "\","
                + "  \"transactionProvider\": \"none\"," + "  \"servicedArea\": " + json + ","
                + "  \"registrationresult\": \"" + configuration.externalUrl + "/registrated/\"" + "}")
        log.info(json)
        return json
    }

    private fun <T> callEndpoint(method: String, endpoint: String, body: Any?, c: Class<T>): T? {
        var lookupService = configuration.lookupService
        val client = ApiClient()
        if (lookupService!!.endsWith("/")) {
            lookupService = lookupService.substring(0, lookupService.length - 1)
        }
        log.info("Calling {}{}", lookupService, endpoint)
        if (body != null && body != "") {
            log.info("body: {}", body)
        }
        client.setBasePath(lookupService)
        val queryParams: List<Pair> = ArrayList()
        val collectionQueryParams: List<Pair> = ArrayList()
        val headerParams: MutableMap<String?, String?> = HashMap()
        val formParams: Map<String, Any> = HashMap()
        val authNames = arrayOf<String?>()
        val progressRequestListener: ProgressRequestListener? = null
        headerParams["Accept-Language"] = client.parameterToString("nl")
        headerParams["Api"] = client.parameterToString("TOMP")
        headerParams["Api-Version"] = client.parameterToString(configuration.apiVersion)
        headerParams["maas-id"] = configuration.maasId
        val localVarAccepts = arrayOf<String?>("application/json")
        val localVarAccept = client.selectHeaderAccept(localVarAccepts)
        if (localVarAccept != null) headerParams["Accept"] = localVarAccept
        headerParams["Content-Type"] = "application/json"
        try {
            val call = client.buildCall(
                endpoint, method, queryParams, collectionQueryParams, body, headerParams,
                formParams, authNames, progressRequestListener
            )
            val response = client.execute<T>(call, c)
            if (response.statusCode != 200) {
                log.info("Error code: {}", response.statusCode)
            }
            return response.data
        } catch (e: ApiException) {
            log.error(e.message)
            log.error(e.responseBody)
        }
        return null
    }

    companion object {
        private val log = LoggerFactory.getLogger(LookupService::class.java)
    }
}
