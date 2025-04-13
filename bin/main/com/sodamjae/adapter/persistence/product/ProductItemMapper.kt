package com.sodamjae.adapter.persistence.product

import com.sodamjae.adapter.persistence.product.entity.ProductItemEntity
import com.sodamjae.adapter.persistence.product.enum.ProductConditionEntity
import com.sodamjae.domain.product.ProductItem
import com.sodamjae.domain.product.enum.ProductCondition
import org.springframework.stereotype.Component

/**
 * 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@Component
class ProductItemMapper {

    // 엔티티를 도메인 모델로 변환
    fun toDomain(entity: ProductItemEntity): ProductItem {
        // 도메인 객체 재구성 (도메인 모델에 맞게 구현 필요)
        return ProductItem.reconstitute(
            id = entity.id ?: throw IllegalStateException("엔티티 ID가 null입니다"),
            productCatalogId = entity.productCatalogId,
            sellerId = entity.sellerId,
            storeId = entity.storeId,
            condition = ProductCondition.valueOf(entity.condition.name),
            stockQuantity = entity.stockQuantity
        )
    }

    // 도메인 모델을 엔티티로 변환
    fun toEntity(domain: ProductItem): ProductItemEntity {
        return ProductItemEntity(
            id = domain.getId(),
            productCatalogId = domain.getProductCatalogId(),
            sellerId = domain.getSellerId(),
            storeId = domain.getStoreId(),
            condition = ProductConditionEntity.valueOf(domain.getCondition().name),
            stockQuantity = domain.getStockQuantity()
        )
    }
} 