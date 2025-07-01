// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.port.outport

import com.music.sale.adapter.persistence.user.entity.SocialProvider
import com.music.sale.adapter.persistence.user.entity.VerificationType
import com.music.sale.application.user.dto.*
import com.music.sale.domain.user.User
import java.time.LocalDate
import java.time.LocalDateTime

interface UserPort {
    fun save(user: User): User

    fun findById(id: Long): User?

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun getUserById(userId: Long): UserOutput

    fun getSocialConnections(userId: Long): List<SocialConnectionOutput>

    fun createAuthUser(userId: Long, password: String)

    fun createSocialConnection(
        userId: Long,
        provider: SocialProvider,
        providerId: String,
        providerEmail: String? = null,
        providerName: String? = null,
        providerProfileImage: String? = null,
        accessToken: String? = null,
        refreshToken: String? = null,
        tokenExpiresAt: LocalDateTime? = null
    )

    fun existsSocialConnection(userId: Long, provider: SocialProvider): Boolean

    fun deleteSocialConnection(userId: Long, provider: SocialProvider)

    fun createPhoneVerification(
        phoneNumber: String,
        verificationCode: String,
        verificationType: VerificationType,
        expiresAt: LocalDateTime
    )

    fun getPhoneVerification(phoneNumber: String, verificationType: VerificationType): PhoneVerificationOutput?

    fun deletePhoneVerification(phoneNumber: String, verificationType: VerificationType)

    fun markPhoneVerificationAsUsed(phoneNumber: String, verificationType: VerificationType)

    fun getUserIdByPhone(phoneNumber: String): Long

    fun verifyUserPhone(userId: Long, phoneNumber: String)

    // User Profile Update Methods
    fun updateUserName(userId: Long, name: String)
    fun updateUserNickname(userId: Long, nickname: String)
    fun updateUserPhoneNumber(userId: Long, phoneNumber: String)
    fun updateUserBirthDate(userId: Long, birthDate: LocalDate)
    fun updateUserGender(userId: Long, gender: String)
    fun updateUserZipcode(userId: Long, zipcode: String)
    fun updateUserBaseAddress(userId: Long, baseAddress: String)
    fun updateUserDetailAddress(userId: Long, detailAddress: String)
    fun updateUserProfileImageUrl(userId: Long, profileImageUrl: String)
    fun updateUserBio(userId: Long, bio: String)
    fun updateUserType(userId: Long, userType: String)
    fun updateUserPreferredPriceRangeMin(userId: Long, minPrice: Int)
    fun updateUserPreferredPriceRangeMax(userId: Long, maxPrice: Int)
    fun updateUserMarketingAgreed(userId: Long, marketingAgreed: Boolean)

    fun deleteUser(userId: Long)

    fun setInterestedCategories(userId: Long, categoryIds: List<Long>)

    fun getInterestedCategories(userId: Long): List<Any>

    // Legacy methods for backward compatibility
    fun saveEmail(user: User, password: String): User

    fun savePhone(user: User, password: String): User

    fun saveProvider(user: User, provider: String, providerId: String): User
}
