package com.music.sale.application.user.port.`in`

import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole

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
