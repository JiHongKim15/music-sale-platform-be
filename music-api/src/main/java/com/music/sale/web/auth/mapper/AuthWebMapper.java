package com.music.sale.web.auth.mapper;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.web.auth.response.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthWebMapper {

  public RefreshTokenCommand toRefreshTokenCommand(
      String refreshToken, String ip, String deviceInfo) {
    return RefreshTokenCommand.of(refreshToken, ip, deviceInfo);
  }

  public TokenResponse toTokenResponse(
      com.music.sale.application.auth.dto.TokenResponse tokenResponse) {
    return new TokenResponse(
        tokenResponse.accessToken(), tokenResponse.refreshToken(), tokenResponse.expiresIn());
  }
}
