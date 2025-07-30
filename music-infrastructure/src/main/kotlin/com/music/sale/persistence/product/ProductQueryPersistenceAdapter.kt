package com.music.sale.persistence.product

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.application.product.port.outport.ProductQueryPort
import com.music.sale.domain.product.Product
import com.music.sale.persistence.product.mapper.ProductQueryPersistenceMapper
import com.music.sale.persistence.product.repository.ProductQueryJooqRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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
    override fun findAll(pageable: PageRequest): Page<Product> {
        val queryResults = queryRepository.findAll(pageable)

        return queryResults.map { mapper.toDomain(it) }
    }

    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<Product> {
        val queryResults = queryRepository.searchProducts(searchCondition, pageable)

        return queryResults.map { mapper.toDomain(it) }
    }

    override fun findById(id: Long): Product? {
        return queryRepository.findById(id)?.let { mapper.toDomain(it) }
    }
}
