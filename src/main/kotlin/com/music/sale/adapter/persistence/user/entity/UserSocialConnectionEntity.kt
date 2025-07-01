// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "user_social_connections",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["user_id", "provider"]),
        UniqueConstraint(columnNames = ["provider", "provider_id"])
    ]
)
class UserSocialConnectionEntity(
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    val id: Long? = null,
    
    // 사용자 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,
    
    // 소셜 플랫폼 정보
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    val provider: SocialProvider,
    
    @Column(name = "provider_id", nullable = false)
    val providerId: String,
    
    // 연동 정보
    @Column(name = "access_token", columnDefinition = "TEXT")
    var accessToken: String? = null,
    
    @Column(name = "refresh_token", columnDefinition = "TEXT")
    var refreshToken: String? = null,
    
    @Column(name = "token_expires_at")
    var tokenExpiresAt: LocalDateTime? = null,
    
    @Column(name = "connected_at", nullable = false)
    val connectedAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "last_used_at")
    var lastUsedAt: LocalDateTime? = null,
    
    // 소셜 플랫폼에서 가져온 추가 정보
    @Column(name = "provider_email")
    var providerEmail: String? = null,
    
    @Column(name = "provider_name")
    var providerName: String? = null,
    
    @Column(name = "provider_profile_image")
    var providerProfileImage: String? = null,
    
    @Column(name = "is_active")
    var isActive: Boolean = true
    
) : BaseEntity() {
    
    fun updateToken(accessToken: String?, refreshToken: String? = null, expiresAt: LocalDateTime? = null) {
        this.accessToken = accessToken
        this.refreshToken = refreshToken
        this.tokenExpiresAt = expiresAt
        this.lastUsedAt = LocalDateTime.now()
    }
    
    fun isTokenExpired(): Boolean {
        return tokenExpiresAt?.isBefore(LocalDateTime.now()) ?: false
    }
} 
