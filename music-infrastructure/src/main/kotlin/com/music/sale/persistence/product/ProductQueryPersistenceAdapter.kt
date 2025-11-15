package com.music.sale.persistence.product

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.application.product.port.outport.ProductQueryPort
import com.music.sale.domain.product.Product
import com.music.sale.persistence.product.repository.ProductItemCommandJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Component
open class ProductQueryPersistenceAdapter(
    private val jpaRepository: ProductItemCommandJpaRepository,
) : ProductQueryPort {
    @Transactional(readOnly = true)
    override fun findAll(pageable: PageRequest): Page<Product> {
        val contentEntities = jpaRepository.findAllWithJoins(pageable)
        val totalElements = jpaRepository.countAllItems()
        
        // 각 entity의 이미지를 명시적으로 로드
        contentEntities.forEach { entity ->
            entity.images.size // Lazy collection 초기화
        }
        
        val contentDomains = contentEntities.map { entity -> entityToDomain(entity) }
        return org.springframework.data.domain.PageImpl(contentDomains, pageable, totalElements)
    }

    @Transactional(readOnly = true)
    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<Product> {
        return findAll(pageable)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Product? {
        return jpaRepository.findByIdWithJoins(id).map(::entityToDomain).orElse(null)
    }

    private fun entityToDomain(entity: com.music.sale.persistence.product.entity.ProductItemEntity): Product {
        return Product(
            id = entity.id!!,
            catalog = com.music.sale.domain.product.ProductCatalog(
                id = entity.catalog.id!!,
                name = entity.catalog.name,
                category = com.music.sale.domain.category.Category(
                    id = entity.catalog.category.id!!,
                    name = entity.catalog.category.name,
                    type = entity.catalog.category.type,
                    parent = null,
                    path = entity.catalog.category.path,
                    depth = entity.catalog.category.depth,
                ),
                brand = entity.catalog.brand,
                attributes = entity.catalog.attributes,
            ),
            price = entity.price,
            seller = entity.seller?.let {
                com.music.sale.domain.user.User(
                    id = it.id!!,
                    name = com.music.sale.domain.user.User.Name(it.name),
                )
            },
            store = entity.store?.let { com.music.sale.domain.store.Store(it.id!!) },
            condition = entity.condition,
            conditionGrade = entity.conditionGrade,
            stockQuantity = entity.stockQuantity,
            status = entity.status,
            customName = entity.customName,
            customAttributes = entity.customAttributes,
            images = entity.images.map {
                com.music.sale.domain.product.ProductImage(
                    id = it.id,
                    productId = entity.id!!,
                    imageUrl = it.imageUrl,
                    isThumbnail = it.isThumbnail,
                    order = it.imageOrder,
                    altText = it.altText,
                )
            }.toMutableList(),
        )
    }
}
