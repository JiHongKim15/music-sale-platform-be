// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.mapper

import com.music.sale.application.user.dto.UserOutput
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toOutput(user: User): UserOutput {
        return UserOutput(
            id = user.id ?: 0L,
            email = user.email?.value ?: "",
            name = user.name?.value ?: "",
            role = user.role ?: UserRole.USER,
        )
    }
}
