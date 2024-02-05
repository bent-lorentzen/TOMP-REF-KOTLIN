package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class DynamicParkingData {
    @JsonProperty("parkingFacilityDynamicInformation")
    var parkingFacilityDynamicInformation: ParkingFacilityDynamicInformation? = null
}
