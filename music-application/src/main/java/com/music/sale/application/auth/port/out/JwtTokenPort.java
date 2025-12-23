package com.music.sale.application.auth.port.out;

import com.music.sale.domain.user.User;
import java.util.Map;

public interface JwtTokenPort {
  String generateAccessToken(User user);

  String generateRefreshToken(User user);

  Map<String, Object> validateToken(String token);

  Long getUserIdFromToken(String token);

  Long getAccessTokenValidity();

  Long getRefreshTokenValidity();
}
