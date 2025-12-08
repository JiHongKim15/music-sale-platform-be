package com.music.sale.infrastructure.security.oauth2.userinfo;

import com.music.sale.domain.user.enums.SocialProvider;
import java.util.Map;

public class AppleOAuth2UserInfo implements OAuth2UserInfo {
  private final Map<String, Object> attributes;

  public AppleOAuth2UserInfo(Map<String, Object> attributes) {
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
    // Apple은 최초 1회만 name을 제공하므로, 없을 경우 email을 사용
    String name = (String) attributes.get("name");
    if (name != null) {
      return name;
    }
    String email = getEmail();
    return email != null ? email.split("@")[0] : null;
  }

  @Override
  public String getProfileImage() {
    // Apple은 프로필 이미지를 제공하지 않음
    return null;
  }

  @Override
  public SocialProvider getProvider() {
    return SocialProvider.APPLE;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }
}
