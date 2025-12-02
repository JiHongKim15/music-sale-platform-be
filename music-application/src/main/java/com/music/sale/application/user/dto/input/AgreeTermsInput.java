package com.music.sale.application.user.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AgreeTermsInput(
    @NotNull Long userId,
    @NotBlank String title,
    @NotBlank String version,
    @NotNull Boolean isAgreed) {}
