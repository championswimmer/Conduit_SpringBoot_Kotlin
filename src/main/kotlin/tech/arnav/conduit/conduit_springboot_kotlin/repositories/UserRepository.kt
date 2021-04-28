package tech.arnav.conduit.conduit_springboot_kotlin.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity

@Repository
interface UserRepository : PagingAndSortingRepository<UserEntity, Long> {

    @Query
    fun findByUsername(@Param("username") username: String): UserEntity?
}