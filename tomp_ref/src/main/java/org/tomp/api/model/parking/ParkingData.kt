package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class ParkingData {
    @JsonProperty("ParkingFacilities")
    var parkingFacilities: Array<ParkingFacility>
}
