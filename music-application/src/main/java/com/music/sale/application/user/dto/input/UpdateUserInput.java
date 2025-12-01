package com.music.sale.application.user.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserInput(
        @NotBlank
        String nickname,
        String profileImageUrl
) {
}
