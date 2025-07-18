package com.music.sale.application.auth.dto

import com.music.sale.domain.user.enum.UserRole

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole,
)
