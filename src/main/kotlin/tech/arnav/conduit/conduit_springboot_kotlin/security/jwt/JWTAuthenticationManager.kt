package tech.arnav.conduit.conduit_springboot_kotlin.security.jwt

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class JWTAuthenticationManager : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        return authentication
    }
}