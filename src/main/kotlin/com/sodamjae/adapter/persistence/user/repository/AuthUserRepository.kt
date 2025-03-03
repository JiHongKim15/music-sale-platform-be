package com.sodamjae.adapter.persistence.user.repository

import com.sodamjae.adapter.persistence.user.entity.AuthUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : JpaRepository<AuthUserEntity, Long> {
    fun findByUserId(userId: Long): AuthUserEntity?
    fun findByProviderAndProviderId(provider: String, providerId: String): AuthUserEntity?
} 