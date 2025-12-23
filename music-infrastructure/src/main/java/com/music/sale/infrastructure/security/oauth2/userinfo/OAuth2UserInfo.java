package com.music.sale.infrastructure.security.oauth2.userinfo;

import com.music.sale.domain.user.enums.SocialProvider;
import java.util.Map;

public interface OAuth2UserInfo {
  String getProviderId();

  String getEmail();

  String getName();

  String getProfileImage();

  SocialProvider getProvider();

  Map<String, Object> getAttributes();
}
