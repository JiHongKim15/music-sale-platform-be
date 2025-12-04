package com.music.sale.application.auth.port.in;

public interface LogoutUseCase {
  void logout(String refreshToken);
}
