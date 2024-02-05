package org.tomp.api.planning

import io.swagger.model.Coordinates
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.repository.RegionContainer
import org.tomp.api.utils.GeoUtil
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "nearStart", matchIfMissing = false)
class NearStartPlanningProvider : GenericPlanningProvider() {
    @Autowired
    var regionContainer: RegionContainer? = null
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        val start = gf.createPoint(toCoordinate(body!!.from!!.coordinates))
        val regions = regionContainer.getRegions()
        for (region in regions!!) {
            val p = GeoUtil.toPolygon(region!!.getServiceArea())
            val distance = start.distance(p)
            if (distance < body.radius!!.toDouble()) {
                return super.getOptions(body, acceptLanguage, bookingIntent)
            }
        }
        return Planning()
    }

    private fun toCoordinate(coordinates: @NotNull @Valid Coordinates?): Coordinate {
        val c = Coordinate()
        c.setX(coordinates!!.lng!!.toDouble())
        c.setY(coordinates.lat!!.toDouble())
        return c
    }

    companion object {
        var gf = GeometryFactory()
    }
}
