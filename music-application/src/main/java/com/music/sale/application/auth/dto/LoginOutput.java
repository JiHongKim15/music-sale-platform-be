package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enums.UserRole;

public record LoginOutput(
        String token,
        Long userId,
        String email,
        String name,
        UserRole role
) {
}
