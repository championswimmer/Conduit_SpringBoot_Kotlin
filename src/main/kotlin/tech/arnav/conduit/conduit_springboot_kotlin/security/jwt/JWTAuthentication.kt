package tech.arnav.conduit.conduit_springboot_kotlin.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity

class JWTAuthentication(
    val jwtString: String,
    val userEntity: UserEntity
) : Authentication {
    override fun getName() = userEntity.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = arrayListOf(SimpleGrantedAuthority("user"))

    override fun getCredentials() = jwtString

    override fun getDetails() = null

    override fun getPrincipal() = userEntity

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        // no-op
    }
}