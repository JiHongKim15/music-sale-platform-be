package com.music.sale.web.user.request

import jakarta.validation.constraints.NotBlank

data class VerifyTwoFactorRequest(
    @field:NotBlank(message = "2FA 코드는 필수입니다") val twoFactorCode: String,
)
