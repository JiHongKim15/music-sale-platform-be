package com.music.sale.web.user.response;

import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import java.time.LocalDateTime;

public record GetUserResponse(
    Long id,
    String nickname,
    String profileImageUrl,
    String email,
    UserRole role,
    UserStatus status,
    boolean isVerified,
    LocalDateTime createdAt) {}
