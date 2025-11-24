package com.music.sale.config.oauth2;

import com.music.sale.application.auth.service.JwtService;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    public OAuth2AuthenticationSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(
                user.getId() != null ? user.getId() : 0L,
                user.getEmail() != null ? user.getEmail().getValue() : "",
                user.getRole() != null ? user.getRole() : UserRole.USER);

        String redirectUrl =
                "http://localhost:3000/oauth2/redirect?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}

