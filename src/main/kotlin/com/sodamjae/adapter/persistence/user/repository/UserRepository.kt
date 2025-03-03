package com.sodamjae.adapter.persistence.user.repository

import com.sodamjae.adapter.persistence.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun save(userEntity: UserEntity): UserEntity
    fun findByEmail(email: String): UserEntity?
    override fun findById(id: Long): Optional<UserEntity>

} 