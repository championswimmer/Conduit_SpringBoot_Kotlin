package tech.arnav.conduit.conduit_springboot_kotlin.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity
import tech.arnav.conduit.conduit_springboot_kotlin.repositories.UsersRepository

@Service
class UserService {

    class UserNotFoundException() : RuntimeException("No such user found")

    @Autowired
    lateinit var repo: UsersRepository

    @Bean
    fun bcryptEncoder() = BCryptPasswordEncoder()

    fun createUser(email: String, username: String, password: String): UserEntity {
        return repo.save(UserEntity().apply {
            this.email = email
            this.password = password
            this.username = username
        })
    }

    fun findUserByUsername(username: String): UserEntity {
        repo.findByUsername(username)?.let {
            return it
        } ?: throw UserNotFoundException()
    }
}