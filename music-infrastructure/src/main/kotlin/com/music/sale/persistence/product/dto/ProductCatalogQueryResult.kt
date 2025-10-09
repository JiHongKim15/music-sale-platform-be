package com.music.sale.persistence.product.dto

import com.music.sale.domain.category.Category

data class ProductCatalogQueryResult(
    val id: Long,
    val name: String,
    val category: Category,
    val brand: String,
    val attribute: Map<String, String>,
) 
