package com.music.sale.application.user.dto.input

import com.music.sale.domain.user.enums.SocialProvider

data class CreateSocialUserInput(
    val provider: SocialProvider,
    val providerId: String,
    val email: String,
    val nickname: String,
    val profileImageUrl: String,
) {
    companion object {
        @JvmStatic
        fun of(
            provider: SocialProvider,
            providerId: String,
            email: String,
            nickname: String,
            profileImageUrl: String,
        ): CreateSocialUserInput = CreateSocialUserInput(provider, providerId, email, nickname, profileImageUrl)
    }
}
