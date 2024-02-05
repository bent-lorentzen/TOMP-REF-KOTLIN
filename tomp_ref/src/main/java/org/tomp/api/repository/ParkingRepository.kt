package org.tomp.api.repository

import io.swagger.model.StationInformation
import io.swagger.model.SystemRegion
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.model.parking.ParkingFacilityDynamicInformation
import org.tomp.api.model.parking.ParkingFacilityInformation

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "parking", matchIfMissing = false)
class ParkingRepository : RegionContainer {
    var staticData = HashMap<String?, ParkingFacilityInformation?>()
    var dynamicData = HashMap<String?, ParkingFacilityDynamicInformation?>()
    var dynamicUrls = HashMap<String?, String?>()
    private override var regions: MutableList<SystemRegion?> = ArrayList()
    var stations: MutableList<StationInformation?> = ArrayList()
    override fun getRegions(): MutableList<SystemRegion?>? {
        return regions
    }

    fun setRegions(regions: MutableList<SystemRegion?>) {
        this.regions = regions
    }
}
