package tech.arnav.conduit.conduit_springboot_kotlin.entities

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity(name = "comments")
class CommentEntity : BaseEntity() {

    @Lob
    @NotNull
    val body: String = ""

    @ManyToOne
    lateinit var author: UserEntity
}