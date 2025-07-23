package com.music.sale.domain.product

data class ProductImage(
    val id: Long? = null,
    // 어떤 Product 인스턴스에 속하는지
    val productId: Long,
    val imageUrl: String,
    var isThumbnail: Boolean = false,
    val order: Int = 0,
    val altText: String? = null,
)
