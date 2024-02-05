package org.tomp.api.providers.regions

import io.swagger.model.SystemRegion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ObjectFromFileProvider

@Component
@ConditionalOnProperty(value = ["tomp.region-file"], matchIfMissing = false)
class FileBasedRegionProvider : RegionProvider {
    @Autowired
    var configuration: ExternalConfiguration? = null
    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion> {
        val provider = ObjectFromFileProvider<Array<SystemRegion>>()
        val regionArray = provider.getObject(
            acceptLanguage, Array<SystemRegion>::class.java,
            configuration.getRegionsFile()
        )!!
        val regions: MutableList<SystemRegion> = ArrayList()
        for (i in regionArray.indices) {
            regions.add(regionArray[i])
        }
        return regions
    }
}
