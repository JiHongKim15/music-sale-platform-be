// Copyright (C) 2024 Your Name or Company
package com.music.sale.config

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.SecretKey

@Configuration
class JwtConfig {
    @Value("\${jwt.secret:your-secret-key-here-make-it-long-enough-for-security}")
    private lateinit var secret: String

    @Bean
    fun secretKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }
} 
