package com.sodamjae.adapter.web.user

import com.sodamjae.domain.user.User

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: User.UserRole,
) {
    fun toDomain(): User {
        return User.create(
            email = User.Email(email),
            password = User.Password.create(password),
            name = User.Name(name),
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