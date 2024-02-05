package org.tomp.api.planning

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.Place
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ClientUtil
import org.tomp.api.utils.RouterUtil
import java.io.FileNotFoundException
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.SignatureException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "router", matchIfMissing = false)
class RouterPlanningProvider : PlanningProvider {
    protected var from: Place? = null
    protected var to: Place? = null
    protected var start: @Valid OffsetDateTime? = null
    protected var end: @Valid OffsetDateTime? = null

    @Autowired
    var clientUtil: ClientUtil? = null

    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var mapper: ObjectMapper? = null

    @Autowired
    var routerUtil: RouterUtil? = null
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        log.info("Request for options")
        try {
            return getResults(body, acceptLanguage, bookingIntent)
        } catch (e: UnrecoverableKeyException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
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
    private fun getResults(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        val localVarPath = "/plannings/?booking-intent=$bookingIntent"
        return routerUtil!!.sendToTO("POST", localVarPath, Planning::class.java, body, acceptLanguage)
    }

    companion object {
        private val log = LoggerFactory.getLogger(RouterPlanningProvider::class.java)
    }
}
