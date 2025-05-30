package com.music.sale.application.user.mapper

import com.music.sale.application.user.dto.UserOutput
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toOutput(user: User): UserOutput {
        return UserOutput(
            id = user.id,
            email = user.email.value,
            name = user.getName().value,
            role = user.getRole()
        )
    }
} 