package com.music.sale.application.user.dto.output;

import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserOutput(
        Long id,
        String nickname,
        String profileImageUrl,
        String email,
        UserRole role,
        UserStatus status,
        boolean isVerified,
        LocalDateTime createdAt
) {
}
