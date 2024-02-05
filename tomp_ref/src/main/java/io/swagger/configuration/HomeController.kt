package io.swagger.configuration

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Home redirection to swagger api documentation
 */
@Controller
class HomeController {
    @RequestMapping(value = ["/"])
    fun index(): String {
        println("/swagger-ui/index.html")
        return "redirect:/swagger-ui/"
    }
}
