package io.swagger.api

import javax.annotation.Generated
import javax.xml.bind.annotation.XmlRootElement

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-03-24T15:10:42.894Z[GMT]")
@XmlRootElement
class ApiResponseMessage(code: Int, var message: String?) {
    var type: String = when (code) {
        ERROR -> "error"
        WARNING -> "warning"
        INFO -> "info"
        OK -> "ok"
        TOO_BUSY -> "too busy"
        else -> "unknown"
    }


    companion object {
        const val ERROR = 1
        const val WARNING = 2
        const val INFO = 3
        const val OK = 4
        const val TOO_BUSY = 5
    }
}
