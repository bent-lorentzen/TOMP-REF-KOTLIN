package org.tomp.api.providers.conditions

import io.swagger.model.ConditionReturnArea
import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.repository.GbfsRepository
import org.tomp.api.utils.GeoUtil

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "gbfs", matchIfMissing = false)
class GbfsConditionProvider : ConditionProvider {
    @Autowired
    var repository: GbfsRepository? = null
    fun getConditions(acceptLanguage: String?): List<OneOflegConditionsItems> {
        val conditions = ArrayList<OneOflegConditionsItems>()
        for (station in repository.getStations()) {
            val condition = ConditionReturnArea()
            condition.id = "RA_" + station!!.stationId
            condition.coordinates = station!!.coordinates
            condition.stationId = station!!.stationId
            condition.setReturnHours(repository.getSystemHours())
            condition.setReturnArea(GeoUtil.toPolygon(station!!.coordinates, 0.0001))
            conditions.add(condition)
        }
        return conditions
    }

    override fun getApplyingConditions(acceptLanguage: String?, result: Leg?): List<OneOflegConditionsItems> {
        return getConditions(acceptLanguage)
    } // @Override
    // public List<OneOflegConditionsItems> getApplyingConditions(String
    // acceptLanguage, Leg leg) {
    // String fromStationId =
    // leg.getAssetType().getAssets().get(0).getOverriddenProperties().getLocation()
    // .getStationId();
    // return getConditions(acceptLanguage).stream().filter(x ->
    // x.getId().equals("RA_" + fromStationId))
    // .collect(Collectors.toList());
    // }
}
