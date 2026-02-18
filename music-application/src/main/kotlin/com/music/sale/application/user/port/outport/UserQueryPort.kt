package com.music.sale.application.user.port.outport

import com.music.sale.domain.user.User
import com.music.sale.domain.user.UserSocial
import com.music.sale.domain.user.UserTerms
import com.music.sale.domain.user.enums.SocialProvider
import java.util.Optional

interface UserQueryPort {
    fun findById(userId: Long): Optional<User>

    fun findBySocialAccount(
        provider: SocialProvider,
        providerId: String,
    ): Optional<User>

    fun findSocialsByUserId(userId: Long): List<UserSocial>

    fun findTermsByUserId(userId: Long): List<UserTerms>

    fun findByCi(ci: String): Optional<User>
}
