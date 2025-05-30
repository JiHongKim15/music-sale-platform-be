package com.music.sale.adapter.persistence.product.enum

/**
 * 제품 상태(컨디션) 엔티티 enum
 * 데이터베이스에 저장되는 값
 */
enum class ProductConditionEntity {
    NEW,
    USED;

    companion object {
        fun fromDomain(condition: com.music.sale.domain.product.enum.ProductCondition): ProductConditionEntity {
            return when (condition) {
                com.music.sale.domain.product.enum.ProductCondition.NEW -> NEW
                com.music.sale.domain.product.enum.ProductCondition.USED -> USED
            }
        }

        fun toDomain(entity: ProductConditionEntity): com.music.sale.domain.product.enum.ProductCondition {
            return when (entity) {
                NEW -> com.music.sale.domain.product.enum.ProductCondition.NEW
                USED -> com.music.sale.domain.product.enum.ProductCondition.USED
            }
        }
    }
} 