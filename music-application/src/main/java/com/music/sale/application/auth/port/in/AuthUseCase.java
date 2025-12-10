package com.music.sale.application.auth.port.in;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;

public interface AuthUseCase {

  TokenResponse loginWithSocialAccount(SocialLoginCommand command, String ip, String deviceInfo);

  TokenResponse renewAccessToken(RefreshTokenCommand command);

  void logout(String refreshToken);
}
