package com.music.sale.persistence.user.entity

import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "user_social_connections",
    uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "provider"])],
)
class UserSocialConnectionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,
    @Enumerated(EnumType.STRING) @Column(nullable = false) val provider: SocialProvider,
    @Column(name = "provider_id", nullable = false) val providerId: String,
    @Column(name = "provider_email") val providerEmail: String? = null,
    @Column(name = "provider_name") val providerName: String? = null,
    @Column(name = "provider_profile_image") val providerProfileImage: String? = null,
    @Column(name = "access_token") val accessToken: String? = null,
    @Column(name = "refresh_token") val refreshToken: String? = null,
    @Column(name = "token_expires_at") val tokenExpiresAt: LocalDateTime? = null,
    @Column(name = "connected_at", nullable = false)
    val connectedAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "last_used_at") val lastUsedAt: LocalDateTime? = null,
    @Column(name = "is_active", nullable = false) val isActive: Boolean = true,
) : BaseEntity()
