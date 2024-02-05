package org.tomp.api.model

import io.swagger.model.AssetClass
import io.swagger.model.SystemRegion

class TransportOperator : MaasOperator() {
    var assetClasses: MutableList<AssetClass?> = ArrayList()
    var regions: List<SystemRegion> = ArrayList()
    fun providesAssetClass(assetClass: AssetClass?): Boolean {
        return assetClasses.contains(assetClass)
    }
}
