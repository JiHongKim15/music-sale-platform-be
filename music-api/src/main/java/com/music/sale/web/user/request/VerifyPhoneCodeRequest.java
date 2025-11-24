package com.music.sale.web.user.request;

import com.music.sale.domain.user.enums.VerificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VerifyPhoneCodeRequest(
        @NotBlank(message = "휴대폰 번호는 필수입니다") String phoneNumber,
        @NotBlank(message = "인증 코드는 필수입니다") String verificationCode,
        @NotNull(message = "인증 타입은 필수입니다") VerificationType verificationType) {}

