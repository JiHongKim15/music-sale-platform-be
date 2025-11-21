package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enum.UserRole;

public record RegisterRequest(
        String email,
        String password,
        String name,
        UserRole role
) {
}

