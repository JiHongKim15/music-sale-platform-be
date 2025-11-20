package com.music.sale.web.user.request;

import jakarta.validation.constraints.NotBlank;

public record VerifyTwoFactorRequest(
        @NotBlank(message = "2FA 코드는 필수입니다") String twoFactorCode) {}

