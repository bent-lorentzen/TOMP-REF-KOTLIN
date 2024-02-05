package org.tomp.api.configuration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.tomp.api.authentication.APIKeyAuthFilter
import org.tomp.api.model.LookupService

@Component
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityAdapter : WebSecurityConfigurerAdapter() {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var lookupService: LookupService? = null
    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        val filter = APIKeyAuthFilter("maas-id")
        filter.setAuthenticationManager { authentication ->
            val maasId = authentication.principal as String
            val maasOperator = lookupService!!.getMaasOperator(maasId)
            if (maasOperator == null) {
                log.error("Unknown MaaS Operator is trying to request information {}", maasId)
                if (!configuration!!.isAllowUnknownOperators) {
                    throw BadCredentialsException("Unknown MaaS Operator.")
                }
            }
            authentication.isAuthenticated = true
            authentication
        }
        val corsCustomizer = Customizer { t: CorsConfigurer<HttpSecurity?> ->
            val source = UrlBasedCorsConfigurationSource()
            val config = CorsConfiguration()
            config.allowCredentials = true
            config.addAllowedOrigin("*")
            config.addAllowedHeader("*")
            config.addAllowedMethod("*")
            source.registerCorsConfiguration("/**", config)
            t.configurationSource(source)
        }
        httpSecurity.csrf().disable().authorizeRequests()
            .antMatchers("/postponed/**").permitAll()
            .antMatchers("/ws2").permitAll()
            .antMatchers("/operator/**").permitAll()
            .and()
            .antMatcher("/**").cors(corsCustomizer).sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(filter).authorizeRequests()
            .anyRequest().authenticated()
    }

    companion object {
        private val log = LoggerFactory.getLogger(SecurityAdapter::class.java)
    }
}