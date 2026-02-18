package com.music.sale.application.user.dto.input

import jakarta.validation.constraints.NotBlank

data class UpdateUserInput(
    @field:NotBlank val nickname: String,
    val profileImageUrl: String?,
)
