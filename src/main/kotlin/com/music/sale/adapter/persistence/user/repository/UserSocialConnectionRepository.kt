// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.repository

import com.music.sale.adapter.persistence.user.entity.UserSocialConnectionEntity
import com.music.sale.adapter.persistence.user.entity.SocialProvider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserSocialConnectionRepository : JpaRepository<UserSocialConnectionEntity, Long> {
    
    fun findByUserId(userId: Long): List<UserSocialConnectionEntity>
    
    fun existsByUserIdAndProvider(userId: Long, provider: SocialProvider): Boolean
    
    fun deleteByUserIdAndProvider(userId: Long, provider: SocialProvider)
    
    fun findByUserIdAndProvider(userId: Long, provider: SocialProvider): UserSocialConnectionEntity?
} 
