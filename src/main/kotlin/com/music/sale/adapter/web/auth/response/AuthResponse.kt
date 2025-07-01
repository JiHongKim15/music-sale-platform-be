// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.auth.response

import com.music.sale.domain.user.enum.UserRole

data class AuthResponse(
    val token: String,
    val userId: Long,
    val email: String,
    val name: String,
    val role: UserRole,
) 
