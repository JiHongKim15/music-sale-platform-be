// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.port.inport

import com.music.sale.adapter.persistence.user.entity.SocialProvider
import com.music.sale.adapter.persistence.user.entity.VerificationType
import com.music.sale.application.user.dto.*

interface UserUseCase {
    
    // 기본 사용자 관리
    fun createUserByEmail(input: CreateUserByEmailInput): UserOutput
    fun createUserByPhone(input: CreateUserByPhoneInput): UserOutput
    fun createUserByProvider(input: CreateUserByProviderInput): UserOutput
    fun getUserById(userId: Long): UserOutput
    fun updateUserProfile(userId: Long, input: UpdateUserProfileInput): UserProfileUpdateResult
    fun deleteUser(userId: Long)
    
    // 휴대폰 인증
    fun sendPhoneVerificationCode(phoneNumber: String, verificationType: VerificationType): PhoneVerificationOutput
    fun verifyPhoneCode(phoneNumber: String, verificationCode: String, verificationType: VerificationType): PhoneVerificationResult
    
    // 소셜 계정 연동
    fun connectSocialAccount(userId: Long, input: ConnectSocialAccountInput): SocialConnectionResult
    fun disconnectSocialAccount(userId: Long, provider: String): SocialConnectionResult
    
    // 사용자 통계
    fun getUserStats(userId: Long): UserStatsOutput
    
    // 2단계 인증
    fun setupTwoFactorAuth(userId: Long): TwoFactorSetupOutput
    fun verifyTwoFactorAuth(userId: Long, code: String): TwoFactorVerificationResult
    
    // 관심 카테고리
    fun setInterestedCategories(userId: Long, categoryIds: List<Long>)
    fun getInterestedCategories(userId: Long): List<Any>
}
