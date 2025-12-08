package com.music.sale.web.auth.response;

public record TokenResponseDto(String accessToken, String refreshToken, Long expiresIn) {}
