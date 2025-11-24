package com.music.sale.application.auth.dto;

import com.music.sale.domain.user.enums.UserRole;

public record RegisterInput(
        String email,
        String password,
        String name,
        UserRole role
) {
}
