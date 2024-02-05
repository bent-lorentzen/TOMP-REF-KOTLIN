package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class Specification {
    @JsonProperty("areaGeometry")
    var areaGeometry: AreaGeometry? = null
}
