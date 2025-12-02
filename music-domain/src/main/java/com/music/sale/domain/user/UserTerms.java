package com.music.sale.domain.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTerms {
  private final Long id;
  private final Long userId;
  private final String title;
  private final String version;
  private final Boolean isAgreed;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public static UserTerms of(Long userId, String title, String version, Boolean isAgreed) {
    return UserTerms.builder()
        .userId(userId)
        .title(title)
        .version(version)
        .isAgreed(isAgreed)
        .build();
  }
}
