// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.auth.service

import com.music.sale.domain.user.enum.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    @Value("\${jwt.secret:your-secret-key-here-make-it-long-enough-for-security}")
    private lateinit var secret: String

    @Value("\${jwt.expiration:86400000}") // 24시간
    private var expiration: Long = 86400000

    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateToken(
        userId: Long,
        email: String,
        role: UserRole,
    ): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("email", email)
            .claim("role", role.name)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getClaimsFromToken(token: String): Claims {
        val jws = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
        return jws.payload
    }

    fun getUserIdFromToken(token: String?): Long? {
        if (token.isNullOrBlank()) return null
        val claims = getClaimsFromToken(token)
        return claims.subject.toLongOrNull()
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
