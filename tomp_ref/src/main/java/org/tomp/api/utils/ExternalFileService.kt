package org.tomp.api.utils

import io.swagger.model.EndpointImplementation
import io.swagger.model.GeojsonPolygon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.Arrays

@Component
class ExternalFileService @Autowired constructor(private val configuration: ExternalConfiguration) {
    val area: GeojsonPolygon?
        get() {
            val areaProvider = ObjectFromFileProvider<GeojsonPolygon>()
            return areaProvider.getObject("", GeojsonPolygon::class.java, configuration.areaFile)
        }

    @get:Throws(IOException::class)
    val versions: String
        get() {
            val f = File(configuration.versionFile)
            return java.lang.String.join("", Files.readAllLines(f.toPath()))
        }
    val endPoints: List<EndpointImplementation>
        get() {
            val versionProvider = ObjectFromFileProvider<Array<EndpointImplementation>>()
            val list = versionProvider.getObject(
                "", Array<EndpointImplementation>::class.java,
                configuration.versionFile
            )!!
            return Arrays.asList(*list)
        }
}
