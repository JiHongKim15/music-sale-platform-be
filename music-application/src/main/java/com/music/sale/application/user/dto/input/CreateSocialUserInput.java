package com.music.sale.application.user.dto.input;

import com.music.sale.domain.user.enums.SocialProvider;

public record CreateSocialUserInput(
    SocialProvider provider,
    String providerId,
    String email,
    String nickname,
    String profileImageUrl) {

  public static CreateSocialUserInput of(
      SocialProvider provider,
      String providerId,
      String email,
      String nickname,
      String profileImageUrl) {
    return new CreateSocialUserInput(provider, providerId, email, nickname, profileImageUrl);
  }
}
