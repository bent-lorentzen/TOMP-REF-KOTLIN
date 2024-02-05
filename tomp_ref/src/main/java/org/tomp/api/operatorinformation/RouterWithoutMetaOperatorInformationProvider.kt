package org.tomp.api.operatorinformation

import io.swagger.model.EndpointImplementation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ExternalFileService

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "router-meta", matchIfMissing = false)
class RouterWithoutMetaOperatorInformationProvider : RouterOperatorInformationProvider() {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var fileService: ExternalFileService? = null
    override fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>? {
        return fileService.getEndPoints()
    }
}
