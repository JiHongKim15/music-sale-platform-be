package com.music.sale.infrastructure.security.jwt;

import com.music.sale.application.auth.port.out.JwtTokenPort;
import com.music.sale.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements JwtTokenPort {

  private final JwtProperties jwtProperties;

  @Override
  public String generateAccessToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    claims.put("email", user.getEmail());
    claims.put("nickname", user.getNickname());
    claims.put("role", user.getRole().name());

    return createToken(claims, user.getEmail(), jwtProperties.getAccessTokenValidity());
  }

  @Override
  public String generateRefreshToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());

    return createToken(claims, user.getEmail(), jwtProperties.getRefreshTokenValidity());
  }

  private String createToken(Map<String, Object> claims, String subject, Long validity) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + validity);

    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(getSigningKey())
        .compact();
  }

  @Override
  public Map<String, Object> validateToken(String token) {
    Claims claims =
        Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();

    return new HashMap<>(claims);
  }

  @Override
  public Long getUserIdFromToken(String token) {
    Claims claims =
        Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();

    return claims.get("userId", Long.class);
  }

  @Override
  public Long getAccessTokenValidity() {
    return jwtProperties.getAccessTokenValidity();
  }

  @Override
  public Long getRefreshTokenValidity() {
    return jwtProperties.getRefreshTokenValidity();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
  }
}
