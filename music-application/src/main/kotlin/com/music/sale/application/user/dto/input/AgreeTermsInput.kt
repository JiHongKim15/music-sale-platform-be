package com.music.sale.application.user.dto.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AgreeTermsInput(
    @field:NotNull val userId: Long,
    @field:NotBlank val title: String,
    @field:NotBlank val version: String,
    @field:NotNull val isAgreed: Boolean,
)
