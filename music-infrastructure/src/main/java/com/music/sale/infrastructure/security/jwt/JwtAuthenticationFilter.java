package com.music.sale.infrastructure.security.jwt;

import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.domain.user.enums.UserRole;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain)
      throws ServletException, IOException {

    try {
      String token = extractToken(request);

      if (token != null) {
        authenticateUser(token, request);
      }
    } catch (ExpiredJwtException e) {
      log.warn("Expired JWT token");
      request.setAttribute("exception", "EXPIRED_TOKEN");
    } catch (JwtException e) {
      log.warn("Invalid JWT token: {}", e.getMessage());
      request.setAttribute("exception", "INVALID_TOKEN");
    } catch (Exception e) {
      log.error("Authentication error", e);
    }

    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(BEARER_PREFIX.length());
    }

    return null;
  }

  private void authenticateUser(String token, HttpServletRequest request) {
    Map<String, Object> claims = jwtTokenProvider.validateToken(token);

    Long userId = ((Number) claims.get("userId")).longValue();
    String email = (String) claims.get("email");
    String nickname = (String) claims.get("nickname");
    String roleStr = (String) claims.get("role");

    UserRole role = UserRole.valueOf(roleStr);

    AuthenticatedUser user = AuthenticatedUser.of(userId, email, nickname, role);

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
