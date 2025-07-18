// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user

import com.music.sale.domain.user.enum.UserRole
import com.music.sale.domain.user.enum.UserType
import java.time.LocalDate
import java.time.LocalDateTime

/** 사용자 도메인 모델 */
data class User(
    val id: Long? = null,
    val email: Email? = null,
    val provider: String? = null,
    val providerId: String? = null,
    val password: Password? = null,
    var name: Name? = null,
    var nickname: Nickname? = null,
    var role: UserRole? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null,
    var gender: String? = null,
    var zipcode: String? = null,
    var baseAddress: String? = null,
    var detailAddress: String? = null,
    var profileImageUrl: String? = null,
    var bio: String? = null,
    var userType: UserType? = null,
    var preferredPriceRangeMin: Int? = null,
    var preferredPriceRangeMax: Int? = null,
    var marketingAgreed: Boolean = false,
    var phoneVerified: Boolean = false,
    var totalPurchases: Int = 0,
    var totalSales: Int = 0,
    var rating: Double = 0.0,
    var reviewCount: Int = 0,
    var lastLoginAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(
            email: String,
            password: String,
            name: String,
            nickname: String,
            role: UserRole,
            phoneNumber: String? = null,
            birthDate: LocalDate? = null,
            gender: String? = null,
            zipcode: String? = null,
            baseAddress: String? = null,
            detailAddress: String? = null,
            userType: UserType? = null,
            marketingAgreed: Boolean = false,
        ): User {
            return User(
                email = Email(email),
                password = Password(password),
                name = Name(name),
                nickname = Nickname(nickname),
                role = role,
                phoneNumber = phoneNumber,
                birthDate = birthDate,
                gender = gender,
                zipcode = zipcode,
                baseAddress = baseAddress,
                detailAddress = detailAddress,
                userType = userType,
                marketingAgreed = marketingAgreed,
            )
        }

        fun create(
            phoneNumber: String,
            password: String,
            name: String,
            nickname: String,
            role: UserRole,
            birthDate: LocalDate? = null,
            gender: String? = null,
            zipcode: String? = null,
            baseAddress: String? = null,
            detailAddress: String? = null,
            userType: UserType? = null,
            marketingAgreed: Boolean = false,
        ): User {
            return User(
                phoneNumber = phoneNumber,
                password = Password(password),
                name = Name(name),
                nickname = Nickname(nickname),
                role = role,
                birthDate = birthDate,
                gender = gender,
                zipcode = zipcode,
                baseAddress = baseAddress,
                detailAddress = detailAddress,
                userType = userType,
                marketingAgreed = marketingAgreed,
            )
        }

        fun create(
            email: String,
            provider: String,
            providerId: String,
            name: String,
            nickname: String,
            role: UserRole,
            phoneNumber: String? = null,
            birthDate: LocalDate? = null,
            gender: String? = null,
            zipcode: String? = null,
            baseAddress: String? = null,
            detailAddress: String? = null,
            userType: UserType? = null,
            marketingAgreed: Boolean = false,
        ): User {
            return User(
                email = Email(email),
                provider = provider,
                providerId = providerId,
                name = Name(name),
                nickname = Nickname(nickname),
                role = role,
                phoneNumber = phoneNumber,
                birthDate = birthDate,
                gender = gender,
                zipcode = zipcode,
                baseAddress = baseAddress,
                detailAddress = detailAddress,
                userType = userType,
                marketingAgreed = marketingAgreed,
            )
        }
    }

    fun changeRole(newRole: UserRole) {
        this.role = newRole
    }

    fun updateLastLogin() {
        this.lastLoginAt = LocalDateTime.now()
    }

    fun incrementPurchaseCount() {
        this.totalPurchases++
    }

    fun incrementSalesCount() {
        this.totalSales++
    }

    fun updateRating(
        newRating: Double,
        reviewCount: Int,
    ) {
        this.rating = newRating
        this.reviewCount = reviewCount
    }

    // Value Objects
    @JvmInline
    value class Email(val value: String) {
        init {
            require(value.contains("@")) { "이메일 형식이 올바르지 않습니다" }
        }
    }

    @JvmInline
    value class Name(val value: String) {
        init {
            require(value.isNotBlank()) { "이름은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Nickname(val value: String) {
        init {
            require(value.isNotBlank()) { "닉네임은 비어있을 수 없습니다" }
            require(value.length in 2..20) { "닉네임은 2-20자 사이여야 합니다" }
        }
    }

    @JvmInline
    value class Password(val value: String) {
        init {
            require(value.isNotBlank()) { "비밀번호는 비어있을 수 없습니다" }
        }
    }
}
