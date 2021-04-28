package tech.arnav.conduit.conduit_springboot_kotlin.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0

    @CreationTimestamp
    val createdAt : Date = Date()

    @UpdateTimestamp
    val updatedAt : Date = Date()

}