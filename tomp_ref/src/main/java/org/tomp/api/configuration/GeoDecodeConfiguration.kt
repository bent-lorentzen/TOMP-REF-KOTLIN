package org.tomp.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "geodecode")
class GeoDecodeConfiguration {
    var isActive = false
    var decodeUrl: String? = null
    var encodeUrl: String? = null
    var streetAddress: String? = null
    var area: String? = null
    var postalCode: String? = null
    var country: String? = null
    var lon: String? = null
    var lat: String? = null
}
