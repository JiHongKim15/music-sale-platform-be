package com.music.sale.web.user.request;

import com.music.sale.domain.user.enums.Gender;
import com.music.sale.domain.user.enums.SocialProvider;
import com.music.sale.domain.user.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserByProviderRequest(
        @NotBlank(message = "이름은 필수입니다") String name,
        @NotBlank(message = "이메일은 필수입니다") String email,
        @NotNull(message = "소셜 제공자는 필수입니다") SocialProvider provider,
        @NotBlank(message = "소셜 ID는 필수입니다") String socialId,
        String phoneNumber,
        Gender gender,
        String birthDate,
        UserType userType) {

    public CreateUserByProviderRequest {
        if (userType == null) {
            userType = UserType.BUYER;
        }
    }
}

