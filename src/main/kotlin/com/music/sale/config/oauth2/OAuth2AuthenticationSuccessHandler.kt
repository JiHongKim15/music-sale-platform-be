package com.music.sale.config.oauth2

import com.music.sale.application.auth.service.JwtService
import com.music.sale.domain.user.enum.UserRole
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class OAuth2AuthenticationSuccessHandler(
    private val jwtService: JwtService,
) : AuthenticationSuccessHandler {
    @Throws(IOException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val oAuth2User = authentication.principal as OAuth2User
        val email = oAuth2User.attributes["email"] as? String ?: ""
        val name = oAuth2User.attributes["name"] as? String ?: ""

        // 임시로 userId 1L 사용 (실제로는 DB에서 조회/생성)
        val token =
            jwtService.generateToken(
                userId = 1L,
                email = email,
                role = UserRole.USER,
            )

        // 프론트엔드로 JWT 토큰과 함께 리다이렉트
        val redirectUrl = "http://localhost:3000/oauth2/success?token=$token&email=$email&name=$name"
        response.sendRedirect(redirectUrl)
    }
} 
