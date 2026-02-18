package com.music.sale.web.user.response

import java.time.LocalDateTime

data class GetUserTermsResponse(
    val title: String?,
    val version: String?,
    val isAgreed: Boolean?,
    val agreedAt: LocalDateTime?,
)
