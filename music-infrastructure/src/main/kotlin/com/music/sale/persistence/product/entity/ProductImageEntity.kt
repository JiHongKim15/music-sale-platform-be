package com.music.sale.persistence.product.entity

import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_image")
class ProductImageEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id", nullable = false)
    var productItem: ProductItemEntity,
    @Column(name = "image_url", nullable = false) val imageUrl: String,
    @Column(name = "is_thumbnail", nullable = false) var isThumbnail: Boolean = false,
    @Column(name = "image_order", nullable = false) val imageOrder: Int = 0,
    @Column(name = "alt_text") val altText: String? = null,
) : BaseEntity()
