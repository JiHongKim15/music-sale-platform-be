package com.music.sale.application.category.dto

import com.music.sale.domain.category.CategoryType

data class CategoryCreationCommand(
    val name: String,
    val type: CategoryType,
    val parentId: Long? = null,
)
