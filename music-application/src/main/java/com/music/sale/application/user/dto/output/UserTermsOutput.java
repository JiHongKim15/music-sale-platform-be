package com.music.sale.application.user.dto.output;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserTermsOutput(
        String title,
        String version,
        Boolean isAgreed,
        LocalDateTime agreedAt
) {
}
