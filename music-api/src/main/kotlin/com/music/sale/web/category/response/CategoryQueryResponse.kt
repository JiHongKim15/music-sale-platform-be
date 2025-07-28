package com.music.sale.web.category.response

import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType

data class CategoryQueryResponse(
    val id: Long,
    val name: String,
    val type: CategoryType,
    val parent: Category?,
    val path: String,
    val depth: Int,
    val isActive: Boolean,
)
