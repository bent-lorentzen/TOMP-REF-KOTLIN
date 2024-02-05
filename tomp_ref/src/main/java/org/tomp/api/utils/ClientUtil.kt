package org.tomp.api.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.Call
import io.swagger.client.ApiClient
import io.swagger.client.ApiException
import io.swagger.client.Pair
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.controllers.WebsocketController
import org.tomp.api.model.MaasOperator
import java.io.IOException

@Component
class ClientUtil @Autowired private constructor(configuration: ExternalConfiguration, mapper: ObjectMapper) {
    @Autowired
    private val websocket: WebsocketController? = null

    init {
        Companion.configuration = configuration
        Companion.mapper = mapper
    }

    @Throws(ApiException::class)
    operator fun <T> get(to: MaasOperator?, localVarPath: String, class1: Class<T>): T {
        return get(
            to, configuration.getAcceptLanguage(), configuration.getApiVersion(), localVarPath,
            configuration.getMaasId(), class1, null
        )
    }

    @Throws(ApiException::class)
    operator fun <T> get(to: MaasOperator?, localVarPath: String, class1: Class<T>, headers: Map<String?, String?>?): T {
        return get(
            to, configuration.getAcceptLanguage(), configuration.getApiVersion(), localVarPath,
            configuration.getMaasId(), class1, headers
        )
    }

    @Throws(ApiException::class)
    operator fun <T> get(
        to: MaasOperator?, acceptLanguage: String?, apiVersion: String?, localVarPath: String, maasId: String?,
        class1: Class<T>, headers: Map<String?, String?>?
    ): T {
        val apiClient = ApiClient()
        apiClient.setVerifyingSsl(false)
        var url = to.getUrl()
        if (url!!.endsWith("/") && localVarPath.startsWith("/")) {
            url = url!!.substring(0, url!!.length - 1)
        }
        apiClient.setBasePath(url)
        val localVarPostBody: Any? = null
        val localVarQueryParams: List<Pair> = ArrayList()
        val localVarCollectionQueryParams: List<Pair> = ArrayList()
        val localVarHeaderParams: MutableMap<String?, String?> = HashMap()
        if (acceptLanguage != null) localVarHeaderParams["Accept-Language"] = apiClient.parameterToString(acceptLanguage)
        localVarHeaderParams["Api"] = apiClient.parameterToString("TOMP")
        if (apiVersion != null) localVarHeaderParams["Api-Version"] = apiClient.parameterToString(apiVersion)
        localVarHeaderParams["maas-id"] = maasId
        val localVarFormParams: Map<String, Any> = HashMap()
        val localVarAccepts = arrayOf<String?>("application/json")
        val localVarAccept = apiClient.selectHeaderAccept(localVarAccepts)
        if (localVarAccept != null) {
            localVarHeaderParams["Accept"] = localVarAccept
        }
        if (headers != null) {
            for ((key, value) in headers) {
                localVarHeaderParams[key] = value
            }
        }
        val localVarContentTypes = arrayOf<String>()
        val localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes)
        localVarHeaderParams["Content-Type"] = localVarContentType
        val localVarAuthNames = arrayOf<String?>()
        val call = apiClient.buildCall(
            localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams,
            localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, null
        )
        return handleResult(to, class1, apiClient, call, localVarPath, localVarPostBody, "GET")
    }

    @Throws(ApiException::class)
    fun <T> post(to: MaasOperator?, localVarPath: String, localVarPostBody: Any?, class1: Class<T>): T {
        return post(
            to, configuration.getAcceptLanguage(), configuration.getApiVersion(), localVarPath,
            configuration.getMaasId(), localVarPostBody, class1
        )
    }

    @Throws(ApiException::class)
    fun <T> post(
        to: MaasOperator?, acceptLanguage: String?, apiVersion: String?, localVarPath: String, maasId: String?,
        localVarPostBody: Any?, class1: Class<T>
    ): T {
        return post(to, acceptLanguage, apiVersion, localVarPath, maasId, null, localVarPostBody, class1)
    }

    @Throws(ApiException::class)
    fun <T> post(
        to: MaasOperator?, acceptLanguage: String?, apiVersion: String?, localVarPath: String, maasId: String?,
        headers: Map<String?, String?>?, localVarPostBody: Any?, class1: Class<T>
    ): T {
        var localVarPostBody = localVarPostBody
        val apiClient = ApiClient()
        apiClient.setConnectTimeout(20000)
        apiClient.setReadTimeout(20000)
        var url = to.getUrl()
        if (url!!.endsWith("/") && localVarPath.startsWith("/")) {
            url = url!!.substring(0, url!!.length - 1)
        }
        apiClient.setBasePath(url)
        apiClient.setReadTimeout(10000)
        log.info("Connecting to {}{}", url, localVarPath)
        log.info("Body {}", localVarPostBody)
        val localVarQueryParams: List<Pair> = ArrayList()
        val localVarCollectionQueryParams: List<Pair> = ArrayList()
        val localVarHeaderParams: MutableMap<String?, String?> = HashMap()
        if (acceptLanguage != null) localVarHeaderParams["Accept-Language"] = apiClient.parameterToString(acceptLanguage)
        localVarHeaderParams["Api"] = apiClient.parameterToString("TOMP")
        if (apiVersion != null) localVarHeaderParams["Api-Version"] = apiClient.parameterToString(apiVersion)
        localVarHeaderParams["maas-id"] = maasId
        if (headers != null) {
            for ((key, value) in headers) {
                localVarHeaderParams[key] = value
            }
        }
        val localVarFormParams: Map<String, Any> = HashMap()
        val localVarAccepts = arrayOf<String?>("application/json")
        val localVarAccept = apiClient.selectHeaderAccept(localVarAccepts)
        if (localVarAccept != null) localVarHeaderParams["Accept"] = localVarAccept
        val localVarContentTypes = arrayOf<String>()
        val localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes)
        localVarHeaderParams["Content-Type"] = localVarContentType
        val localVarAuthNames = arrayOf<String?>()
        if (class1 != String::class.java) {
            try {
                localVarPostBody = mapper!!.writeValueAsString(localVarPostBody)
            } catch (e: JsonProcessingException) {
                log.error(e.message)
            }
        }
        val call = apiClient.buildCall(
            localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
            localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, null
        )
        return handleResult(to, class1, apiClient, call, localVarPath, localVarPostBody, "POST")
    }

    private fun <T> handleResult(
        to: MaasOperator?, class1: Class<T>, apiClient: ApiClient, call: Call?, localVarPath: String,
        body: Any?, getOrPost: String
    ): T? {
        if (class1 == String::class.java) {
            return try {
                val result = apiClient.execute<T>(call, String::class.java)
                result.data
            } catch (e: ApiException) {
                websocket!!.sendMessage(localVarPath, body)
                log.error("Cannot connect to {} ({})", to.getName(), to.getUrl())
                log.error(e.message)
                log.error(e.responseBody)
                null
            }
        }
        try {
            val dest = if (getOrPost == "POST") post(to, localVarPath, body, String::class.java) else if (getOrPost == "GET") get(
                to,
                localVarPath,
                String::class.java
            ) else patch(to, localVarPath, body, String::class.java)
            return if (dest != null) {
                mapper!!.readValue(dest, class1)
            } else {
                null
            }
        } catch (e2: IOException) {
            log.error("Deserialisation error")
            log.error(e2.message)
        } catch (e: ApiException) {
            log.error("Exception connecting to {} ({})", to.getName(), to.getUrl())
            log.error(e.message)
            log.error(e.responseBody)
        } catch (e: Exception) {
            log.error("Exception connecting to {} ({})", to.getName(), to.getUrl())
            log.error(e.message)
        }
        return null
    }

    @Throws(ApiException::class)
    fun <T> patch(to: MaasOperator?, localVarPath: String, localVarPostBody: Any?, class1: Class<T>): T {
        return patch(
            to, configuration.getAcceptLanguage(), configuration.getApiVersion(), localVarPath,
            configuration.getMaasId(), localVarPostBody, class1
        )
    }

    @Throws(ApiException::class)
    private fun <T> patch(
        to: MaasOperator?, acceptLanguage: String?, apiVersion: String?, localVarPath: String, maasId: String?,
        localVarPostBody: Any?, class1: Class<T>
    ): T {
        var localVarPostBody = localVarPostBody
        val apiClient = ApiClient()
        apiClient.setConnectTimeout(20000)
        apiClient.setReadTimeout(20000)
        var url = to.getUrl()
        if (url!!.endsWith("/") && localVarPath.startsWith("/")) {
            url = url!!.substring(0, url!!.length - 1)
        }
        apiClient.setBasePath(url)
        apiClient.setReadTimeout(10000)
        log.info("Connecting to {}{}", url, localVarPath)
        log.info("Body {}", localVarPostBody)
        val localVarQueryParams: List<Pair> = ArrayList()
        val localVarCollectionQueryParams: List<Pair> = ArrayList()
        val localVarHeaderParams: MutableMap<String?, String?> = HashMap()
        if (acceptLanguage != null) localVarHeaderParams["Accept-Language"] = apiClient.parameterToString(acceptLanguage)
        localVarHeaderParams["Api"] = apiClient.parameterToString("TOMP")
        if (apiVersion != null) localVarHeaderParams["Api-Version"] = apiClient.parameterToString(apiVersion)
        localVarHeaderParams["maas-id"] = maasId
        val localVarFormParams: Map<String, Any> = HashMap()
        val localVarAccepts = arrayOf<String?>("application/json")
        val localVarAccept = apiClient.selectHeaderAccept(localVarAccepts)
        if (localVarAccept != null) localVarHeaderParams["Accept"] = localVarAccept
        val localVarContentTypes = arrayOf<String>()
        val localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes)
        localVarHeaderParams["Content-Type"] = localVarContentType
        val localVarAuthNames = arrayOf<String?>()
        if (class1 != String::class.java) {
            try {
                localVarPostBody = mapper!!.writeValueAsString(localVarPostBody)
            } catch (e: JsonProcessingException) {
                log.error(e.message)
            }
        }
        val call = apiClient.buildCall(
            localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams,
            localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, null
        )
        return handleResult(to, class1, apiClient, call, localVarPath, localVarPostBody, "PATCH")
    }

    companion object {
        private var configuration: ExternalConfiguration? = null
        private val log = LoggerFactory.getLogger(ClientUtil::class.java)
        private var mapper: ObjectMapper? = null
    }
}
