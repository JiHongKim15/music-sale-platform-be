package com.music.sale.infrastructure.security.oauth2;

import com.music.sale.infrastructure.security.oauth2.userinfo.OAuth2UserInfo;
import com.music.sale.infrastructure.security.oauth2.userinfo.OAuth2UserInfoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(
    prefix = "spring.security.oauth2.client.registration.google",
    name = "client-id")
public class OAuth2UserLoader extends DefaultOAuth2UserService {

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    return processOAuth2User(userRequest, oAuth2User);
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    OAuth2UserInfo userInfo =
        OAuth2UserInfoFactory.create(registrationId, oAuth2User.getAttributes());

    if (userInfo.getEmail() == null || userInfo.getEmail().isEmpty()) {
      throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
    }

    return new OAuth2AuthenticatedUser(userInfo, oAuth2User.getAttributes());
  }
}
