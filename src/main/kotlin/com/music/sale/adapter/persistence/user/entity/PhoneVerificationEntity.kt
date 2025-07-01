// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "phone_verifications")
class PhoneVerificationEntity(
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    val id: Long? = null,
    
    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String,
    
    @Column(name = "verification_code", nullable = false)
    val verificationCode: String,
    
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_type", nullable = false)
    val verificationType: VerificationType,
    
    @Column(name = "expires_at", nullable = false)
    val expiresAt: LocalDateTime,
    
    @Column(name = "verified_at")
    var verifiedAt: LocalDateTime? = null,
    
    @Column(name = "attempt_count")
    var attemptCount: Int = 0,
    
    @Column(name = "is_used")
    var isUsed: Boolean = false,
    
    @Column(name = "user_id")
    var userId: Long? = null, // 가입 완료 후 연결
    
    @Column(name = "ip_address")
    var ipAddress: String? = null,
    
    @Column(name = "user_agent", columnDefinition = "TEXT")
    var userAgent: String? = null
    
) : BaseEntity() {
    
    fun isExpired(): Boolean {
        return LocalDateTime.now().isAfter(expiresAt)
    }
    
    fun isVerified(): Boolean {
        return verifiedAt != null
    }
    
    fun canAttempt(): Boolean {
        return !isExpired() && !isUsed && attemptCount < MAX_ATTEMPT_COUNT
    }
    
    fun incrementAttempt() {
        attemptCount++
    }
    
    fun verify(): Boolean {
        return if (canAttempt()) {
            verifiedAt = LocalDateTime.now()
            isUsed = true
            true
        } else {
            false
        }
    }
    
    companion object {
        const val MAX_ATTEMPT_COUNT = 5
        const val VERIFICATION_CODE_LENGTH = 6
        const val VERIFICATION_EXPIRE_MINUTES = 5L
        
        fun createVerificationCode(): String {
            return (100000..999999).random().toString()
        }
        
        fun createExpirationTime(): LocalDateTime {
            return LocalDateTime.now().plusMinutes(VERIFICATION_EXPIRE_MINUTES)
        }
    }
}

enum class VerificationType {
    SIGNUP,         // 회원가입
    LOGIN,          // 로그인
    PASSWORD_RESET, // 비밀번호 재설정
    PHONE_CHANGE,   // 휴대폰 번호 변경
    ACCOUNT_VERIFY  // 계정 인증
} 
