package com.music.sale.adapter.web.user.response

import com.music.sale.application.user.dto.UserOutput

data class UserResponse(
    val id: Long?,
    val email: String,
    val name: String
) {
    companion object {
        fun from(output: UserOutput) = UserResponse(
            id = output.id,
            email = output.email,
            name = output.name
        )
    }
} 