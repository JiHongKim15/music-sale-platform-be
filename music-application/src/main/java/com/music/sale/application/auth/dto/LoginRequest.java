package com.music.sale.application.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}

