package com.music.sale.persistence.product.dto

import com.music.sale.persistence.category.entity.CategoryEntity

data class ProductCatalogQueryResult(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val brand: String,
    val attribute: Map<String, String>,
) 
