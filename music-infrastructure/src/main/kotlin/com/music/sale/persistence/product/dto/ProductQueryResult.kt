package com.music.sale.persistence.product.dto

import com.music.sale.domain.product.ProductImage
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User

data class ProductQueryResult(
    val id: Long,
    val name: String,
    val catalog: ProductCatalogQueryResult,
    val price: Int,
    val seller: User?,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>?,
    val images: List<ProductImage>?
)
