// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.OneToOne
import jakarta.persistence.JoinColumn
import java.time.LocalDateTime

@Entity
@Table(
    name = "auth_users",
    uniqueConstraints = [
        jakarta.persistence.UniqueConstraint(columnNames = ["user_id"])
    ]
)
class AuthUserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @OneToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: UserEntity,
    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,
    @Column(name = "password_salt")
    var passwordSalt: String? = null,
    @Column(name = "failed_login_attempts")
    var failedLoginAttempts: Int = 0,
    @Column(name = "locked_until")
    var lockedUntil: LocalDateTime? = null,
    @Column(name = "password_changed_at")
    var passwordChangedAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "must_change_password")
    var mustChangePassword: Boolean = false,
    @Column(name = "two_factor_enabled")
    var twoFactorEnabled: Boolean = false,
    @Column(name = "two_factor_secret")
    var twoFactorSecret: String? = null,
    @Column(name = "backup_codes", columnDefinition = "TEXT")
    var backupCodes: String? = null,
) : BaseEntity() {
    fun isLocked(): Boolean {
        return lockedUntil?.isAfter(LocalDateTime.now()) ?: false
    }
    
    fun incrementFailedAttempts() {
        failedLoginAttempts++
        if (failedLoginAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount()
        }
    }
    
    fun resetFailedAttempts() {
        failedLoginAttempts = 0
        lockedUntil = null
    }
    
    fun lockAccount() {
        lockedUntil = LocalDateTime.now().plusMinutes(LOCK_DURATION_MINUTES)
    }
    
    fun changePassword(newPasswordHash: String) {
        passwordHash = newPasswordHash
        passwordChangedAt = LocalDateTime.now()
        mustChangePassword = false
        resetFailedAttempts()
    }
    
    fun shouldChangePassword(): Boolean {
        val passwordAge = java.time.Duration.between(passwordChangedAt, LocalDateTime.now())
        return mustChangePassword || passwordAge.toDays() > PASSWORD_EXPIRE_DAYS
    }
    
    companion object {
        const val MAX_FAILED_ATTEMPTS = 5
        const val LOCK_DURATION_MINUTES = 30L
        const val PASSWORD_EXPIRE_DAYS = 90L
    }
}
