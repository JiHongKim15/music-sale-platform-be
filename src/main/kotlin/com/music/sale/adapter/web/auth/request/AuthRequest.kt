// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.auth.request

import com.music.sale.domain.user.enum.UserRole

data class LoginRequest(
    val email: String,
    val password: String,
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole = UserRole.USER,
) 
