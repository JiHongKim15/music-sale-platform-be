package com.music.sale.adapter.web.user.reqeust

import com.music.sale.application.user.dto.CreateUserByEmailInput
import com.music.sale.application.user.dto.CreateUserByProviderInput


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