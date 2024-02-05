package org.tomp.api.providers.assets

import io.swagger.model.AssetType

interface AssetProvider {
    val typeOfAsset: AssetType?
    val assetTypes: List<AssetType?>?
}
