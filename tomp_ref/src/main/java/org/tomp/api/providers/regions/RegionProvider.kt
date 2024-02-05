package org.tomp.api.providers.regions

import io.swagger.model.SystemRegion

interface RegionProvider {
    fun getRegions(acceptLanguage: String?): MutableList<SystemRegion>
}
