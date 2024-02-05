package org.tomp.api.providers.assets

import io.swagger.model.AssetType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ObjectFromFileProvider
import java.util.Collections
import java.util.Random

@Component
@ConditionalOnProperty(value = ["tomp.asset-file"], matchIfMissing = false)
class FileBasedAssetProvider : AssetProvider {
    @Autowired
    var configuration: ExternalConfiguration? = null
    override val assetTypes: List<AssetType?>?
        get() {
            val provider = ObjectFromFileProvider<Array<AssetType>>()
            val assetFile = configuration.getAssetFile()
            val assets = provider.getObject("", Array<AssetType>::class.java, assetFile)!!
            val list = ArrayList<AssetType?>()
            Collections.addAll(list, *assets)
            return list
        }
    override val typeOfAsset: AssetType?
        get() {
            val provider = ObjectFromFileProvider<Array<AssetType>>()
            val list = ArrayList<AssetType>()
            val assetFile = configuration.getAssetFile()
            val assets = provider.getObject("", Array<AssetType>::class.java, assetFile)!!
            Collections.addAll(list, *assets)
            val randomItem = Random().nextInt(list.size)
            return list[randomItem]
        }
}
