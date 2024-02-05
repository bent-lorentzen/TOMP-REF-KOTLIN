package org.tomp.api.utils

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.exceptions.MissingArgumentException
import org.tomp.api.model.LookupService
import java.util.Collections
import java.util.Locale
import javax.servlet.http.HttpServletRequest

@Component
class HeaderValidator @Autowired private constructor(configuration: ExternalConfiguration, private val lookupService: LookupService) {
    private val requiredHeaderValues = HashMap<String, String?>()

    init {
        requiredHeaderValues["api-version"] = configuration.apiVersion
        requiredHeaderValues["api"] = "TOMP"
        requiredHeaderValues["accept-language"] = "*"
        requiredHeaderValues["maas-id"] = "*"
        singleton = this
    }

    private fun privateValidateHeader(request: HttpServletRequest) {
        val headerNames = Collections.list(request.headerNames)
        headerNames.replaceAll { obj: String -> obj.lowercase(Locale.getDefault()) }
        for (required in requiredHeaderValues.keys) {
            if (!headerNames.contains(required)) {
                throw MissingArgumentException(required)
            }
            val value = requiredHeaderValues[required]
            val headerValue = request.getHeader(required)
            if (value != "*" && !headerValue.equals(value, ignoreCase = true)) {
                log.error("{} contains invalid value: {} ({})", required, value, headerValue)
                throw IllegalArgumentException(required)
            }
        }
        val p = request.userPrincipal
        if (p != null) {
            checkValidCombination(p.name, request.getHeader("maas-id"))
        }
    }

    private fun checkValidCombination(token: String, maasId: String) {
        if (validateCertificate(maasId, token)) {
            val msg = String.format("Invalid combination maas-id/certificate %s %s", maasId, token)
            log.error(msg)
        }
    }

    private fun validateCertificate(maasId: String, token: String): Boolean {
        return lookupService.validate(maasId, token)
    }

    companion object {
        private var singleton: HeaderValidator
        private val log = LoggerFactory.getLogger(HeaderValidator::class.java)
        fun validateHeader(request: HttpServletRequest) {
            singleton.privateValidateHeader(request)
        }
    }
}
