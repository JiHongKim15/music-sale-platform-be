package com.music.sale.persistence.product

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.application.product.port.outport.ProductQueryPort
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import com.music.sale.persistence.product.mapper.ProductQueryPersistenceMapper
import com.music.sale.persistence.product.repository.ProductQueryJooqRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Component
@Transactional(readOnly = true)
open class ProductQueryPersistenceAdapter(
    private val queryRepository: ProductQueryJooqRepository,
    private val mapper: ProductQueryPersistenceMapper,
) : ProductQueryPort {
    override fun findAll(pageable: Pageable): Page<Product> {
        val springPageable = pageable.toSpringPageable()
        val queryResults = queryRepository.findAll(springPageable)

        return queryResults.map { mapper.toDomain(it) }
    }

    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: Pageable,
    ): Page<Product> {
        val springPageable = pageable.toSpringPageable()
        val queryResults = queryRepository.searchProducts(searchCondition, springPageable)

        return queryResults.map { mapper.toDomain(it) }
    }

    override fun findById(id: Long): Product? {
        return queryRepository.findById(id)?.let { mapper.toDomain(it) }
    }
}

// Pageable 확장 함수
private fun Pageable.toSpringPageable(): org.springframework.data.domain.Pageable {
    return org.springframework.data.domain.PageRequest.of(
        if (this.pageNumber < 0) 0 else this.pageNumber,
        this.pageSize,
        this.sort?.let { sort ->
            org.springframework.data.domain.Sort.by(
                org.springframework.data.domain.Sort.Direction.valueOf(
                    this.sortDirection?.name ?: "DESC",
                ),
                sort,
            )
        } ?: org.springframework.data.domain.Sort.by("createdAt").descending(),
    )
} 
