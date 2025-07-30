package com.music.sale.persistence.wishlist

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.application.wishlist.port.outport.WishlistPort
import com.music.sale.common.Pageable
import com.music.sale.persistence.wishlist.entity.WishlistEntity
import com.music.sale.persistence.wishlist.repository.WishlistRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
open class WishlistPersistenceAdapter(
    private val repository: WishlistRepository,
) : WishlistPort {
    override fun findByUserId(
        userId: Long,
        pageable: Pageable,
    ): Page<WishlistOutput> {
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize)
        val page = repository.findByUserId(userId, pageRequest)
        return page.map { entity ->
            WishlistOutput(
                id = entity.id!!,
                userId = entity.userId,
                product =
                    ProductOutput(
                        id = entity.productId,
                        // 실제로는 ProductService에서 조회해야 함
                        name = "",
                        catalog =
                            ProductOutput.ProductCatalog(
                                id = 0,
                                category =
                                    com.music.sale.domain.category.Category(
                                        id = 0,
                                        name = "",
                                        type =
                                            com.music.sale.domain
                                                .category
                                                .CategoryType
                                                .PRODUCT,
                                        parent = null,
                                        path = "",
                                        depth = 0,
                                        isActive = true,
                                    ),
                            ),
                        price = 0,
                        seller = null,
                        store = null,
                        condition =
                            com.music.sale.domain.product.enum.ProductCondition.NEW,
                        conditionGrade =
                            com.music.sale.domain.product.enum.ProductConditionGrade
                                .S,
                        stockQuantity = 0,
                        status =
                            com.music.sale.domain.product.enum.ProductStatus
                                .SELLING,
                        attributes = emptyMap(),
                    ),
                createdAt = entity.createdAt,
            )
        }
    }

    override fun save(
        userId: Long,
        productId: Long,
    ): WishlistOutput {
        val entity =
            WishlistEntity(
                userId = userId,
                productId = productId,
            )
        val savedEntity = repository.save(entity)
        return WishlistOutput(
            id = savedEntity.id!!,
            userId = savedEntity.userId,
            product =
                ProductOutput(
                    id = savedEntity.productId,
                    name = "",
                    catalog =
                        ProductOutput.ProductCatalog(
                            id = 0,
                            category =
                                com.music.sale.domain.category.Category(
                                    id = 0,
                                    name = "",
                                    type =
                                        com.music.sale.domain
                                            .category
                                            .CategoryType
                                            .PRODUCT,
                                    parent = null,
                                    path = "",
                                    depth = 0,
                                    isActive = true,
                                ),
                        ),
                    price = 0,
                    seller = null,
                    store = null,
                    condition = com.music.sale.domain.product.enum.ProductCondition.NEW,
                    conditionGrade =
                        com.music.sale.domain.product.enum.ProductConditionGrade.S,
                    stockQuantity = 0,
                    status = com.music.sale.domain.product.enum.ProductStatus.SELLING,
                    attributes = emptyMap(),
                ),
            createdAt = savedEntity.createdAt,
        )
    }

    override fun deleteByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ) {
        val entity = repository.findByUserIdAndProductId(userId, productId)
        entity?.let { repository.delete(it) }
    }

    override fun existsByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): Boolean {
        return repository.existsByUserIdAndProductId(userId, productId)
    }

    override fun findByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): WishlistOutput? {
        val entity = repository.findByUserIdAndProductId(userId, productId)
        return entity?.let {
            WishlistOutput(
                id = it.id!!,
                userId = it.userId,
                product =
                    ProductOutput(
                        id = it.productId,
                        name = "",
                        catalog =
                            ProductOutput.ProductCatalog(
                                id = 0,
                                category =
                                    com.music.sale.domain.category.Category(
                                        id = 0,
                                        name = "",
                                        type =
                                            com.music.sale.domain
                                                .category
                                                .CategoryType
                                                .PRODUCT,
                                        parent = null,
                                        path = "",
                                        depth = 0,
                                        isActive = true,
                                    ),
                            ),
                        price = 0,
                        seller = null,
                        store = null,
                        condition =
                            com.music.sale.domain.product.enum.ProductCondition.NEW,
                        conditionGrade =
                            com.music.sale.domain.product.enum.ProductConditionGrade
                                .S,
                        stockQuantity = 0,
                        status =
                            com.music.sale.domain.product.enum.ProductStatus
                                .SELLING,
                        attributes = emptyMap(),
                    ),
                createdAt = it.createdAt,
            )
        }
    }
}
