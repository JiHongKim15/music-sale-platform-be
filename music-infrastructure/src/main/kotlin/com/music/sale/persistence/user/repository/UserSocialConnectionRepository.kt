package com.music.sale.persistence.user.repository

import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.persistence.user.entity.UserSocialConnectionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserSocialConnectionRepository : JpaRepository<UserSocialConnectionEntity, Long> {
    fun findByUserId(userId: Long): List<UserSocialConnectionEntity>

    fun existsByUserIdAndProvider(
        userId: Long,
        provider: SocialProvider,
    ): Boolean

    fun deleteByUserIdAndProvider(
        userId: Long,
        provider: SocialProvider,
    )

    fun findByProviderAndProviderId(
        provider: SocialProvider,
        providerId: String,
    ): UserSocialConnectionEntity?
}
