package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class StaticParkingData {
    @JsonProperty("parkingFacilityInformation")
    var information: ParkingFacilityInformation? = null
}
