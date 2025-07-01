// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.user.reqeust

import com.music.sale.adapter.persistence.user.entity.Gender
import com.music.sale.adapter.persistence.user.entity.SocialProvider
import com.music.sale.adapter.persistence.user.entity.UserType
import com.music.sale.adapter.persistence.user.entity.VerificationType
import com.music.sale.domain.user.enum.UserRole
import java.time.LocalDate

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
)

data class UpdateUserRequest(
    val name: String? = null,
    val nickname: String? = null,
    val phoneNumber: String? = null,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val userType: UserType? = null,
    val preferredPriceRangeMin: Int? = null,
    val preferredPriceRangeMax: Int? = null,
    val marketingAgreed: Boolean? = null,
)

data class CreateUserByEmailRequest(
    val email: String,
    val password: String,
    val name: String,
    val nickname: String,
    val role: UserRole,
    val phoneNumber: String? = null,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val userType: UserType = UserType.BUYER,
    val marketingAgreed: Boolean = false,
)

data class CreateUserByProviderRequest(
    val email: String,
    val provider: SocialProvider,
    val providerId: String,
    val name: String,
    val nickname: String,
    val role: UserRole,
    val phoneNumber: String? = null,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val userType: UserType = UserType.BUYER,
    val marketingAgreed: Boolean = false,
    val providerEmail: String? = null,
    val providerName: String? = null,
    val providerProfileImage: String? = null,
)

data class SendPhoneVerificationRequest(
    val phoneNumber: String,
    val verificationType: VerificationType,
)

data class VerifyPhoneCodeRequest(
    val phoneNumber: String,
    val verificationCode: String,
    val verificationType: VerificationType,
)

data class ConnectSocialAccountRequest(
    val provider: SocialProvider,
    val providerId: String,
    val providerEmail: String? = null,
    val providerName: String? = null,
    val providerProfileImage: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val tokenExpiresAt: java.time.LocalDateTime? = null,
) {
    fun toInput() = com.music.sale.application.user.dto.ConnectSocialAccountInput(
        provider = provider,
        providerId = providerId,
        providerEmail = providerEmail,
        providerName = providerName,
        providerProfileImage = providerProfileImage,
        accessToken = accessToken,
        refreshToken = refreshToken,
        tokenExpiresAt = tokenExpiresAt,
    )
}

data class DisconnectSocialAccountRequest(
    val provider: SocialProvider,
)

data class UpdateInterestedCategoriesRequest(
    val categoryIds: List<Long>,
)

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String,
)

data class ResetPasswordRequest(
    val email: String,
    val phoneNumber: String,
    val verificationCode: String,
    val newPassword: String,
)

data class EnableTwoFactorRequest(
    val secret: String,
    val backupCodes: List<String>,
)

data class DisableTwoFactorRequest(
    val currentPassword: String,
)

data class UpdateUserProfileRequest(
    val name: String? = null,
    val nickname: String? = null,
    val phoneNumber: String? = null,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val userType: UserType? = null,
    val preferredPriceRangeMin: Int? = null,
    val preferredPriceRangeMax: Int? = null,
    val marketingAgreed: Boolean? = null,
) {
    fun toInput() = com.music.sale.application.user.dto.UpdateUserProfileInput(
        name = name,
        nickname = nickname,
        phoneNumber = phoneNumber,
        birthDate = birthDate,
        gender = gender,
        zipcode = zipcode,
        baseAddress = baseAddress,
        detailAddress = detailAddress,
        profileImageUrl = profileImageUrl,
        bio = bio,
        userType = userType,
        preferredPriceRangeMin = preferredPriceRangeMin,
        preferredPriceRangeMax = preferredPriceRangeMax,
        marketingAgreed = marketingAgreed,
    )
}

data class VerifyTwoFactorRequest(
    val code: String,
)

data class SetInterestedCategoriesRequest(
    val categoryIds: List<Long>,
)
