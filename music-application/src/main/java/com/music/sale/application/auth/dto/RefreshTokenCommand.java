package com.music.sale.application.auth.dto;

public record RefreshTokenCommand(String refreshToken, String ip, String deviceInfo) {

  public static RefreshTokenCommand of(String refreshToken, String ip, String deviceInfo) {
    return new RefreshTokenCommand(refreshToken, ip, deviceInfo);
  }
}
