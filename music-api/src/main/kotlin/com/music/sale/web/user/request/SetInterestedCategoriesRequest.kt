package com.music.sale.web.user.request

import jakarta.validation.constraints.NotEmpty

data class SetInterestedCategoriesRequest(
    @field:NotEmpty(message = "관심 카테고리는 최소 1개 이상 선택해야 합니다") val categoryIds: List<Long>,
)
