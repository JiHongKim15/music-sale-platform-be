package com.music.sale.persistence.user.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 1209600)
public class RefreshTokenEntity {

  @Id private String refreshToken;

  private Long userId;

  private String ip;

  private String deviceInfo;
}
