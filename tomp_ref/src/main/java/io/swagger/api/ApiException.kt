package io.swagger.api

import javax.annotation.Generated

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-09T13:23:42.205Z[GMT]")
open class ApiException(private val code: Int, msg: String?) : Exception(msg)
