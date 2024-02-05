package org.tomp.api.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "parking")
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "parking", matchIfMissing = false)
class ParkingDataConfiguration {
    var opendataUrl: String? = null
    var uuids: Array<String>
    var refreshInMillis: Long = 0
    var nameContains: String? = null
    var r = 0.0
}
