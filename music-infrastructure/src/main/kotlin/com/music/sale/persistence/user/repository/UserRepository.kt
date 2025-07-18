// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.user.repository

import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.persistence.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun save(userEntity: UserEntity): UserEntity

    fun findByEmail(email: String): UserEntity?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun findByPhoneNumber(phoneNumber: String): UserEntity?

    fun findByProviderAndProviderId(
        provider: SocialProvider,
        providerId: String,
    ): UserEntity?

    override fun findById(id: Long): Optional<UserEntity>
}
