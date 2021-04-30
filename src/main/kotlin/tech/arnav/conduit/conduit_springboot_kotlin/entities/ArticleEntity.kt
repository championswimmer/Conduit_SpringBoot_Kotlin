package tech.arnav.conduit.conduit_springboot_kotlin.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "articles")
class ArticleEntity : BaseEntity() {
    @NotNull
    val slug: String = ""

    @NotNull
    val title: String = ""

    @Lob
    val description: String? = null

    @Lob
    val body: String? = null

    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var author: UserEntity

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val comments: List<CommentEntity> = listOf()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_tags")
    val tags: List<TagEntity> = listOf()

}