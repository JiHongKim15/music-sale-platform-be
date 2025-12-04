package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enums.UserRole;

public record UserInfo(Long userId, String email, String nickname, UserRole role) {

  public static UserInfo of(Long userId, String email, String nickname, UserRole role) {
    return new UserInfo(userId, email, nickname, role);
  }
}
