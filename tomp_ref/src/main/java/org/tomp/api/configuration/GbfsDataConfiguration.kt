package org.tomp.api.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "gbfs")
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "gbfs", matchIfMissing = false)
class GbfsDataConfiguration {
    var opendataUrl: String? = null
    var refreshInMillis: Long = 0
    var nameContains: String? = null
    var r = 0.0
}
