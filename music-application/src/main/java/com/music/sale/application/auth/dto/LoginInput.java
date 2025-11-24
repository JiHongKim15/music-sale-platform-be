package com.music.sale.application.auth.dto;

public record LoginInput(
        String email,
        String password
) {
}
