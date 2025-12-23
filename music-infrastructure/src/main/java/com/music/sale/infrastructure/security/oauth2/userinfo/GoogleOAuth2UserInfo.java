package com.music.sale.infrastructure.security.oauth2.userinfo;

import com.music.sale.domain.user.enums.SocialProvider;
import java.util.Map;

public class GoogleOAuth2UserInfo implements OAuth2UserInfo {
  private final Map<String, Object> attributes;

  public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProviderId() {
    return (String) attributes.get("sub");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }

  @Override
  public String getProfileImage() {
    return (String) attributes.get("picture");
  }

  @Override
  public SocialProvider getProvider() {
    return SocialProvider.GOOGLE;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }
}
