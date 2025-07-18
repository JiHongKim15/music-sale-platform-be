// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.user.mapper

import com.music.sale.application.user.dto.*
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.Gender
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.persistence.user.entity.*
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapper {
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = if (entity.email.isNotBlank()) User.Email(entity.email) else null,
            provider = entity.provider?.name,
            providerId = entity.providerId,
            name = if (entity.name.isNotBlank()) User.Name(entity.name) else null,
            nickname =
                if (entity.nickname.isNotBlank()) User.Nickname(entity.nickname) else null,
            role = entity.role,
            phoneNumber = entity.phoneNumber,
            birthDate = entity.birthDate,
            gender = entity.gender?.name,
            zipcode = entity.zipcode,
            baseAddress = entity.baseAddress,
            detailAddress = entity.detailAddress,
            profileImageUrl = entity.profileImageUrl,
            bio = entity.bio,
            userType = entity.userType,
            preferredPriceRangeMin = entity.preferredPriceRangeMin,
            preferredPriceRangeMax = entity.preferredPriceRangeMax,
            marketingAgreed = entity.marketingAgreed,
            phoneVerified = entity.phoneVerified,
            totalPurchases = entity.totalPurchases,
            totalSales = entity.totalSales,
            rating = entity.rating,
            reviewCount = entity.reviewCount,
            lastLoginAt = entity.lastLoginAt,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }

    fun toEntity(domain: User): UserEntity {
        return UserEntity(
            id = domain.id,
            email = domain.email?.value ?: "",
            name = domain.name?.value ?: "",
            nickname = domain.nickname?.value ?: "",
            role = domain.role ?: com.music.sale.domain.user.enum.UserRole.USER,
            provider = domain.provider?.let { SocialProvider.valueOf(it.uppercase()) },
            providerId = domain.providerId,
            phoneNumber = domain.phoneNumber,
            phoneVerified = domain.phoneVerified,
            birthDate = domain.birthDate,
            gender = domain.gender?.let { Gender.valueOf(it.uppercase()) },
            zipcode = domain.zipcode,
            baseAddress = domain.baseAddress,
            detailAddress = domain.detailAddress,
            profileImageUrl = domain.profileImageUrl,
            bio = domain.bio,
            userType = domain.userType,
            preferredPriceRangeMin = domain.preferredPriceRangeMin,
            preferredPriceRangeMax = domain.preferredPriceRangeMax,
            marketingAgreed = domain.marketingAgreed,
            totalPurchases = domain.totalPurchases,
            totalSales = domain.totalSales,
            rating = domain.rating,
            reviewCount = domain.reviewCount,
            isActive = true,
            isVerified = domain.phoneVerified,
            lastLoginAt = domain.lastLoginAt,
        )
    }

    fun toUserOutput(
        entity: UserEntity,
        socialConnections: List<SocialConnectionOutput>,
    ): UserOutput {
        return UserOutput(
            id = entity.id!!,
            email = entity.email,
            name = entity.name,
            nickname = entity.nickname,
            role = entity.role,
            phoneNumber = entity.phoneNumber,
            phoneVerified = entity.phoneVerified,
            birthDate = entity.birthDate,
            gender = entity.gender,
            zipcode = entity.zipcode,
            baseAddress = entity.baseAddress,
            detailAddress = entity.detailAddress,
            profileImageUrl = entity.profileImageUrl,
            bio = entity.bio,
            userType = entity.userType,
            preferredPriceRangeMin = entity.preferredPriceRangeMin,
            preferredPriceRangeMax = entity.preferredPriceRangeMax,
            totalPurchases = entity.totalPurchases,
            totalSales = entity.totalSales,
            rating = entity.rating,
            reviewCount = entity.reviewCount,
            isActive = entity.isActive,
            isVerified = entity.isVerified,
            lastLoginAt = entity.lastLoginAt,
            marketingAgreed = entity.marketingAgreed,
            socialConnections = socialConnections,
            interestedCategories = null,
            // TODO: 관심 카테고리 구현
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }

    fun toSocialConnectionOutput(entity: UserSocialConnectionEntity): SocialConnectionOutput {
        return SocialConnectionOutput(
            id = entity.id!!,
            provider = entity.provider,
            providerId = entity.providerId,
            providerEmail = entity.providerEmail,
            providerName = entity.providerName,
            providerProfileImage = entity.providerProfileImage,
            connectedAt = entity.connectedAt,
            lastUsedAt = entity.lastUsedAt,
            isActive = entity.isActive,
        )
    }

    fun toPhoneVerificationOutput(entity: PhoneVerificationEntity): PhoneVerificationOutput {
        return PhoneVerificationOutput(
            phoneNumber = entity.phoneNumber,
            verificationCode = entity.verificationCode,
            verificationType = entity.verificationType,
            expiresAt = entity.expiresAt,
            attemptCount = entity.attemptCount,
            isUsed = entity.isUsed,
        )
    }
}
