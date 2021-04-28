package tech.arnav.conduit.conduit_springboot_kotlin.entities

import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.Email
import javax.validation.constraints.Max

@Entity(name = "users")
class UserEntity : BaseEntity() {
    @Column(unique = true)
    @Length(min = 4, max = 30, message = "username must be 4 to 30 chars in length")
    val username : String = ""

    @Email
    @Column(length = 50)
    val email: String = ""

    val password: String = ""

    val bio: String? = null

    val image: String? = null

}