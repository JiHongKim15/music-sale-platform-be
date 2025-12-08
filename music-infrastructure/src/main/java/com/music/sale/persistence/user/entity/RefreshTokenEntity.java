package com.music.sale.persistence.user.entity;

import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 1209600) // 14일
public class RefreshTokenEntity {

  @Id private String refreshToken;

  @Indexed private Long userId;

  private String ip;

  private String deviceInfo;

  private Long expiresAt;
}
