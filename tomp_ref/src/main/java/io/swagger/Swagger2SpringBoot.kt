package io.swagger

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.oas.annotations.EnableOpenApi

@SpringBootApplication
@EnableOpenApi //@ComponentScan(basePackages = { "io.swagger", "io.swagger.api", "io.swagger.configuration" })
@ComponentScan(basePackages = ["org.tomp.api", "io.swagger.client"])
class Swagger2SpringBoot : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg arg0: String) {
        if (arg0.isNotEmpty() && arg0[0] == "exitcode") {
            throw ExitException()
        }
    }

    internal inner class ExitException : RuntimeException(), ExitCodeGenerator {
        override fun getExitCode(): Int {
            return 10
        }

    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("*")
            }
        }
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication(Swagger2SpringBoot::class.java).run(*args)
        }
    }
}
