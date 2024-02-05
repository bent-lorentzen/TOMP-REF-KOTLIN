package org.tomp.api.planning

import io.swagger.model.AssetType
import io.swagger.model.Fare
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.providers.assets.AssetProvider
import org.tomp.api.providers.fares.FareProvider

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "generic", matchIfMissing = true)
open class GenericPlanningProvider : BasePlanningProvider() {
    @Autowired
    var assetProvider: AssetProvider? = null

    @Autowired
    var fareProvider: FareProvider? = null
    protected override val fare: Fare?
        protected get() = fareProvider.getFare()
    protected override val assetType: AssetType?
        protected get() = assetProvider.getTypeOfAsset()
}
