package tech.arnav.conduit.conduit_springboot_kotlin.entities

import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull

@Entity(name = "users")
class UserEntity : BaseEntity() {
    @Column(unique = true)
    @Length(min = 4, max = 30, message = "username must be 4 to 30 chars in length")
    @NotNull
    var username : String = ""

    @Email
    @Column(length = 50)
    @NotNull
    var email: String = ""

    @NotNull
    var password: String = ""

    @Lob
    val bio: String? = null

    val image: String? = null

}