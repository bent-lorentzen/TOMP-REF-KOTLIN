package org.tomp.api.repository

import io.swagger.model.SystemRegion

interface RegionContainer {
    val regions: MutableList<SystemRegion?>?
}
