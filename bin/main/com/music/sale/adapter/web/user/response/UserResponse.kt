// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.user.response

import com.music.sale.adapter.persistence.user.entity.Gender
import com.music.sale.adapter.persistence.user.entity.SocialProvider
import com.music.sale.adapter.persistence.user.entity.UserType
import com.music.sale.domain.user.enum.UserRole
import java.time.LocalDate
import java.time.LocalDateTime

// 기본 사용자 응답
data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val role: String,
    val phoneNumber: String?,
    val phoneVerified: Boolean,
    val birthDate: LocalDate?,
    val gender: String?,
    val zipcode: String?,
    val baseAddress: String?,
    val detailAddress: String?,
    val profileImageUrl: String?,
    val bio: String?,
    val userType: String,
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
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

// 상세 사용자 응답 (소셜 연동 정보 포함)
data class UserDetailResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val role: String,
    val phoneNumber: String?,
    val phoneVerified: Boolean,
    val birthDate: LocalDate?,
    val gender: String?,
    val zipcode: String?,
    val baseAddress: String?,
    val detailAddress: String?,
    val profileImageUrl: String?,
    val bio: String?,
    val userType: String,
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
    val socialConnections: List<SocialConnectionResponse>,
    val interestedCategories: List<CategoryResponse>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

// 소셜 연동 정보 응답
data class SocialConnectionResponse(
    val id: Long,
    val provider: String,
    val providerId: String,
    val providerEmail: String?,
    val providerName: String?,
    val providerProfileImage: String?,
    val connectedAt: LocalDateTime,
    val lastUsedAt: LocalDateTime?,
    val isActive: Boolean,
)

// 카테고리 응답
data class CategoryResponse(
    val id: Long,
    val name: String,
    val path: String,
)

// 휴대폰 인증 응답
data class PhoneVerificationResponse(
    val phoneNumber: String,
    val verificationType: String,
    val expiresAt: LocalDateTime,
    val attemptCount: Int,
    val isUsed: Boolean,
)

// 휴대폰 인증 확인 응답
data class PhoneVerificationResultResponse(
    val success: Boolean,
    val message: String,
    val phoneNumber: String,
    val verifiedAt: LocalDateTime?,
)

// 소셜 연동 결과 응답
data class SocialConnectionResultResponse(
    val success: Boolean,
    val message: String,
    val provider: String,
    val connectedAt: LocalDateTime?,
)

// 사용자 통계 응답
data class UserStatsResponse(
    val userId: Long,
    val totalPurchases: Int,
    val totalSales: Int,
    val rating: Double,
    val reviewCount: Int,
    val lastLoginAt: LocalDateTime?,
    val joinDate: LocalDateTime,
)

// 2단계 인증 설정 응답
data class TwoFactorSetupResponse(
    val enabled: Boolean,
    val secret: String?,
    val qrCodeUrl: String?,
    val backupCodes: List<String>?,
)

// 사용자 프로필 업데이트 응답
data class UserProfileUpdateResponse(
    val success: Boolean,
    val message: String,
    val updatedFields: List<String>,
)
