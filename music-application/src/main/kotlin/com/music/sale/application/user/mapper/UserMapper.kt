// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.mapper

import com.music.sale.application.user.dto.UserOutput
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toOutput(user: User): UserOutput {
        return UserOutput(
            id = user.id ?: 0L,
            email = user.email?.value ?: "",
            name = user.name?.value ?: "",
            nickname = user.nickname?.value ?: "",
            role = user.role ?: UserRole.USER,
            phoneNumber = user.phoneNumber,
            phoneVerified = user.phoneVerified,
            birthDate = user.birthDate,
            gender =
                when (user.gender?.uppercase()) {
                    "MALE" -> com.music.sale.domain.user.enum.Gender.MALE
                    "FEMALE" -> com.music.sale.domain.user.enum.Gender.FEMALE
                    "OTHER" -> com.music.sale.domain.user.enum.Gender.OTHER
                    else -> null
                },
            zipcode = user.zipcode,
            baseAddress = user.baseAddress,
            detailAddress = user.detailAddress,
            profileImageUrl = user.profileImageUrl,
            bio = user.bio,
            userType = user.userType,
            preferredPriceRangeMin = user.preferredPriceRangeMin,
            preferredPriceRangeMax = user.preferredPriceRangeMax,
            totalPurchases = user.totalPurchases,
            totalSales = user.totalSales,
            rating = user.rating,
            reviewCount = user.reviewCount,
            isActive = true,
            isVerified = user.phoneVerified,
            lastLoginAt = user.lastLoginAt,
            marketingAgreed = user.marketingAgreed,
            socialConnections = null,
            interestedCategories = null,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt,
        )
    }
}
