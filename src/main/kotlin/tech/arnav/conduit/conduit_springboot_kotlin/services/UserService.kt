package tech.arnav.conduit.conduit_springboot_kotlin.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity
import tech.arnav.conduit.conduit_springboot_kotlin.repositories.UserRepository

@Service
class UserService {

    class UserNotFoundException() : RuntimeException("No such user found")

    @Autowired
    lateinit var repo: UserRepository

    fun findUserByUsername(username: String): UserEntity {
        repo.findByUsername(username)?.let {
            return it
        } ?: throw UserNotFoundException()
    }
}