package com.music.sale.domain.user;

import com.music.sale.domain.user.enums.SocialProvider;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSocial {
  private final Long id;
  private final Long userId;
  private final SocialProvider provider;
  private final String providerId;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
}
