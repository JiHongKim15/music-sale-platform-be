package com.music.sale.domain.user

import com.music.sale.domain.user.enums.UserRole
import com.music.sale.domain.user.enums.UserStatus
import com.music.sale.domain.user.exception.UserNotActiveException

data class User(
    val id: Long? = null,
    val nickname: String? = null,
    val profileImageUrl: String? = null,
    val email: String? = null,
    val role: UserRole? = null,
    var status: UserStatus? = null,
    val ci: String? = null,
    val realName: String? = null,
    val phoneNumber: String? = null,
    val birthDate: String? = null,
    val isVerified: Boolean = false,
) {
    fun updateProfile(
        nickname: String,
        profileImageUrl: String,
    ): User = copy(nickname = nickname, profileImageUrl = profileImageUrl)

    fun withdraw() {
        status = UserStatus.WITHDRAWAL
    }

    fun isActive(): Boolean = status == UserStatus.ACTIVE

    fun validateActive() {
        if (!isActive()) {
            throw UserNotActiveException()
        }
    }

    fun verifyIdentity(
        ci: String,
        realName: String,
        phoneNumber: String,
        birthDate: String,
    ): User = copy(ci = ci, realName = realName, phoneNumber = phoneNumber, birthDate = birthDate, isVerified = true)
}
