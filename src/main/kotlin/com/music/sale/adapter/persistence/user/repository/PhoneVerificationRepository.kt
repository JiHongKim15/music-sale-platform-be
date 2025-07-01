// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.repository

import com.music.sale.adapter.persistence.user.entity.PhoneVerificationEntity
import com.music.sale.adapter.persistence.user.entity.VerificationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhoneVerificationRepository : JpaRepository<PhoneVerificationEntity, Long> {
    
    fun findByPhoneNumberAndVerificationType(phoneNumber: String, verificationType: VerificationType): PhoneVerificationEntity?
    
    fun deleteByPhoneNumberAndVerificationType(phoneNumber: String, verificationType: VerificationType)
    
    fun findByPhoneNumber(phoneNumber: String): List<PhoneVerificationEntity>
} 
