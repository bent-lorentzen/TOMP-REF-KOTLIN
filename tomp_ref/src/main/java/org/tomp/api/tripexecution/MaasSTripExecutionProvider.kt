package org.tomp.api.tripexecution

import io.swagger.model.Leg
import io.swagger.model.LegEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.repository.MPRepository
import org.tomp.api.utils.ClientUtil

@Component
@ConditionalOnProperty(value = ["tomp.providers.tripexecution"], havingValue = "maasprovider", matchIfMissing = false)
class MaasSTripExecutionProvider : TripExecutionProvider {
    @Autowired
    var repository: MPRepository? = null

    @Autowired
    var clientUtil: ClientUtil? = null
    override fun prepare(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        /*
		 * Trip trip = repository.getTrip(id); Segment segment =
		 * trip.getSegments().get(Integer.parseInt(body.getComment()));
		 * TransportOperator operator = segment.getOperators().iterator().next();
		 * PlanningResult result = segment.getResult(operator).getResults().get(0);
		 * SimpleLeg simpleLeg = (SimpleLeg) result; try { clientUtil.post(operator,
		 * String.format("/legs/%s/events", simpleLeg.getId()), body, Leg[].class); }
		 * catch (ApiException e) { e.printStackTrace(); }
		 */
        return null
    }

    override fun assignAsset(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }

    override fun reserve(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }

    override fun setInUse(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }

    override fun pause(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }

    override fun startFinishing(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }

    override fun finish(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        // TODO Auto-generated method stub
        return null
    }
}
