package com.music.sale.infrastructure.security.oauth2.userinfo;

import com.music.sale.domain.user.enums.SocialProvider;
import java.util.Map;

public class NaverOAuth2UserInfo implements OAuth2UserInfo {
  private final Map<String, Object> attributes;

  public NaverOAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  @SuppressWarnings("unchecked")
  public String getProviderId() {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    if (response == null) {
      return null;
    }
    return (String) response.get("id");
  }

  @Override
  @SuppressWarnings("unchecked")
  public String getEmail() {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    if (response == null) {
      return null;
    }
    return (String) response.get("email");
  }

  @Override
  @SuppressWarnings("unchecked")
  public String getName() {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    if (response == null) {
      return null;
    }
    return (String) response.get("name");
  }

  @Override
  @SuppressWarnings("unchecked")
  public String getProfileImage() {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    if (response == null) {
      return null;
    }
    return (String) response.get("profile_image");
  }

  @Override
  public SocialProvider getProvider() {
    return SocialProvider.NAVER;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }
}
