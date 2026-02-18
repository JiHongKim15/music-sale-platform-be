package com.music.sale.application.user.port.inport

import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.application.user.dto.output.UserSocialOutput
import com.music.sale.application.user.dto.output.UserTermsOutput
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enums.SocialProvider
import java.util.Optional

interface UserQueryUseCase {
    fun getUserById(userId: Long): UserOutput

    fun findById(userId: Long): Optional<User>

    fun findBySocialAccount(
        provider: SocialProvider,
        providerId: String,
    ): Optional<User>

    fun findByCi(ci: String): Optional<User>

    fun getSocialsByUserId(userId: Long): List<UserSocialOutput>

    fun getTermsByUserId(userId: Long): List<UserTermsOutput>
}
