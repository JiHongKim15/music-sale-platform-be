package com.music.sale.application.category.port.`in`

import com.music.sale.domain.category.Category


interface CategoryUseCase {
    fun getCategoryById(id: Long): Category
}