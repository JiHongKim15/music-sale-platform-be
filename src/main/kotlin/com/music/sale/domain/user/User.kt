// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user

import com.music.sale.domain.user.enum.UserRole

data class User(
    val id: Long? = null,
    val email: Email? = null,
    val provider: String? = null,
    val providerId: String? = null,
    val password: Password? = null,
    var name: Name? = null,
    var role: UserRole? = null,
) {
    companion object {
        fun create(
            email: String,
            provider: String,
            providerId: String,
            name: String,
            role: UserRole,
        ): User {
            return User(
                email = Email(email),
                provider = provider,
                providerId = providerId,
                name = Name(name),
                role = role,
            )
        }

        fun create(
            email: String,
            password: String,
            name: String,
            role: UserRole,
        ): User {
            return User(
                email = Email(email),
                password = Password(password),
                name = Name(name),
                role = role,
            )
        }
    }

    fun changeRole(newRole: UserRole) {
        this.role = newRole
    }

    // Value Objects
    @JvmInline
    value class Email(val value: String) {
        init {
            require(value.contains("@")) { "이메일 형식이 올바르지 않습니다" }
        }
    }

    @JvmInline
    value class Name(val value: String) {
        init {
            require(value.isNotBlank()) { "이름은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Password(val value: String) {
        init {
            require(value.isNotBlank()) { "비밀번호는 비어있을 수 없습니다" }
        }
    }
}
