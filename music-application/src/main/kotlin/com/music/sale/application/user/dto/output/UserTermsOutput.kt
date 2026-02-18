package com.music.sale.application.user.dto.output

import java.time.LocalDateTime

data class UserTermsOutput(
    val title: String?,
    val version: String?,
    val isAgreed: Boolean?,
    val agreedAt: LocalDateTime?,
)
