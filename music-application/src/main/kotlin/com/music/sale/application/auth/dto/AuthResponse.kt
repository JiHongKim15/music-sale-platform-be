package com.music.sale.application.auth.dto

import com.music.sale.domain.user.enum.UserRole

data class AuthResponse(
    val token: String,
    val userId: Long,
    val email: String,
    val name: String,
    val role: UserRole,
)
