package org.tomp.api.configuration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.tomp.api.model.MaasEnvironmentType
import org.tomp.api.model.TransportOperator
import javax.validation.constraints.NotEmpty

@Component
@ConfigurationProperties(prefix = "tomp")
class ExternalConfiguration {
    @Value("\${spring.application.name}")
    var appName: String? = null
    var lookupService: String? = null
    var maasId: @NotEmpty String? = null
    var acceptLanguage: @NotEmpty String? = null
    var apiVersion: @NotEmpty String? = null
    var assetFile: String? = null
    var systemInformationFile: String? = null
    var conditionFile: String? = null
    var transportOperators: List<TransportOperator>? = null
    var fareFile: String? = null
    var legFile: String? = null
    var typeOfAssetFile: String? = null
    var regionsFile: String? = null
    var stationsFile: String? = null
    var areaFile: String? = null
    var bookingMailBox: String? = null
    var expirationDays = 0
    var externalUrl: String? = null
    var currencyCode: String? = null
    var vatRate: Long = 0
    var pw: String? = null
    var pfx: String? = null
    var versionFile: String? = null
    var isRefreshOnStartUp = true
    var isAllowUnknownOperators = false
    var isAuthenticationRequired = true

    // Router
    var routerUrl: String? = null
    var sensorId: String? = null
    var toID: String? = null
    var thumbprint: String? = null
    var environmentType = MaasEnvironmentType.TO

    init {
        log.info("Configuration file")
    }

    val optionsLegFile: String?
        get() = legFile

    companion object {
        private val log = LoggerFactory.getLogger(ExternalConfiguration::class.java)
    }
}
