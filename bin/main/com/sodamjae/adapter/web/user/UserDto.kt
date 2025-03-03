package com.sodamjae.adapter.web.user

import com.sodamjae.domain.user.User
import com.sodamjae.domain.user.model.UserRole

data class CreateUserRequest(
    val email: String,
    val name: String,
    val role: UserRole,
) {
    fun toDomain(): User {
        return User.create(
            email = User.Email(email),
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