package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enums.SocialProvider;

public record SocialLoginCommand(
    SocialProvider provider,
    String providerId,
    String email,
    String nickname,
    String profileImageUrl) {

  public static SocialLoginCommand of(
      SocialProvider provider,
      String providerId,
      String email,
      String nickname,
      String profileImageUrl) {
    return new SocialLoginCommand(provider, providerId, email, nickname, profileImageUrl);
  }
}
