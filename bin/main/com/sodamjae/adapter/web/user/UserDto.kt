package com.music.sale.adapter.web.user

import com.music.sale.application.user.port.`in`.CreateUserByEmailInput
import com.music.sale.application.user.port.`in`.CreateUserByProviderInput
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole

data class CreateUserByEmailRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole,
) {
    fun toUserUseCase(): CreateUserByEmailInput {
        return CreateUserByEmailInput(
            email = email,
            password = password,
            name = name,
            role = role
        )
    }
}

data class CreateUserByProviderRequest(
    val email: String,
    val provider: String,
    val providerId: String,
    val name: String,
    val role: UserRole,
) {
    fun toUserUseCase(): CreateUserByProviderInput {
        return CreateUserByProviderInput(
            email = email,
            provider = provider,
            providerId = providerId,
            name = name,
            role = role
        )
    }
}

data class UserResponse(
    val id: Long?,
    val email: String,
    val name: String
) {
    companion object {
        fun from(user: User) = UserResponse(
            id = user.id,
            email = user.email.value,
            name = user.getName().value
        )
    }
} 