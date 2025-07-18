package com.music.sale.web.user.request

import com.music.sale.domain.user.enum.Gender
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.domain.user.enum.UserType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateUserByProviderRequest(
    @field:NotBlank(message = "이름은 필수입니다") val name: String,
    @field:NotBlank(message = "이메일은 필수입니다") val email: String,
    @field:NotNull(message = "소셜 제공자는 필수입니다") val provider: SocialProvider,
    @field:NotBlank(message = "소셜 ID는 필수입니다") val socialId: String,
    val phoneNumber: String? = null,
    val gender: Gender? = null,
    val birthDate: String? = null,
    val userType: UserType = UserType.BUYER,
)
