package com.music.sale.persistence.viewcount

import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.application.viewcount.port.outport.ProductViewCountPort
import com.music.sale.domain.viewcount.ProductViewCount
import com.music.sale.persistence.viewcount.entity.ProductViewCountEntity
import com.music.sale.persistence.viewcount.repository.ProductViewCountRepository
import org.springframework.stereotype.Repository

@Repository
open class ProductViewCountPersistenceAdapter(
    private val repository: ProductViewCountRepository,
) : ProductViewCountPort {
    override fun findByProductId(productId: Long): ProductViewCountOutput? {
        return repository.findByProductId(productId)?.toOutput()
    }

    override fun save(viewCount: ProductViewCount): ProductViewCountOutput {
        val entity = ProductViewCountEntity.fromDomain(viewCount)
        return repository.save(entity).toOutput()
    }
}
