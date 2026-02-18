package com.music.sale.domain.user

import com.music.sale.domain.user.enums.SocialProvider

data class UserSocial(
    val id: Long? = null,
    val userId: Long? = null,
    val provider: SocialProvider? = null,
    val providerId: String? = null,
)
