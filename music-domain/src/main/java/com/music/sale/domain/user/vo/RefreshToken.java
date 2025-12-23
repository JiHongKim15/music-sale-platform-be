package com.music.sale.domain.user.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshToken {
  private final String token;
  private final Long userId;
  private final String ip;
  private final String deviceInfo;
  private final Long expiresAt;
}
