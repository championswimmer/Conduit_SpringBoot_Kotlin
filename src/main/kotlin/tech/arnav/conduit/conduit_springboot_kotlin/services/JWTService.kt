package tech.arnav.conduit.conduit_springboot_kotlin.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity
import tech.arnav.conduit.conduit_springboot_kotlin.security.jwt.JWTAuthentication
import java.util.*

@Service
class JWTService(
    @Autowired val userService: UserService
) {
    companion object {
        const val JWT_KEY = "6LtyfKrhL7JrjRq04WpFUjREBfUOBgfS8Wo0QduEVzvfNeEz6C3lQXUArMc5b43m"
        const val JWT_VALIDITY_MS = 1000 * 60 * 60 * 24 * 7 // 1 week
    }

    private val key = Keys.hmacShaKeyFor(JWT_KEY.toByteArray(Charsets.UTF_8))

    fun createJwt(userEntity: UserEntity) =
        Jwts.builder()
            .setSubject(userEntity.username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + JWT_VALIDITY_MS))
            .signWith(key)
            .compact()

    fun decodeJwt(jwtString: String) : JWTAuthentication? {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwtString)
            .body

        val username = claims.subject
        val userEntity = userService.findUserByUsername(username)
        return JWTAuthentication(jwtString, userEntity)
    }


}