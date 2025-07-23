// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user.enum

enum class UserRole {
    ADMIN,
    SELLER,
    USER,
    ;

    fun hasPermission(requiredRole: UserRole): Boolean {
        return when (this) {
            ADMIN -> true
            SELLER -> this == requiredRole
            USER -> this == requiredRole
        }
    }
}
