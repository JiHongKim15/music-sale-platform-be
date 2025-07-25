package com.music.sale.web.user.mapper

import com.music.sale.application.user.dto.CreateUserByEmailInput
import com.music.sale.application.user.dto.CreateUserByPhoneInput
import com.music.sale.application.user.dto.CreateUserByProviderInput
import com.music.sale.application.user.dto.UserOutput
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.domain.user.enum.UserType
import com.music.sale.web.user.request.*
import com.music.sale.web.user.response.*
import org.springframework.stereotype.Component

@Component
class UserWebMapper {
    // Request → Application Input 매핑
    fun toCreateUserByEmailInput(request: CreateUserByEmailRequest): CreateUserByEmailInput {
        return CreateUserByEmailInput(
            email = request.email,
            password = request.password,
            name = request.name,
            // 임시로 name을 nickname으로 사용
            nickname = request.name,
            role = com.music.sale.domain.user.enum.UserRole.USER,
            phoneNumber = request.phoneNumber,
            birthDate = request.birthDate?.let { java.time.LocalDate.parse(it) },
            gender = request.gender,
            userType = request.userType,
        )
    }

    fun toCreateUserByPhoneInput(request: CreateUserByPhoneRequest): CreateUserByPhoneInput {
        return CreateUserByPhoneInput(
            phoneNumber = request.phoneNumber,
            password = request.password,
            name = request.name,
            // 임시로 name을 nickname으로 사용
            nickname = request.name,
            role = com.music.sale.domain.user.enum.UserRole.USER,
            birthDate = request.birthDate,
            gender = request.gender,
            userType = request.userType,
        )
    }

    fun toCreateUserByProviderInput(request: CreateUserByProviderRequest): CreateUserByProviderInput {
        return CreateUserByProviderInput(
            email = request.email,
            provider = request.provider,
            providerId = request.socialId,
            name = request.name,
            // 임시로 name을 nickname으로 사용
            nickname = request.name,
            role = com.music.sale.domain.user.enum.UserRole.USER,
            phoneNumber = request.phoneNumber,
            birthDate = request.birthDate?.let { java.time.LocalDate.parse(it) },
            gender = request.gender,
            userType = request.userType,
        )
    }

    // Application Output → Response 매핑
    fun toUserResponse(output: UserOutput): UserResponse {
        return UserResponse(
            id = output.id,
            email = output.email,
            name = output.name,
            nickname = output.nickname,
            role = output.role.toString(),
            phoneNumber = output.phoneNumber,
            phoneVerified = output.phoneVerified,
            birthDate = output.birthDate,
            gender = output.gender?.name,
            zipcode = output.zipcode,
            baseAddress = output.baseAddress,
            detailAddress = output.detailAddress,
            profileImageUrl = output.profileImageUrl,
            bio = output.bio,
            userType =
                output.userType?.name
                    ?: com.music.sale.domain.user.enum.UserType.BUYER.name,
            preferredPriceRangeMin = output.preferredPriceRangeMin,
            preferredPriceRangeMax = output.preferredPriceRangeMax,
            totalPurchases = output.totalPurchases,
            totalSales = output.totalSales,
            rating = output.rating,
            reviewCount = output.reviewCount,
            isActive = output.isActive,
            isVerified = output.isVerified,
            lastLoginAt = output.lastLoginAt,
            marketingAgreed = output.marketingAgreed,
            createdAt = output.createdAt,
            updatedAt = output.updatedAt,
        )
    }

    fun toUserDetailResponse(output: UserOutput): UserDetailResponse {
        return UserDetailResponse(
            id = output.id,
            email = output.email,
            name = output.name,
            nickname = output.nickname,
            role = output.role.toString(),
            phoneNumber = output.phoneNumber,
            phoneVerified = output.phoneVerified,
            birthDate = output.birthDate,
            gender = output.gender?.name,
            zipcode = output.zipcode,
            baseAddress = output.baseAddress,
            detailAddress = output.detailAddress,
            profileImageUrl = output.profileImageUrl,
            bio = output.bio,
            userType =
                output.userType?.name
                    ?: com.music.sale.domain.user.enum.UserType.BUYER.name,
            preferredPriceRangeMin = output.preferredPriceRangeMin,
            preferredPriceRangeMax = output.preferredPriceRangeMax,
            totalPurchases = output.totalPurchases,
            totalSales = output.totalSales,
            rating = output.rating,
            reviewCount = output.reviewCount,
            isActive = output.isActive,
            isVerified = output.isVerified,
            lastLoginAt = output.lastLoginAt,
            marketingAgreed = output.marketingAgreed,
            socialConnections =
                output.socialConnections?.map { toSocialConnectionResponse(it) }
                    ?: emptyList(),
            interestedCategories =
                output.interestedCategories?.map { toCategoryResponse(it) }
                    ?: emptyList(),
            createdAt = output.createdAt,
            updatedAt = output.updatedAt,
        )
    }

    fun toSocialConnectionResponse(
        socialConnection: com.music.sale.application.user.dto.SocialConnectionOutput,
    ): SocialConnectionResponse {
        return SocialConnectionResponse(
            id = socialConnection.id,
            provider = socialConnection.provider.name,
            providerId = socialConnection.providerId,
            providerEmail = socialConnection.providerEmail,
            providerName = socialConnection.providerName,
            providerProfileImage = socialConnection.providerProfileImage,
            connectedAt = socialConnection.connectedAt,
            lastUsedAt = socialConnection.lastUsedAt,
            isActive = socialConnection.isActive,
        )
    }

    fun toCategoryResponse(category: Any): CategoryResponse {
        // TODO: 실제 CategoryOutput으로 변경
        return CategoryResponse(
            id = 0L,
            name = "카테고리",
            path = "/카테고리",
        )
    }

    fun toPhoneVerificationResponse(verification: com.music.sale.application.user.dto.PhoneVerificationOutput): PhoneVerificationResponse {
        return PhoneVerificationResponse(
            phoneNumber = verification.phoneNumber,
            verificationType = verification.verificationType.name,
            expiresAt = verification.expiresAt,
            attemptCount = verification.attemptCount,
            isUsed = verification.isUsed,
        )
    }

    fun toPhoneVerificationResultResponse(
        success: Boolean,
        message: String,
        phoneNumber: String,
        verifiedAt: java.time.LocalDateTime?,
    ): PhoneVerificationResultResponse {
        return PhoneVerificationResultResponse(
            success = success,
            message = message,
            phoneNumber = phoneNumber,
            verifiedAt = verifiedAt,
        )
    }

    fun toSocialConnectionResultResponse(
        success: Boolean,
        message: String,
        provider: SocialProvider,
        connectedAt: java.time.LocalDateTime?,
    ): SocialConnectionResultResponse {
        return SocialConnectionResultResponse(
            success = success,
            message = message,
            provider = provider.name,
            connectedAt = connectedAt,
        )
    }

    fun toUserStatsResponse(stats: com.music.sale.application.user.dto.UserStatsOutput): UserStatsResponse {
        return UserStatsResponse(
            userId = stats.userId,
            totalPurchases = stats.totalPurchases,
            totalSales = stats.totalSales,
            rating = stats.rating,
            reviewCount = stats.reviewCount,
            lastLoginAt = stats.lastLoginAt,
            joinDate = stats.joinDate,
        )
    }

    fun toTwoFactorSetupResponse(setup: com.music.sale.application.user.dto.TwoFactorSetupOutput): TwoFactorSetupResponse {
        return TwoFactorSetupResponse(
            enabled = setup.enabled,
            secret = setup.secret,
            qrCodeUrl = setup.qrCodeUrl,
            backupCodes = setup.backupCodes,
        )
    }

    fun toUserProfileUpdateResponse(
        success: Boolean,
        message: String,
        updatedFields: List<String>,
    ): UserProfileUpdateResponse {
        return UserProfileUpdateResponse(
            success = success,
            message = message,
            updatedFields = updatedFields,
        )
    }
}
