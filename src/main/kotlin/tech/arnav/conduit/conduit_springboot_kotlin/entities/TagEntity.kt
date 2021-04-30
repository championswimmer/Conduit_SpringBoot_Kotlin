package tech.arnav.conduit.conduit_springboot_kotlin.entities

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity(name = "tags")
class TagEntity : BaseEntity() {
    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[a-z0-9]+\$", message = "tags can only contain [a-z] or [0-9] characters")
    @Length(min = 2, max = 20, message = "tag name must be 2 to 20 characters")
    lateinit var name: String

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_tags")
    val articles: List<ArticleEntity> = listOf()

}