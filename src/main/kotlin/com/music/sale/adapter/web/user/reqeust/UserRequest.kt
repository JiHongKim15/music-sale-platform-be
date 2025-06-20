// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.user.reqeust

import com.music.sale.domain.user.enum.UserRole

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
)

data class UpdateUserRequest(
    val name: String,
    val phoneNumber: String,
)

data class CreateUserByEmailRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole,
)

data class CreateUserByProviderRequest(
    val email: String,
    val provider: String,
    val providerId: String,
    val name: String,
    val role: UserRole,
)
