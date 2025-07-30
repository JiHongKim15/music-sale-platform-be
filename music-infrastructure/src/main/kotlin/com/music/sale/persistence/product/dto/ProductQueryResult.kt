package com.music.sale.persistence.product.dto

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.persistence.product.entity.ProductImageEntity

data class ProductQueryResult(
    val id: Long,
    val name: String,
    val catalog: ProductCatalogQueryResult,
    val price: Int,
    val sellerId: Long?, // UserEntity 대신 ID만
    val storeId: Long?,  // StoreEntity 대신 ID만
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>?,
    val images: List<ProductImageEntity>?
)
