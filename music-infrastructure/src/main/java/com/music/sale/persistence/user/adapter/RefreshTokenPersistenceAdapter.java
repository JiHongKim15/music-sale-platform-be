package com.music.sale.persistence.user.adapter;

import com.music.sale.application.auth.port.out.RefreshTokenPort;
import com.music.sale.persistence.user.entity.RefreshTokenEntity;
import com.music.sale.persistence.user.repository.RefreshTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {

  private final RefreshTokenRepository refreshTokenRepository;
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void saveRefreshToken(String refreshToken, Long userId, String ip, String deviceInfo) {
    RefreshTokenEntity entity =
        RefreshTokenEntity.builder()
            .refreshToken(refreshToken)
            .userId(userId)
            .ip(ip)
            .deviceInfo(deviceInfo)
            .expiresAt(System.currentTimeMillis() + 1209600000L) // 14일
            .build();

    refreshTokenRepository.save(entity);
  }

  @Override
  public Optional<Long> getUserIdByRefreshToken(String refreshToken) {
    return refreshTokenRepository
        .findByRefreshToken(refreshToken)
        .map(RefreshTokenEntity::getUserId);
  }

  @Override
  public void deleteRefreshToken(String refreshToken) {
    refreshTokenRepository.deleteByRefreshToken(refreshToken);
  }

  @Override
  public Long getRefreshTokenTTL(String refreshToken) {
    return refreshTokenRepository
        .findByRefreshToken(refreshToken)
        .map(entity -> entity.getExpiresAt() - System.currentTimeMillis())
        .orElse(0L);
  }
}
