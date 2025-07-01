// Copyright (C) 2024 Your Name or Company
package com.music.sale.config

import com.music.sale.application.auth.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtService: JwtService) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = extractTokenFromRequest(request)

        if (StringUtils.hasText(token) && jwtService.validateToken(token!!)) {
            val userId = jwtService.getUserIdFromToken(token)
            val email = jwtService.getEmailFromToken(token)
            val role = jwtService.getRoleFromToken(token)

            if (userId != null && email != null && role != null) {
                val authorities = listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
                val authentication =
                    UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        authorities,
                    )

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun extractTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
} 
