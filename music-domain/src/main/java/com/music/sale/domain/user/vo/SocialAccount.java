package com.music.sale.domain.user.vo;

import com.music.sale.domain.user.enums.SocialProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialAccount {
  private final Long id;
  private final Long userId;
  private final SocialProvider provider;
  private final String providerId;
}
