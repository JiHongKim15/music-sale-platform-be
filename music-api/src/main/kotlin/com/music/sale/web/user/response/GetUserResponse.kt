package com.music.sale.web.user.response

import com.music.sale.domain.user.enums.UserRole
import com.music.sale.domain.user.enums.UserStatus
import java.time.LocalDateTime

data class GetUserResponse(
    val id: Long?,
    val nickname: String?,
    val profileImageUrl: String?,
    val email: String?,
    val role: UserRole?,
    val status: UserStatus?,
    val isVerified: Boolean,
    val createdAt: LocalDateTime?,
)
