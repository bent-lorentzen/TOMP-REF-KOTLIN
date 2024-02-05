package org.tomp.api.repository

import io.swagger.model.StationInformation

interface StationContainer {
    val stations: MutableList<StationInformation?>
}
