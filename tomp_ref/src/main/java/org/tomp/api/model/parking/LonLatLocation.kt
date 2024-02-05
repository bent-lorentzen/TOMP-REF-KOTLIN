package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class LonLatLocation {
    @JsonProperty("latitude")
    var latitude: Float? = null

    @JsonProperty("longitude")
    var longitude: Float? = null
}
