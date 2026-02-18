package com.music.sale.application.user.mapper

import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.application.user.dto.output.UserSocialOutput
import com.music.sale.application.user.dto.output.UserTermsOutput
import com.music.sale.domain.user.User
import com.music.sale.domain.user.UserSocial
import com.music.sale.domain.user.UserTerms
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toOutput(user: User): UserOutput =
        UserOutput(
            id = user.id,
            nickname = user.nickname,
            profileImageUrl = user.profileImageUrl,
            email = user.email,
            role = user.role,
            status = user.status,
            isVerified = user.isVerified,
            createdAt = null,
        )

    fun toOutput(userSocial: UserSocial): UserSocialOutput =
        UserSocialOutput(
            provider = userSocial.provider,
            providerId = userSocial.providerId,
        )

    fun toOutput(userTerms: UserTerms): UserTermsOutput =
        UserTermsOutput(
            title = userTerms.title,
            version = userTerms.version,
            isAgreed = userTerms.isAgreed,
            agreedAt = null,
        )
}
