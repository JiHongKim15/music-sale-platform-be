package com.music.sale.persistence.user.repository

import com.music.sale.domain.user.enum.VerificationType
import com.music.sale.persistence.user.entity.PhoneVerificationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhoneVerificationRepository : JpaRepository<PhoneVerificationEntity, Long> {
    fun findByPhoneNumberAndVerificationType(
        phoneNumber: String,
        verificationType: VerificationType,
    ): PhoneVerificationEntity?

    fun deleteByPhoneNumberAndVerificationType(
        phoneNumber: String,
        verificationType: VerificationType,
    )
}
