// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.port.inport

import com.music.sale.domain.category.Category

interface CategoryUseCase {
    fun getCategoryById(id: Long): Category
}
