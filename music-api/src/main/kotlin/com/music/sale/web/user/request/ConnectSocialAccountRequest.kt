package com.music.sale.web.user.request

import com.music.sale.application.user.dto.ConnectSocialAccountInput
import com.music.sale.domain.user.enum.SocialProvider
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ConnectSocialAccountRequest(
    @field:NotNull(message = "소셜 제공자는 필수입니다") val provider: SocialProvider,
    @field:NotBlank(message = "소셜 ID는 필수입니다") val socialId: String,
    val email: String? = null,
    val name: String? = null,
) {
    fun toInput(): ConnectSocialAccountInput {
        return ConnectSocialAccountInput(
            provider = provider,
            providerId = socialId,
            providerEmail = email,
            providerName = name,
        )
    }
}
