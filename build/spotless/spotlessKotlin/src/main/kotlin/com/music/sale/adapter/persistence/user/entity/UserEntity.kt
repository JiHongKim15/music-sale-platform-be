// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false, unique = true) val email: String,
    @Column(nullable = false) var name: String,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var role: UserRole,
    @Column(nullable = true) var provider: String? = null,
    @Column(nullable = true) var providerId: String? = null,
) : BaseEntity() {
    fun toDomain(): User {
        return User(
            id = id,
            email = User.Email(email),
            name = User.Name(name),
            role = role,
            provider = provider,
            providerId = providerId,
        )
    }

    fun formEmail(
        user: User,
        password: String,
    ): Any {
        return UserEntity(
            id = user.id,
            email = user.email?.value ?: "",
            name = user.name?.value ?: "",
            role = user.role ?: UserRole.USER,
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email?.value ?: "",
                name = user.name?.value ?: "",
                role = user.role ?: UserRole.USER,
                provider = user.provider,
                providerId = user.providerId,
            )
        }

        fun empty(): UserEntity {
            return UserEntity(id = 0L, email = "", name = "", role = UserRole.USER)
        }
    }
}
