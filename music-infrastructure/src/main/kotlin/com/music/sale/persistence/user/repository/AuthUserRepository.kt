// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.user.repository

import com.music.sale.persistence.user.entity.AuthUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : JpaRepository<AuthUserEntity, Long> {
    fun findByUserId(userId: Long): AuthUserEntity?
}
