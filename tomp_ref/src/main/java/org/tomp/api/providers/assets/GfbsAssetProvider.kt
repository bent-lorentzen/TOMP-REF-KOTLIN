package org.tomp.api.providers.assets

import io.swagger.model.AssetType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.repository.GbfsRepository
import java.util.Random

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "gbfs", matchIfMissing = false)
class GfbsAssetProvider : AssetProvider {
    @Autowired
    var repostory: GbfsRepository? = null
    override val typeOfAsset: AssetType?
        get() {
            val assets = repostory.getAssets()
            return assets!![r.nextInt(assets!!.size)]
        }
    override val assetTypes: List<AssetType?>?
        get() = repostory.getAssets()

    companion object {
        private val r = Random()
    }
}
