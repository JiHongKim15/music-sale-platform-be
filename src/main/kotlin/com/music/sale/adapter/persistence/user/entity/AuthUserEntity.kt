// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "auth_users")
class AuthUserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false, unique = true) val userId: Long,
    @Column(nullable = false) var provider: String,
    @Column(nullable = true) var providerId: String? = null,
    @Column(nullable = true) var password: String? = null,
) : BaseEntity() {
    companion object {
        fun fromProvider(
            user: User,
            provider: String,
            providerId: String,
        ): AuthUserEntity {
            return AuthUserEntity(
                userId = user.id ?: 0,
                provider = provider,
                providerId = providerId,
            )
        }

        fun formEmail(
            user: User,
            password: String,
        ): AuthUserEntity {
            return AuthUserEntity(userId = user.id ?: 0, provider = "email", password = password)
        }
    }
}
