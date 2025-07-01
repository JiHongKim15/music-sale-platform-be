// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.auth

import com.music.sale.adapter.web.auth.request.LoginRequest
import com.music.sale.adapter.web.auth.request.RegisterRequest
import com.music.sale.adapter.web.auth.response.AuthResponse
import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.application.auth.service.AuthService
import com.music.sale.common.ResponseCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        val response = authService.login(request)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                code = ResponseCode.LOGIN_SUCCESS.code,
            ),
        )
    }

    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequest,
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        val response = authService.register(request)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                code = ResponseCode.REGISTER_SUCCESS.code,
            ),
        )
    }
} 
