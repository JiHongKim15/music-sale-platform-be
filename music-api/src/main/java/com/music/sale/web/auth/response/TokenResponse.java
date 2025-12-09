package com.music.sale.web.auth.response;

public record TokenResponseDTO(String accessToken, String refreshToken, Long expiresIn) {
}
