package com.music.sale.config.oauth2

import com.music.sale.application.auth.service.JwtService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2AuthenticationSuccessHandler(
    private val jwtService: JwtService,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val user =
            authentication.principal as com.music.sale.domain.user.User // Assuming your User class implements OAuth2User

        val token =
            jwtService.generateToken(
                userId = user.id ?: 0L,
                email = user.email?.value ?: "",
                role = user.role ?: com.music.sale.domain.user.enum.UserRole.USER,
            )

        // Redirect to a frontend URL with the token
        val redirectUrl = "http://localhost:3000/oauth2/redirect?token=\${token}"
        response.sendRedirect(redirectUrl)
    }
}
