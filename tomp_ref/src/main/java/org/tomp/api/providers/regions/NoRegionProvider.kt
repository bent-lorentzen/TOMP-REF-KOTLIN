package org.tomp.api.providers.regions

import io.swagger.model.SystemRegion
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["tomp.region-file"], havingValue = "none", matchIfMissing = false)
class NoRegionProvider : RegionProvider {
    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion> {
        return ArrayList()
    }
}
