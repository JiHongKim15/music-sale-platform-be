package com.music.sale.web.auth.mapper;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.web.auth.response.TokenResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthWebMapper {

  public RefreshTokenCommand toRefreshTokenCommand(
      String refreshToken, String ip, String deviceInfo) {
    return RefreshTokenCommand.of(refreshToken, ip, deviceInfo);
  }

  public TokenResponseDto toTokenResponseDto(TokenResponse tokenResponse) {
    return new TokenResponseDto(
        tokenResponse.accessToken(), tokenResponse.refreshToken(), tokenResponse.expiresIn());
  }
}
