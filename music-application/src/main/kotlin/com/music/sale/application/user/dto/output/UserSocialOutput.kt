package com.music.sale.application.user.dto.output

import com.music.sale.domain.user.enums.SocialProvider

data class UserSocialOutput(
    val provider: SocialProvider?,
    val providerId: String?,
)
