// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.auth.service

import com.music.sale.application.auth.dto.AuthResponse
import com.music.sale.application.auth.dto.LoginRequest
import com.music.sale.application.auth.dto.RegisterRequest
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class AuthService(
        private val userPort: UserPort,
        private val jwtService: JwtService,
        private val passwordEncoder: PasswordEncoder,
) {
    fun login(request: LoginRequest): AuthResponse {
        val user =
                userPort.findByEmail(request.email)
                        ?: throw IllegalArgumentException("Invalid email or password")

        // TODO: 실제 비밀번호 검증 로직 구현 필요
        // 현재는 임시로 이메일만 확인
        if (user.email?.value != request.email) {
            throw IllegalArgumentException("Invalid email or password")
        }

        val token =
                jwtService.generateToken(
                        userId = user.id ?: 0L,
                        email = user.email?.value ?: "",
                        role = user.role ?: UserRole.USER,
                )

        return AuthResponse(
                token = token,
                userId = user.id ?: 0L,
                email = user.email?.value ?: "",
                name = user.name?.value ?: "",
                role = user.role ?: UserRole.USER,
        )
    }

    fun register(request: RegisterRequest): AuthResponse {
        // 이메일 중복 확인
        val existingUser = userPort.findByEmail(request.email)
        if (existingUser != null) {
            throw IllegalArgumentException("Email already exists")
        }

        // 새 사용자 생성
        val newUser =
                User(
                        id = null,
                        email = User.Email(request.email),
                        name = User.Name(request.name),
                        role = request.role,
                        provider = null,
                        providerId = null,
                )

        val savedUser = userPort.saveEmail(newUser, request.password)

        val token =
                jwtService.generateToken(
                        userId = savedUser.id ?: 0L,
                        email = savedUser.email?.value ?: "",
                        role = savedUser.role ?: UserRole.USER,
                )

        return AuthResponse(
                token = token,
                userId = savedUser.id ?: 0L,
                email = savedUser.email?.value ?: "",
                name = savedUser.name?.value ?: "",
                role = savedUser.role ?: UserRole.USER,
        )
    }
}
