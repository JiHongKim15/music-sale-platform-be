package com.music.sale.application.auth.dto

data class LoginRequest(
    val email: String,
    val password: String,
)
