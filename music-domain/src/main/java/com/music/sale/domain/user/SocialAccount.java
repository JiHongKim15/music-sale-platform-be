package com.music.sale.domain.user;

public record SocialAccount(SocialProvider provider, String providerId) {
  public SocialAccount {
    if (provider == null) {
      throw new IllegalArgumentException("Provider cannot be null");
    }
    if (providerId == null || providerId.isBlank()) {
      throw new IllegalArgumentException("ProviderId cannot be empty");
    }
  }

  public static SocialAccount of(SocialProvider provider, String providerId) {
    return new SocialAccount(provider, providerId);
  }
}
