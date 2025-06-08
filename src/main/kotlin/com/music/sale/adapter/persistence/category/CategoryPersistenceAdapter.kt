package com.music.sale.adapter.persistence.category

import com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.adapter.persistence.category.repository.CategoryRepository
import com.music.sale.application.category.port.out.CategoryPort
import com.music.sale.domain.category.Category
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CategoryPersistenceAdapter(
    private val repository: CategoryRepository,
    private val mapper: CategoryPersistenceMapper
) : CategoryPort {

    override fun getCategoryById(id: Long): Category {
        return mapper.toDomain(repository.findById(id).get())
    }

}