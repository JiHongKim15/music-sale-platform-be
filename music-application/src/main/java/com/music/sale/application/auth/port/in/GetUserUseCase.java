package com.music.sale.application.auth.port.in;

import com.music.sale.application.auth.dto.UserInfo;

public interface GetUserUseCase {
  UserInfo getUserInfo(Long userId);
}
