// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.service

import com.music.sale.adapter.persistence.user.entity.Gender
import com.music.sale.adapter.persistence.user.entity.SocialProvider
import com.music.sale.adapter.persistence.user.entity.UserType
import com.music.sale.adapter.persistence.user.entity.VerificationType
import com.music.sale.application.user.dto.*
import com.music.sale.application.user.port.inport.UserUseCase
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional
class UserService(
    private val userPort: UserPort,
    private val passwordEncoder: PasswordEncoder,
) : UserUseCase {

    override fun createUserByEmail(input: CreateUserByEmailInput): UserOutput {
        // 이메일 중복 확인
        if (userPort.existsByEmail(input.email)) {
            throw IllegalArgumentException("이미 등록된 이메일입니다: ${input.email}")
        }

        // 닉네임 중복 확인
        if (userPort.existsByNickname(input.nickname)) {
            throw IllegalArgumentException("이미 사용 중인 닉네임입니다: ${input.nickname}")
        }

        // 도메인 객체 생성
        val user = input.toDomain()

        // 사용자 저장
        val savedUser = userPort.saveEmail(user, input.password)

        return userPort.getUserById(savedUser.id!!)
    }

    override fun createUserByPhone(input: CreateUserByPhoneInput): UserOutput {
        // 휴대폰번호 중복 확인
        if (userPort.existsByPhoneNumber(input.phoneNumber)) {
            throw IllegalArgumentException("이미 등록된 휴대폰번호입니다: ${input.phoneNumber}")
        }

        // 닉네임 중복 확인
        if (userPort.existsByNickname(input.nickname)) {
            throw IllegalArgumentException("이미 사용 중인 닉네임입니다: ${input.nickname}")
        }

        // 도메인 객체 생성
        val user = input.toDomain()

        // 사용자 저장
        val savedUser = userPort.savePhone(user, input.password)

        return userPort.getUserById(savedUser.id!!)
    }

    override fun createUserByProvider(input: CreateUserByProviderInput): UserOutput {
        // 이메일 중복 확인
        if (userPort.existsByEmail(input.email)) {
            throw IllegalArgumentException("이미 존재하는 이메일입니다: ${input.email}")
        }

        // 닉네임 중복 확인
        if (userPort.existsByNickname(input.nickname)) {
            throw IllegalArgumentException("이미 존재하는 닉네임입니다: ${input.nickname}")
        }

        // User 도메인 생성
        val user = User.create(
            email = input.email,
            provider = input.provider.name,
            providerId = input.providerId,
            name = input.name,
            nickname = input.nickname,
            role = input.role,
            phoneNumber = input.phoneNumber,
            birthDate = input.birthDate,
            gender = input.gender?.name,
            zipcode = input.zipcode,
            baseAddress = input.baseAddress,
            detailAddress = input.detailAddress,
            userType = input.userType,
            marketingAgreed = input.marketingAgreed
        )

        // 저장
        val savedUser = userPort.save(user)

        // 소셜 연동 정보 생성
        userPort.createSocialConnection(
            userId = savedUser.id!!,
            provider = input.provider,
            providerId = input.providerId,
            providerEmail = input.providerEmail,
            providerName = input.providerName,
            providerProfileImage = input.providerProfileImage
        )

        return userPort.getUserById(savedUser.id!!)
    }

    override fun getUserById(userId: Long): UserOutput {
        return userPort.getUserById(userId)
    }

    override fun updateUserProfile(userId: Long, input: UpdateUserProfileInput): UserProfileUpdateResult {
        val user = userPort.getUserById(userId)
        val updatedFields = mutableListOf<String>()

        // 업데이트할 필드들 처리
        input.name?.let {
            userPort.updateUserName(userId, it)
            updatedFields.add("name")
        }

        input.nickname?.let {
            if (userPort.existsByNickname(it) && user.nickname != it) {
                throw IllegalArgumentException("이미 존재하는 닉네임입니다: $it")
            }
            userPort.updateUserNickname(userId, it)
            updatedFields.add("nickname")
        }

        input.phoneNumber?.let {
            userPort.updateUserPhoneNumber(userId, it)
            updatedFields.add("phoneNumber")
        }

        input.birthDate?.let {
            userPort.updateUserBirthDate(userId, it)
            updatedFields.add("birthDate")
        }

        input.gender?.let {
            userPort.updateUserGender(userId, it.name)
            updatedFields.add("gender")
        }

        input.zipcode?.let {
            userPort.updateUserZipcode(userId, it)
            updatedFields.add("zipcode")
        }

        input.baseAddress?.let {
            userPort.updateUserBaseAddress(userId, it)
            updatedFields.add("baseAddress")
        }

        input.detailAddress?.let {
            userPort.updateUserDetailAddress(userId, it)
            updatedFields.add("detailAddress")
        }

        input.profileImageUrl?.let {
            userPort.updateUserProfileImageUrl(userId, it)
            updatedFields.add("profileImageUrl")
        }

        input.bio?.let {
            userPort.updateUserBio(userId, it)
            updatedFields.add("bio")
        }

        input.userType?.let {
            userPort.updateUserType(userId, it.name)
            updatedFields.add("userType")
        }

        input.preferredPriceRangeMin?.let {
            userPort.updateUserPreferredPriceRangeMin(userId, it)
            updatedFields.add("preferredPriceRangeMin")
        }

        input.preferredPriceRangeMax?.let {
            userPort.updateUserPreferredPriceRangeMax(userId, it)
            updatedFields.add("preferredPriceRangeMax")
        }

        input.marketingAgreed?.let {
            userPort.updateUserMarketingAgreed(userId, it)
            updatedFields.add("marketingAgreed")
        }

        return UserProfileUpdateResult(
            success = true,
            message = "프로필이 성공적으로 업데이트되었습니다.",
            updatedFields = updatedFields
        )
    }

    override fun deleteUser(userId: Long) {
        userPort.deleteUser(userId)
    }

    override fun sendPhoneVerificationCode(phoneNumber: String, verificationType: VerificationType): PhoneVerificationOutput {
        // 인증 코드 생성 (6자리 숫자)
        val verificationCode = String.format("%06d", Random().nextInt(1000000))
        val expiresAt = LocalDateTime.now().plusMinutes(5)

        // 기존 인증 정보 삭제
        userPort.deletePhoneVerification(phoneNumber, verificationType)

        // 새로운 인증 정보 저장
        userPort.createPhoneVerification(
            phoneNumber = phoneNumber,
            verificationCode = verificationCode,
            verificationType = verificationType,
            expiresAt = expiresAt
        )

        // TODO: 실제 SMS 발송 로직 구현
        println("SMS 발송: $phoneNumber -> 인증코드: $verificationCode")

        return PhoneVerificationOutput(
            phoneNumber = phoneNumber,
            verificationCode = verificationCode,
            verificationType = verificationType,
            expiresAt = expiresAt,
            attemptCount = 0,
            isUsed = false
        )
    }

    override fun verifyPhoneCode(phoneNumber: String, verificationCode: String, verificationType: VerificationType): PhoneVerificationResult {
        val verification = userPort.getPhoneVerification(phoneNumber, verificationType)
            ?: return PhoneVerificationResult(
                success = false,
                message = "인증 정보를 찾을 수 없습니다.",
                verifiedAt = null
            )

        if (verification.isUsed) {
            return PhoneVerificationResult(
                success = false,
                message = "이미 사용된 인증 코드입니다.",
                verifiedAt = null
            )
        }

        if (verification.expiresAt.isBefore(LocalDateTime.now())) {
            return PhoneVerificationResult(
                success = false,
                message = "만료된 인증 코드입니다.",
                verifiedAt = null
            )
        }

        if (verification.verificationCode != verificationCode) {
            return PhoneVerificationResult(
                success = false,
                message = "잘못된 인증 코드입니다.",
                verifiedAt = null
            )
        }

        // 인증 성공 처리
        userPort.markPhoneVerificationAsUsed(phoneNumber, verificationType)
        userPort.verifyUserPhone(userId = userPort.getUserIdByPhone(phoneNumber), phoneNumber = phoneNumber)

        return PhoneVerificationResult(
            success = true,
            message = "휴대폰 인증이 완료되었습니다.",
            verifiedAt = LocalDateTime.now()
        )
    }

    override fun connectSocialAccount(userId: Long, input: ConnectSocialAccountInput): SocialConnectionResult {
        // 이미 연동된 소셜 계정인지 확인
        if (userPort.existsSocialConnection(userId, input.provider)) {
            return SocialConnectionResult(
                success = false,
                message = "이미 연동된 소셜 계정입니다.",
                provider = input.provider,
                connectedAt = null
            )
        }

        // 소셜 연동 정보 생성
        userPort.createSocialConnection(
            userId = userId,
            provider = input.provider,
            providerId = input.providerId,
            providerEmail = input.providerEmail,
            providerName = input.providerName,
            providerProfileImage = input.providerProfileImage,
            accessToken = input.accessToken,
            refreshToken = input.refreshToken,
            tokenExpiresAt = input.tokenExpiresAt
        )

        return SocialConnectionResult(
            success = true,
            message = "소셜 계정이 성공적으로 연동되었습니다.",
            provider = input.provider,
            connectedAt = LocalDateTime.now()
        )
    }

    override fun disconnectSocialAccount(userId: Long, provider: String): SocialConnectionResult {
        val socialProvider = SocialProvider.valueOf(provider.uppercase())
        
        if (!userPort.existsSocialConnection(userId, socialProvider)) {
            return SocialConnectionResult(
                success = false,
                message = "연동된 소셜 계정을 찾을 수 없습니다.",
                provider = socialProvider,
                connectedAt = null
            )
        }

        userPort.deleteSocialConnection(userId, socialProvider)

        return SocialConnectionResult(
            success = true,
            message = "소셜 계정 연동이 해제되었습니다.",
            provider = socialProvider,
            connectedAt = null
        )
    }

    override fun getUserStats(userId: Long): UserStatsOutput {
        val user = userPort.getUserById(userId)
        
        return UserStatsOutput(
            userId = userId,
            totalPurchases = user.totalPurchases,
            totalSales = user.totalSales,
            rating = user.rating,
            reviewCount = user.reviewCount,
            lastLoginAt = user.lastLoginAt,
            joinDate = user.createdAt
        )
    }

    override fun setupTwoFactorAuth(userId: Long): TwoFactorSetupOutput {
        // TODO: 2단계 인증 구현
        return TwoFactorSetupOutput(
            enabled = false,
            secret = null,
            qrCodeUrl = null,
            backupCodes = null
        )
    }

    override fun verifyTwoFactorAuth(userId: Long, code: String): TwoFactorVerificationResult {
        // TODO: 2단계 인증 구현
        return TwoFactorVerificationResult(
            success = false,
            message = "2단계 인증이 아직 구현되지 않았습니다."
        )
    }

    override fun setInterestedCategories(userId: Long, categoryIds: List<Long>) {
        userPort.setInterestedCategories(userId, categoryIds)
    }

    override fun getInterestedCategories(userId: Long): List<Any> {
        return userPort.getInterestedCategories(userId)
    }
}
