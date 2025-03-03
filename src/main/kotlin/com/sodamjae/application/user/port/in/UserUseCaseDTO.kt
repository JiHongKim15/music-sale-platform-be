package com.sodamjae.application.user.port.`in`

import com.sodamjae.domain.user.User
import com.sodamjae.domain.user.model.UserRole

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
            role = role
        )
    }
}

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
            role = role
        )
    }
}
