package com.music.sale.persistence.user.mapper;

import com.music.sale.domain.user.vo.RefreshToken;
import com.music.sale.persistence.user.entity.RefreshTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapper {

  public RefreshToken toDomain(RefreshTokenEntity entity) {
    if (entity == null) {
      return null;
    }

    return RefreshToken.builder()
        .token(entity.getRefreshToken())
        .userId(entity.getUserId())
        .ip(entity.getIp())
        .deviceInfo(entity.getDeviceInfo())
        .expiresAt(entity.getExpiresAt())
        .build();
  }

  public RefreshTokenEntity toEntity(RefreshToken domain) {
    if (domain == null) {
      return null;
    }

    return RefreshTokenEntity.builder()
        .refreshToken(domain.getToken())
        .userId(domain.getUserId())
        .ip(domain.getIp())
        .deviceInfo(domain.getDeviceInfo())
        .expiresAt(domain.getExpiresAt())
        .build();
  }
}
