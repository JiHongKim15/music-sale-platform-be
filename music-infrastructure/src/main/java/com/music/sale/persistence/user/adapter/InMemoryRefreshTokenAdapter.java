package com.music.sale.persistence.user.adapter;

import com.music.sale.application.auth.port.out.RefreshTokenPort;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "simple", matchIfMissing = true)
public class InMemoryRefreshTokenAdapter implements RefreshTokenPort {

  private final Map<String, RefreshTokenData> tokenStore = new ConcurrentHashMap<>();

  @Override
  public void saveRefreshToken(String refreshToken, Long userId, String ip, String deviceInfo) {
    long expiresAt = System.currentTimeMillis() + 1209600000L; // 14일
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
