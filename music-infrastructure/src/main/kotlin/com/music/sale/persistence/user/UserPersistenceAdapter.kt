// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.user

import com.music.sale.application.user.dto.PhoneVerificationOutput
import com.music.sale.application.user.dto.SocialConnectionOutput
import com.music.sale.application.user.dto.UserOutput
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.Gender
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.domain.user.enum.UserType
import com.music.sale.domain.user.enum.VerificationType
import com.music.sale.persistence.user.entity.AuthUserEntity
import com.music.sale.persistence.user.entity.PhoneVerificationEntity
import com.music.sale.persistence.user.entity.UserSocialConnectionEntity
import com.music.sale.persistence.user.mapper.UserPersistenceMapper
import com.music.sale.persistence.user.repository.AuthUserRepository
import com.music.sale.persistence.user.repository.PhoneVerificationRepository
import com.music.sale.persistence.user.repository.UserRepository
import com.music.sale.persistence.user.repository.UserSocialConnectionRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
open class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val authUserRepository: AuthUserRepository,
    private val userSocialConnectionRepository: UserSocialConnectionRepository,
    private val phoneVerificationRepository: PhoneVerificationRepository,
    private val mapper: UserPersistenceMapper,
) : UserPort, UserDetailsService {
    override fun save(user: User): User {
        val userEntity = mapper.toEntity(user)
        val savedUser = userRepository.save(userEntity)
        return mapper.toDomain(savedUser)
    }

    override fun findById(id: Long): User? {
        return userRepository.findById(id).map(mapper::toDomain).orElse(null)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.let(mapper::toDomain)
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun existsByNickname(nickname: String): Boolean {
        return userRepository.existsByNickname(nickname)
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return userRepository.existsByPhoneNumber(phoneNumber)
    }

    override fun getUserById(userId: Long): UserOutput {
        val userEntity =
            userRepository.findById(userId).orElseThrow {
                IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")
            }

        val socialConnections = getSocialConnections(userId)
        return mapper.toUserOutput(userEntity, socialConnections)
    }

    override fun getSocialConnections(userId: Long): List<SocialConnectionOutput> {
        return userSocialConnectionRepository.findByUserId(userId).map {
            mapper.toSocialConnectionOutput(it)
        }
    }

    override fun createAuthUser(
        userId: Long,
        password: String,
    ) {
        val userEntity =
            userRepository.findById(userId).orElseThrow {
                IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")
            }

        val authUserEntity =
            AuthUserEntity(
                user = userEntity,
                passwordHash = password,
            )
        authUserRepository.save(authUserEntity)
    }

    override fun createSocialConnection(
        userId: Long,
        provider: SocialProvider,
        providerId: String,
        providerEmail: String?,
        providerName: String?,
        providerProfileImage: String?,
        accessToken: String?,
        refreshToken: String?,
        tokenExpiresAt: LocalDateTime?,
    ) {
        val userEntity =
            userRepository.findById(userId).orElseThrow {
                IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")
            }

        val socialConnectionEntity =
            UserSocialConnectionEntity(
                user = userEntity,
                provider = provider,
                providerId = providerId,
                providerEmail = providerEmail,
                providerName = providerName,
                providerProfileImage = providerProfileImage,
                accessToken = accessToken,
                refreshToken = refreshToken,
                tokenExpiresAt = tokenExpiresAt,
                connectedAt = LocalDateTime.now(),
                lastUsedAt = LocalDateTime.now(),
                isActive = true,
            )
        userSocialConnectionRepository.save(socialConnectionEntity)
    }

    override fun existsSocialConnection(
        userId: Long,
        provider: SocialProvider,
    ): Boolean {
        return userSocialConnectionRepository.existsByUserIdAndProvider(userId, provider)
    }

    override fun deleteSocialConnection(
        userId: Long,
        provider: SocialProvider,
    ) {
        userSocialConnectionRepository.deleteByUserIdAndProvider(userId, provider)
    }

    override fun createPhoneVerification(
        phoneNumber: String,
        verificationCode: String,
        verificationType: VerificationType,
        expiresAt: LocalDateTime,
    ) {
        val phoneVerificationEntity =
            PhoneVerificationEntity(
                phoneNumber = phoneNumber,
                verificationCode = verificationCode,
                verificationType = verificationType,
                expiresAt = expiresAt,
                attemptCount = 0,
                isUsed = false,
            )
        phoneVerificationRepository.save(phoneVerificationEntity)
    }

    override fun getPhoneVerification(
        phoneNumber: String,
        verificationType: VerificationType,
    ): PhoneVerificationOutput? {
        return phoneVerificationRepository.findByPhoneNumberAndVerificationType(
            phoneNumber,
            verificationType,
        )
            ?.let { mapper.toPhoneVerificationOutput(it) }
    }

    override fun deletePhoneVerification(
        phoneNumber: String,
        verificationType: VerificationType,
    ) {
        phoneVerificationRepository.deleteByPhoneNumberAndVerificationType(
            phoneNumber,
            verificationType,
        )
    }

    override fun markPhoneVerificationAsUsed(
        phoneNumber: String,
        verificationType: VerificationType,
    ) {
        phoneVerificationRepository.findByPhoneNumberAndVerificationType(
            phoneNumber,
            verificationType,
        )
            ?.let { entity ->
                entity.isUsed = true
                phoneVerificationRepository.save(entity)
            }
    }

    override fun getUserIdByPhone(phoneNumber: String): Long {
        val userEntity =
            userRepository.findByPhoneNumber(phoneNumber)
                ?: throw IllegalArgumentException(
                    "해당 전화번호로 등록된 사용자를 찾을 수 없습니다: $phoneNumber",
                )
        return userEntity.id!!
    }

    override fun verifyUserPhone(
        userId: Long,
        phoneNumber: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.phoneVerified = true
            userRepository.save(entity)
        }
    }

    // User Profile Update Methods
    override fun updateUserName(
        userId: Long,
        name: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.name = name
            userRepository.save(entity)
        }
    }

    override fun updateUserNickname(
        userId: Long,
        nickname: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.nickname = nickname
            userRepository.save(entity)
        }
    }

    override fun updateUserPhoneNumber(
        userId: Long,
        phoneNumber: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.phoneNumber = phoneNumber
            userRepository.save(entity)
        }
    }

    override fun updateUserBirthDate(
        userId: Long,
        birthDate: LocalDate,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.birthDate = birthDate
            userRepository.save(entity)
        }
    }

    override fun updateUserGender(
        userId: Long,
        gender: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.gender = Gender.valueOf(gender.uppercase())
            userRepository.save(entity)
        }
    }

    override fun updateUserZipcode(
        userId: Long,
        zipcode: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.zipcode = zipcode
            userRepository.save(entity)
        }
    }

    override fun updateUserBaseAddress(
        userId: Long,
        baseAddress: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.baseAddress = baseAddress
            userRepository.save(entity)
        }
    }

    override fun updateUserDetailAddress(
        userId: Long,
        detailAddress: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.detailAddress = detailAddress
            userRepository.save(entity)
        }
    }

    override fun updateUserProfileImageUrl(
        userId: Long,
        profileImageUrl: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.profileImageUrl = profileImageUrl
            userRepository.save(entity)
        }
    }

    override fun updateUserBio(
        userId: Long,
        bio: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.bio = bio
            userRepository.save(entity)
        }
    }

    override fun updateUserType(
        userId: Long,
        userType: String,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.userType = UserType.valueOf(userType.uppercase())
            userRepository.save(entity)
        }
    }

    override fun updateUserPreferredPriceRangeMin(
        userId: Long,
        minPrice: Int,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.preferredPriceRangeMin = minPrice
            userRepository.save(entity)
        }
    }

    override fun updateUserPreferredPriceRangeMax(
        userId: Long,
        maxPrice: Int,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.preferredPriceRangeMax = maxPrice
            userRepository.save(entity)
        }
    }

    override fun updateUserMarketingAgreed(
        userId: Long,
        marketingAgreed: Boolean,
    ) {
        userRepository.findById(userId).ifPresent { entity ->
            entity.marketingAgreed = marketingAgreed
            userRepository.save(entity)
        }
    }

    override fun deleteUser(userId: Long) {
        userRepository.deleteById(userId)
    }

    override fun setInterestedCategories(
        userId: Long,
        categoryIds: List<Long>,
    ) {
        // TODO: 관심 카테고리 설정 구현
    }

    override fun getInterestedCategories(userId: Long): List<Any> {
        // TODO: 관심 카테고리 조회 구현
        return emptyList()
    }

    override fun findByProviderAndProviderId(
        provider: SocialProvider,
        providerId: String,
    ): User? {
        return userRepository
            .findByProviderAndProviderId(provider, providerId)
            ?.let(mapper::toDomain)
    }

    // Legacy methods for backward compatibility
    override fun saveEmail(
        user: User,
        password: String,
    ): User {
        val userEntity = mapper.toEntity(user)
        val savedUserEntity = userRepository.save(userEntity)

        val authUserEntity =
            AuthUserEntity(
                user = savedUserEntity,
                passwordHash = password,
            )
        authUserRepository.save(authUserEntity)
        return mapper.toDomain(savedUserEntity)
    }

    override fun savePhone(
        user: User,
        password: String,
    ): User {
        val userEntity = mapper.toEntity(user)
        val savedUserEntity = userRepository.save(userEntity)

        val authUserEntity =
            AuthUserEntity(
                user = savedUserEntity,
                passwordHash = password,
            )
        authUserRepository.save(authUserEntity)
        return mapper.toDomain(savedUserEntity)
    }

    override fun saveProvider(
        user: User,
        provider: String,
        providerId: String,
    ): User {
        val userEntity = mapper.toEntity(user)
        val savedUserEntity = userRepository.save(userEntity)
        return mapper.toDomain(savedUserEntity)
    }

    // UserDetailsService 구현
    override fun loadUserByUsername(username: String): UserDetails {
        val user = findByEmail(username) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다: $username")
        
        val authorities = listOf(SimpleGrantedAuthority(user.role?.name ?: "USER"))
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.id?.toString() ?: "")
            .password("") // JWT 인증을 사용하므로 비밀번호는 빈 문자열
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}
