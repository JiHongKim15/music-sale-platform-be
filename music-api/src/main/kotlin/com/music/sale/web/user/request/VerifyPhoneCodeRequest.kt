package com.music.sale.web.user.request

import com.music.sale.domain.user.enum.VerificationType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class VerifyPhoneCodeRequest(
    @field:NotBlank(message = "휴대폰 번호는 필수입니다") val phoneNumber: String,
    @field:NotBlank(message = "인증 코드는 필수입니다") val verificationCode: String,
    @field:NotNull(message = "인증 타입은 필수입니다") val verificationType: VerificationType,
)
