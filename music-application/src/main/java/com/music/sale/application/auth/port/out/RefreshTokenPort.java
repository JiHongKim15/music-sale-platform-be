package com.music.sale.application.auth.port.out;

import java.util.Optional;

public interface RefreshTokenPort {
  void saveRefreshToken(String refreshToken, Long userId, String ip, String deviceInfo);

  Optional<Long> getUserIdByRefreshToken(String refreshToken);

  void deleteRefreshToken(String refreshToken);

  Long getRefreshTokenTTL(String refreshToken);
}
