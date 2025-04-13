package com.sodamjae.domain.user.enum

enum class UserRole {
    ADMIN, SELLER, USER;

    fun hasPermission(requiredRole: UserRole): Boolean {
        return when (this) {
            ADMIN -> true
            SELLER -> this == requiredRole
            USER -> this == requiredRole
        }
    }
} 