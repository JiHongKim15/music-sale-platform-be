package com.music.sale.infrastructure.security.oauth2;

import com.music.sale.domain.user.enums.SocialProvider;
import java.util.Map;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(
      String registrationId, Map<String, Object> attributes) {
    SocialProvider provider = SocialProvider.valueOf(registrationId.toUpperCase());

    return switch (provider) {
      case KAKAO -> new KakaoOAuth2UserInfo(attributes);
      case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
      case NAVER -> new NaverOAuth2UserInfo(attributes);
      case APPLE -> new AppleOAuth2UserInfo(attributes);
    };
  }
}
