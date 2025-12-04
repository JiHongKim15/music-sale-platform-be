package com.music.sale.infrastructure.security.oauth2;

import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.application.auth.port.in.SocialLoginUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final SocialLoginUseCase socialLoginUseCase;

  @Value("${oauth2.redirect-uri}")
  private String redirectUri;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
    OAuth2UserInfo userInfo = oAuth2User.getUserInfo();

    // IP, Device Info 추출
    String ip = getClientIP(request);
    String deviceInfo = getUserAgent(request);

    // SocialLoginCommand 생성
    SocialLoginCommand command =
        SocialLoginCommand.of(
            userInfo.getProvider(),
            userInfo.getProviderId(),
            userInfo.getEmail(),
            userInfo.getName(),
            userInfo.getProfileImage());

    // 소셜 로그인 처리 (회원가입/로그인 + 토큰 생성)
    TokenResponse tokenResponse = socialLoginUseCase.socialLogin(command, ip, deviceInfo);

    // 프론트엔드로 리다이렉트 (토큰을 URL 파라미터로 전달)
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

  private String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  private String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }
}
