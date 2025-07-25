package com.music.sale.application.product.service

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.mapper.ProductMapper
import com.music.sale.application.product.port.inport.ProductQueryUseCase
import com.music.sale.application.product.port.outport.ProductQueryPort
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class ProductQueryService(
    private val queryPort: ProductQueryPort,
    private val mapper: ProductMapper,
) : ProductQueryUseCase {
    override fun getProducts(pageable: Pageable): Page<ProductOutput> {
        return queryPort.findAll(pageable).map { mapper.toOutput(it) }
    }

    override fun getProductById(id: Long): ProductOutput? {
        return queryPort.findById(id)?.let { mapper.toOutput(it) }
    }

    override fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable,
    ): Page<ProductOutput> {
        return queryPort.searchProducts(
            searchCondition = mapper.toSearchProductCondition(input),
            pageable = pageable,
        )
            .map { mapper.toOutput(it) }
    }
} 
