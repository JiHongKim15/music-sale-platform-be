// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.store.entity

import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "store")
class StoreEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
    @Column(nullable = false) val name: String,
    @Column(nullable = false, length = 1000) val description: String,
    @Column(nullable = false) val zipcode: String,
    @Column(nullable = false) val baseAddress: String,
    @Column(nullable = true) val detailAddress: String? = null,
    @Column(nullable = true) val latitude: Double? = null,
    @Column(nullable = true) val longitude: Double? = null,
    @Column(nullable = false) val contactNumber: String,
    @Column(nullable = false) val businessNumber: String,
    @Column(nullable = true) val imageUrl: String? = null,
    @Column(nullable = false) @Enumerated(EnumType.STRING) val status: StoreStatus,
    @Column(nullable = false) val sellerId: Long,
) : BaseEntity() {
    enum class StoreStatus {
        PENDING,
        ACTIVE,
        SUSPENDED,
        CLOSED,
    }
}
