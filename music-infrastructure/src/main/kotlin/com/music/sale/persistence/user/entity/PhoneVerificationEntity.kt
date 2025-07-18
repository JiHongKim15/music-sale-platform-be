package com.music.sale.persistence.user.entity

import com.music.sale.domain.user.enum.VerificationType
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "phone_verifications",
    uniqueConstraints = [UniqueConstraint(columnNames = ["phone_number", "verification_type"])],
)
class PhoneVerificationEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "phone_number", nullable = false) val phoneNumber: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_type", nullable = false)
    val verificationType: VerificationType,
    @Column(name = "verification_code", nullable = false) val verificationCode: String,
    @Column(name = "expires_at", nullable = false) val expiresAt: LocalDateTime,
    @Column(name = "attempt_count", nullable = false) var attemptCount: Int = 0,
    @Column(name = "is_used", nullable = false) var isUsed: Boolean = false,
    @Column(name = "used_at") var usedAt: LocalDateTime? = null,
) : BaseEntity()
