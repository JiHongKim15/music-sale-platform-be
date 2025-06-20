package com.music.sale.application.category.port.out

import com.music.sale.domain.category.Category
import org.springframework.stereotype.Component


@Component
interface CategoryPort {
    fun getCategoryById(id: Long): Category

    fun findAll(): List<Category>

    fun findById(id: Long): Category?

    fun save(category: Category): Category

    fun getReferenceById(id: Long): Category
}