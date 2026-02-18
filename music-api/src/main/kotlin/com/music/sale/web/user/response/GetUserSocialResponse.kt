package com.music.sale.web.user.response

import com.music.sale.domain.user.enums.SocialProvider

data class GetUserSocialResponse(
    val provider: SocialProvider?,
    val providerId: String?,
)
