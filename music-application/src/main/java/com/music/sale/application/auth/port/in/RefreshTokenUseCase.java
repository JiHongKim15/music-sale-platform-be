package com.music.sale.application.auth.port.in;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.TokenResponse;

public interface RefreshTokenUseCase {
  TokenResponse refreshToken(RefreshTokenCommand command);
}
