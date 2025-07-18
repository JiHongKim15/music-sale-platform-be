// Copyright (C)2024ur Name or Company
package com.music.sale.application.auth.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.music.sale.domain.user.enum.UserRole
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.jwt.Jwt
import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.MacSigner
import org.springframework.security.jwt.crypto.sign.Signer
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    @Value("\${jwt.secret:your-secret-key-here-make-it-long-enough-for-security}")
    private lateinit var secret: String

    @Value("\${jwt.expiration:86400000}") // 24시간
    private var expiration: Long = 86400000

    private val objectMapper = ObjectMapper()
    private val signer: Signer by lazy { MacSigner(secret) }

    fun generateToken(
        userId: Long,
        email: String,
        role: UserRole,
    ): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        val claims =
            mapOf(
                "sub" to userId.toString(),
                "email" to email,
                "role" to role.name,
                "iat" to (now.time / 1000).toInt(),
                "exp" to (expiryDate.time / 1000).toInt(),
            )

        val claimsJson = objectMapper.writeValueAsString(claims)
        return JwtHelper.encode(claimsJson, signer).encoded
    }

    fun validateToken(token: String): Boolean {
        return try {
            JwtHelper.decode(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getClaimsFromToken(token: String): Map<String, Any> {
        val jwt: Jwt = JwtHelper.decode(token)
        return objectMapper.readValue(jwt.claims, Map::class.java) as Map<String, Any>
    }

    fun getUserIdFromToken(token: String?): Long? {
        if (token.isNullOrBlank()) return null
        val claims = getClaimsFromToken(token)
        return claims["sub"]?.toString()?.toLongOrNull()
    }

    fun getEmailFromToken(token: String?): String? {
        if (token.isNullOrBlank()) return null
        val claims = getClaimsFromToken(token)
        return claims["email"] as? String
    }

    fun getRoleFromToken(token: String?): UserRole? {
        if (token.isNullOrBlank()) return null
        val claims = getClaimsFromToken(token)
        val roleString = claims["role"] as? String ?: return null
        return try {
            UserRole.valueOf(roleString)
        } catch (e: Exception) {
            null
        }
    }
}
