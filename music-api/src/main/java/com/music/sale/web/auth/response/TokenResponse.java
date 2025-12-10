package com.music.sale.web.auth.response;

public record TokenResponse(String accessToken, String refreshToken, Long expiresIn) {}
