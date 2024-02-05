package io.swagger.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.Date
import javax.annotation.Generated

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
@Configuration
class SwaggerDocumentationConfig {
    @Bean
    fun customImplementation(): Docket {
        return Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
            .build()
            .directModelSubstitute(LocalDate::class.java, java.sql.Date::class.java)
            .directModelSubstitute(OffsetDateTime::class.java, Date::class.java)
            .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Transport Operator MaaS Provider API")
            .description("An API between MaaS providers and transport operators for booking trips and corresponding assets. <p>The documentation (examples, process flows and sequence diagrams) can be found at <a href=\"https://github.com/TOMP-WG/TOMP-API/\">github</a>.")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.3.0")
            .contact(Contact("", "", ""))
            .build()
    }

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Transport Operator MaaS Provider API")
                    .description("An API between MaaS providers and transport operators for booking trips and corresponding assets. <p>The documentation (examples, process flows and sequence diagrams) can be found at <a href=\"https://github.com/TOMP-WG/TOMP-API/\">github</a>.")
                    .termsOfService("")
                    .version("1.3.0")
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                    )
                    .contact(
                        io.swagger.v3.oas.models.info.Contact()
                            .email("")
                    )
            )
    }
}
