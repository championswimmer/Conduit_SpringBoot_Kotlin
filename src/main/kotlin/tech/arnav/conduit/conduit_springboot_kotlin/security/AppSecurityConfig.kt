package tech.arnav.conduit.conduit_springboot_kotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.web.server.authorization.AuthorizationWebFilter
import org.springframework.security.web.session.ConcurrentSessionFilter
import org.springframework.web.filter.OncePerRequestFilter
import tech.arnav.conduit.conduit_springboot_kotlin.security.jwt.JWTAuthenticationFilter
import tech.arnav.conduit.conduit_springboot_kotlin.security.jwt.JWTAuthenticationManager
import javax.servlet.http.HttpFilter

@Configuration
@EnableWebSecurity
class AppSecurityConfig(
   @Autowired val jwtAuthenticationFilter: JWTAuthenticationFilter,
   @Autowired val authEntryPoint: AuthEntryPoint,
) : WebSecurityConfigurerAdapter() {


    override fun authenticationManager(): AuthenticationManager {
        return JWTAuthenticationManager()
    }

    override fun configure(http: HttpSecurity) {
        http.exceptionHandling().authenticationEntryPoint(authEntryPoint)
            .and()
            .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter::class.java)
            // Public APIs
            .authorizeRequests().antMatchers(HttpMethod.GET, "/articles/**", "/profiles/*", "/tags").permitAll()
            // Login and Signup
            .and()
            .authorizeRequests().antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
            // All authenticated APIs
            .and()
            .authorizeRequests().anyRequest().authenticated()
    }

}