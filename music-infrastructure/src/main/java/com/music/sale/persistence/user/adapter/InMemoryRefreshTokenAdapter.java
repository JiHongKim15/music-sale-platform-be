package com.music.sale.persistence.user.adapter;

import com.music.sale.application.auth.port.out.RefreshTokenPort;
import com.music.sale.infrastructure.security.jwt.JwtProperties;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "simple", matchIfMissing = true)
public class InMemoryRefreshTokenAdapter implements RefreshTokenPort {

  private final Map<String, RefreshTokenData> tokenStore = new ConcurrentHashMap<>();
  private final JwtProperties jwtProperties;

  @Override
  public void saveRefreshToken(String refreshToken, Long userId, String ip, String deviceInfo) {
    long expiresAt = System.currentTimeMillis() + jwtProperties.getRefreshTokenValidity();
    tokenStore.put(refreshToken, new RefreshTokenData(userId, ip, deviceInfo, expiresAt));
  }

  @Override
  public Optional<Long> getUserIdByRefreshToken(String refreshToken) {
    RefreshTokenData data = tokenStore.get(refreshToken);
    if (data == null || data.expiresAt < System.currentTimeMillis()) {
      tokenStore.remove(refreshToken);
      return Optional.empty();
    }
    return Optional.of(data.userId);
  }

  @Override
  public void deleteRefreshToken(String refreshToken) {
    tokenStore.remove(refreshToken);
  }

  @Override
  public Long getRefreshTokenTTL(String refreshToken) {
    RefreshTokenData data = tokenStore.get(refreshToken);
    if (data == null) {
      return 0L;
    }
    return Math.max(0, data.expiresAt - System.currentTimeMillis());
  }

  private record RefreshTokenData(Long userId, String ip, String deviceInfo, long expiresAt) {}
}
