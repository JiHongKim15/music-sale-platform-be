package com.music.sale.application.auth.port.in;

import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;

public interface SocialLoginUseCase {
  TokenResponse socialLogin(SocialLoginCommand command, String ip, String deviceInfo);
}
