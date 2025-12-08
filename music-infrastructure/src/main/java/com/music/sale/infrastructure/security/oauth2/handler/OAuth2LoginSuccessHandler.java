package com.music.sale.infrastructure.security.oauth2.handler;

import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.application.auth.port.in.SocialLoginUseCase;
import com.music.sale.infrastructure.common.HttpRequestUtils;
import com.music.sale.infrastructure.security.oauth2.OAuth2AuthenticatedUser;
import com.music.sale.infrastructure.security.oauth2.userinfo.OAuth2UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
    prefix = "spring.security.oauth2.client.registration.google",
    name = "client-id")
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final SocialLoginUseCase socialLoginUseCase;

  @Value("${oauth2.redirect-uri}")
  private String redirectUri;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    OAuth2AuthenticatedUser oAuth2User = (OAuth2AuthenticatedUser) authentication.getPrincipal();
    OAuth2UserInfo userInfo = oAuth2User.getUserInfo();

    String ip = HttpRequestUtils.getClientIP(request);
    String deviceInfo = HttpRequestUtils.getUserAgent(request);

    SocialLoginCommand command =
        SocialLoginCommand.of(
            userInfo.getProvider(),
            userInfo.getProviderId(),
            userInfo.getEmail(),
            userInfo.getName(),
            userInfo.getProfileImage());

    TokenResponse tokenResponse = socialLoginUseCase.socialLogin(command, ip, deviceInfo);

    String targetUrl =
        UriComponentsBuilder.fromUriString(redirectUri)
            .queryParam("accessToken", tokenResponse.accessToken())
            .queryParam("refreshToken", tokenResponse.refreshToken())
            .queryParam("tokenType", tokenResponse.tokenType())
            .queryParam("expiresIn", tokenResponse.expiresIn())
            .build()
            .toUriString();

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
