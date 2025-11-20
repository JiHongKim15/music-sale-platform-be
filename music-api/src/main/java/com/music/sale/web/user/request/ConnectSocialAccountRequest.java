package com.music.sale.web.user.request;

import com.music.sale.application.user.dto.ConnectSocialAccountInput;
import com.music.sale.domain.user.enum.SocialProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConnectSocialAccountRequest(
        @NotNull(message = "소셜 제공자는 필수입니다") SocialProvider provider,
        @NotBlank(message = "소셜 ID는 필수입니다") String socialId,
        String email,
        String name) {

    public ConnectSocialAccountInput toInput() {
        return new ConnectSocialAccountInput(
                provider,
                socialId,
                email,
                name);
    }
}

