package com.music.sale.web.user.mapper

import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.application.user.dto.output.UserSocialOutput
import com.music.sale.application.user.dto.output.UserTermsOutput
import com.music.sale.web.user.response.GetUserResponse
import com.music.sale.web.user.response.GetUserSocialResponse
import com.music.sale.web.user.response.GetUserTermsResponse
import org.springframework.stereotype.Component

@Component
class UserWebMapper {
    fun toGetUserResponse(output: UserOutput): GetUserResponse =
        GetUserResponse(
            id = output.id,
            nickname = output.nickname,
            profileImageUrl = output.profileImageUrl,
            email = output.email,
            role = output.role,
            status = output.status,
            isVerified = output.isVerified,
            createdAt = output.createdAt,
        )

    fun toGetUserSocialResponses(outputs: List<UserSocialOutput>): List<GetUserSocialResponse> = outputs.map { toGetUserSocialResponse(it) }

    fun toGetUserSocialResponse(output: UserSocialOutput): GetUserSocialResponse =
        GetUserSocialResponse(
            provider = output.provider,
            providerId = output.providerId,
        )

    fun toGetUserTermsResponses(outputs: List<UserTermsOutput>): List<GetUserTermsResponse> = outputs.map { toGetUserTermsResponse(it) }

    fun toGetUserTermsResponse(output: UserTermsOutput): GetUserTermsResponse =
        GetUserTermsResponse(
            title = output.title,
            version = output.version,
            isAgreed = output.isAgreed,
            agreedAt = output.agreedAt,
        )
}
