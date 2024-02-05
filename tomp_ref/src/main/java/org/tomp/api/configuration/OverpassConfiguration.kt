package org.tomp.api.configuration

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "overpass")
class OverpassConfiguration {
    var ql: String? = null

    init {
        log.info("Overpass Configuration file")
    }

    companion object {
        private val log = LoggerFactory.getLogger(OverpassConfiguration::class.java)
    }
}
