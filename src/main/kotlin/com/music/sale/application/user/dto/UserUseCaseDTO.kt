// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.dto

import com.music.sale.adapter.persistence.user.entity.Gender
import com.music.sale.adapter.persistence.user.entity.UserType
import com.music.sale.domain.user.enum.UserRole
import java.time.LocalDate
import java.time.LocalDateTime

// UseCase의 출력을 나타내는 DTO
data class UserOutput(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val role: UserRole,
    val phoneNumber: String?,
    val phoneVerified: Boolean,
    val birthDate: LocalDate?,
    val gender: Gender?,
    val zipcode: String?,
    val baseAddress: String?,
    val detailAddress: String?,
    val profileImageUrl: String?,
    val bio: String?,
    val userType: UserType?,
    val preferredPriceRangeMin: Int?,
    val preferredPriceRangeMax: Int?,
    val totalPurchases: Int,
    val totalSales: Int,
    val rating: Double,
    val reviewCount: Int,
    val isActive: Boolean,
    val isVerified: Boolean,
    val lastLoginAt: LocalDateTime?,
    val marketingAgreed: Boolean,
    val socialConnections: List<SocialConnectionOutput>? = null,
    val interestedCategories: List<Any>? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

// 이메일 회원가입 입력
data class CreateUserByEmailInput(
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
) {
    fun toDomain(): com.music.sale.domain.user.User {
        return com.music.sale.domain.user.User.create(
            email = email,
            password = password,
            name = name,
            nickname = nickname,
            role = role,
            phoneNumber = phoneNumber,
            birthDate = birthDate,
            gender = gender?.name,
            zipcode = zipcode,
            baseAddress = baseAddress,
            detailAddress = detailAddress,
            userType = userType,
            marketingAgreed = marketingAgreed
        )
    }
}

// 휴대폰번호 회원가입 입력
data class CreateUserByPhoneInput(
    val phoneNumber: String,
    val password: String,
    val name: String,
    val nickname: String,
    val role: UserRole,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val userType: UserType = UserType.BUYER,
    val marketingAgreed: Boolean = false,
) {
    fun toDomain(): com.music.sale.domain.user.User {
        return com.music.sale.domain.user.User.create(
            phoneNumber = phoneNumber,
            password = password,
            name = name,
            nickname = nickname,
            role = role,
            birthDate = birthDate,
            gender = gender?.name,
            zipcode = zipcode,
            baseAddress = baseAddress,
            detailAddress = detailAddress,
            userType = userType,
            marketingAgreed = marketingAgreed
        )
    }
}

// 소셜 로그인 회원가입 입력
data class CreateUserByProviderInput(
    val email: String,
    val provider: com.music.sale.adapter.persistence.user.entity.SocialProvider,
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
) {
    fun toDomain(): com.music.sale.domain.user.User {
        return com.music.sale.domain.user.User.create(
            email = email,
            provider = provider.name,
            providerId = providerId,
            name = name,
            nickname = nickname,
            role = role,
            phoneNumber = phoneNumber,
            birthDate = birthDate,
            gender = gender?.name,
            zipcode = zipcode,
            baseAddress = baseAddress,
            detailAddress = detailAddress,
            userType = userType,
            marketingAgreed = marketingAgreed
        )
    }
}

// 프로필 수정 입력
data class UpdateUserProfileInput(
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

// 소셜 계정 연동 입력
data class ConnectSocialAccountInput(
    val provider: com.music.sale.adapter.persistence.user.entity.SocialProvider,
    val providerId: String,
    val providerEmail: String? = null,
    val providerName: String? = null,
    val providerProfileImage: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val tokenExpiresAt: LocalDateTime? = null,
)

// 소셜 연동 출력
data class SocialConnectionOutput(
    val id: Long,
    val provider: com.music.sale.adapter.persistence.user.entity.SocialProvider,
    val providerId: String,
    val providerEmail: String?,
    val providerName: String?,
    val providerProfileImage: String?,
    val connectedAt: LocalDateTime,
    val lastUsedAt: LocalDateTime?,
    val isActive: Boolean,
)

// 휴대폰 인증 출력
data class PhoneVerificationOutput(
    val phoneNumber: String,
    val verificationCode: String,
    val verificationType: com.music.sale.adapter.persistence.user.entity.VerificationType,
    val expiresAt: LocalDateTime,
    val attemptCount: Int,
    val isUsed: Boolean,
)

// 휴대폰 인증 결과
data class PhoneVerificationResult(
    val success: Boolean,
    val message: String,
    val verifiedAt: LocalDateTime?,
)

// 소셜 연동 결과
data class SocialConnectionResult(
    val success: Boolean,
    val message: String,
    val provider: com.music.sale.adapter.persistence.user.entity.SocialProvider,
    val connectedAt: LocalDateTime?,
)

// 사용자 통계 출력
data class UserStatsOutput(
    val userId: Long,
    val totalPurchases: Int,
    val totalSales: Int,
    val rating: Double,
    val reviewCount: Int,
    val lastLoginAt: LocalDateTime?,
    val joinDate: LocalDateTime,
)

// 2단계 인증 설정 출력
data class TwoFactorSetupOutput(
    val enabled: Boolean,
    val secret: String?,
    val qrCodeUrl: String?,
    val backupCodes: List<String>?,
)

// 2단계 인증 확인 결과
data class TwoFactorVerificationResult(
    val success: Boolean,
    val message: String,
)

// 프로필 업데이트 결과
data class UserProfileUpdateResult(
    val success: Boolean,
    val message: String,
    val updatedFields: List<String>,
)
