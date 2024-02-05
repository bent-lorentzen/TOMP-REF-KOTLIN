package org.tomp.api.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.client.ApiException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.model.MaasOperator
import java.io.FileNotFoundException
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.SignatureException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Arrays
import java.util.Date
import java.util.Locale

@Component
class RouterUtil {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var mapper: ObjectMapper? = null

    @Autowired
    var clientUtil: ClientUtil? = null
    @Throws(
        UnrecoverableKeyException::class,
        InvalidKeyException::class,
        FileNotFoundException::class,
        KeyStoreException::class,
        CertificateException::class,
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        SignatureException::class
    )
    fun createHeadersToMP(method: String, localVarPath: String, body: String?, mpId: String?): HttpHeaders {
        var mpId = mpId
        val url = configuration.getRouterUrl() ?: return HttpHeaders()
        val thumbprint = configuration.getThumbprint()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSZ")
        val router = MaasOperator()
        router.name = "Router"
        router.url = url
        val generator = SignatureGenerator()
        var msgBody: String? = ""
        try {
            if (body != null && body != "") {
                msgBody = mapper!!.writeValueAsString(body)
            }
        } catch (e1: JsonProcessingException) {
            e1.printStackTrace()
        }
        if (msgBody == null) {
            msgBody = ""
        }
        if (mpId == null) {
            mpId = ""
        }
        val timestamp = formatter.format(ZonedDateTime.now())
        val signature = generator.generateSignature(
            method, (url + localVarPath.substring(1)).uppercase(Locale.getDefault()),
            mpId.replace("-", ""), thumbprint, msgBody, configuration.getToID(), timestamp, configuration.getPfx(),
            configuration.getPw()
        )
        val headers = HttpHeaders()
        headers["Client-Signature"] = Arrays.asList(signature)
        headers["CertifacteThumbprint"] = Arrays.asList(thumbprint)
        headers["MPID"] = Arrays.asList(mpId)
        headers["TOID"] = Arrays.asList(configuration.getToID())
        headers["DEST"] = mutableListOf("MP")
        headers["timestamp"] = Arrays.asList(timestamp)
        return headers
    }

    @Throws(
        UnrecoverableKeyException::class,
        InvalidKeyException::class,
        FileNotFoundException::class,
        KeyStoreException::class,
        CertificateException::class,
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        SignatureException::class,
        JsonProcessingException::class
    )
    fun <T> sendToTO(method: String, localVarPath: String, valueType: Class<T>?, body: Any?, acceptLanguage: String?): T? {
        val url = configuration.getRouterUrl()
        val sensorId = configuration.getSensorId()
        val toID = configuration.getToID()
        val thumbprint = configuration.getThumbprint()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSZ")
        val router = MaasOperator()
        router.name = "Router"
        router.url = url
        val generator = SignatureGenerator()
        var msgBody: String? = ""
        try {
            if (body != null && body != "") {
                msgBody = mapper!!.writeValueAsString(body)
            }
        } catch (e1: JsonProcessingException) {
            e1.printStackTrace()
        }
        if (msgBody == null) {
            msgBody = ""
        }
        val timestamp = formatter.format(ZonedDateTime.now())
        val signature = generator.generateSignature(
            method, (url + localVarPath.substring(1)).uppercase(Locale.getDefault()),
            sensorId!!.replace("-", ""), thumbprint, msgBody, toID, timestamp, configuration.getPfx(),
            configuration.getPw()
        )
        val headers = HashMap<String?, String?>()
        headers["TOID"] = toID
        headers["Client-Signature"] = signature
        headers["CertifacteThumbprint"] = thumbprint
        headers["MPID"] = sensorId
        headers["DEST"] = "TO"
        headers["timestamp"] = timestamp
        try {
            log.info("Request body ({}) : {}", method, msgBody)
            var json = if (method == "POST") clientUtil!!.post(
                router, acceptLanguage, "1.1.0", localVarPath, configuration.getMaasId(), headers,
                msgBody, String::class.java
            ) else clientUtil!!.get(
                router, acceptLanguage, "1.1.0", localVarPath, configuration.getMaasId(),
                String::class.java, headers
            )
            log.info("Response: {}", json)
            val m = ObjectMapper()
            if (json!!.startsWith("{")) {
                val map: Map<*, *> = m.readValue(json, HashMap::class.java)
                val `object` = map["validUntil"]
                if (`object` != null) {
                    val validUntil = `object`.toString()
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
                    json = json.replace(validUntil, simpleDateFormat.format(Date()))
                }
            }
            return mapper!!.readValue(json, valueType)
        } catch (e: ApiException) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private val log = LoggerFactory.getLogger(RouterUtil::class.java)
    }
}
