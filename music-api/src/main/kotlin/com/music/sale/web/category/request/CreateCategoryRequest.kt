package com.music.sale.web.category.request

import com.music.sale.domain.category.CategoryType

data class CreateCategoryRequest(
    val name: String,
    val type: CategoryType,
    val parentId: Long? = null,
)
