package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enum.UserRole;

public record AuthResponse(
        String token,
        Long userId,
        String email,
        String name,
        UserRole role
) {
}

