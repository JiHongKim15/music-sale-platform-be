// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.user.entity

import com.music.sale.domain.user.enum.Gender
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.domain.user.enum.UserRole
import com.music.sale.domain.user.enum.UserType
import com.music.sale.persistence.category.entity.CategoryEntity
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    // 기본 정보
    @Column(nullable = false, unique = true) val email: String,
    @Column(nullable = false) var name: String,
    @Column(nullable = false, unique = true) var nickname: String,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var role: UserRole,
    // 소셜 로그인 정보
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    var provider: SocialProvider? = null,
    @Column(name = "provider_id") var providerId: String? = null,
    // 연락처 정보
    @Column(name = "phone_number", unique = true) var phoneNumber: String? = null,
    @Column(name = "phone_verified") var phoneVerified: Boolean = false,
    @Column(name = "phone_verified_at") var phoneVerifiedAt: LocalDateTime? = null,
    // 개인 정보
    @Column(name = "birth_date") var birthDate: LocalDate? = null,
    @Enumerated(EnumType.STRING) @Column(name = "gender") var gender: Gender? = null,
    // 주소 정보
    @Column(name = "zipcode") var zipcode: String? = null,
    @Column(name = "base_address") var baseAddress: String? = null,
    @Column(name = "detail_address") var detailAddress: String? = null,
    // 프로필 정보
    @Column(name = "profile_image_url") var profileImageUrl: String? = null,
    @Column(name = "bio", length = 500) var bio: String? = null,
    // 사용자 성향
    @Enumerated(EnumType.STRING) @Column(name = "user_type") var userType: UserType? = null,
    // 구매자, 판매자, 둘다
    @Column(name = "preferred_categories", columnDefinition = "TEXT")
    var preferredCategories: String? = null,
    // JSON 형태로 관심 카테고리 저장
    @Column(name = "preferred_price_range_min") var preferredPriceRangeMin: Int? = null,
    @Column(name = "preferred_price_range_max") var preferredPriceRangeMax: Int? = null,
    // 활동 통계
    @Column(name = "total_purchases") var totalPurchases: Int = 0,
    @Column(name = "total_sales") var totalSales: Int = 0,
    @Column(name = "rating") var rating: Double = 0.0,
    @Column(name = "review_count") var reviewCount: Int = 0,
    // 계정 상태
    @Column(name = "is_active") var isActive: Boolean = true,
    @Column(name = "is_verified") var isVerified: Boolean = false,
    @Column(name = "last_login_at") var lastLoginAt: LocalDateTime? = null,
    @Column(name = "marketing_agreed") var marketingAgreed: Boolean = false,
    @Column(name = "marketing_agreed_at") var marketingAgreedAt: LocalDateTime? = null,
) : BaseEntity() {
    // 소셜 로그인 연동 정보 (One-to-Many)
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var socialConnections: MutableList<UserSocialConnectionEntity> = mutableListOf()

    // 관심 카테고리 (Many-to-Many)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_interested_categories",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")],
    )
    var interestedCategories: MutableSet<CategoryEntity> = mutableSetOf()

    fun getFullAddress(): String? {
        return if (baseAddress != null) {
            "${zipcode ?: ""} $baseAddress ${detailAddress ?: ""}".trim()
        } else {
            null
        }
    }

    fun addSocialConnection(
        provider: SocialProvider,
        providerId: String,
        accessToken: String? = null,
    ) {
        val connection =
            UserSocialConnectionEntity(
                user = this,
                provider = provider,
                providerId = providerId,
                accessToken = accessToken,
                connectedAt = LocalDateTime.now(),
            )
        socialConnections.add(connection)
    }

    fun removeSocialConnection(provider: SocialProvider) {
        socialConnections.removeIf { it.provider == provider }
    }

    fun hasSocialConnection(provider: SocialProvider): Boolean {
        return socialConnections.any { it.provider == provider }
    }
}
