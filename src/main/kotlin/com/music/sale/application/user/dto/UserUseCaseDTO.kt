// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.dto

import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole

// UseCase의 출력을 나타내는 DTO
data class UserOutput(
    val id: Long,
    val email: String,
    val name: String,
    val role: UserRole,
)

// 이메일 회원가입 입력 DTO
data class CreateUserByEmailInput(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole,
) {
    fun toDomain(): User {
        return User.create(
            email = email,
            password = password,
            name = name,
            role = role,
        )
    }
}

// 소셜 회원가입 입력 DTO
data class CreateUserByProviderInput(
    val email: String,
    val provider: String,
    val providerId: String,
    val name: String,
    val role: UserRole,
) {
    fun toDomain(): User {
        return User.create(
            email = email,
            provider = provider,
            providerId = providerId,
            name = name,
            role = role,
        )
    }
}
