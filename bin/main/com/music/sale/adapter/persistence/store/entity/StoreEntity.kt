// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.store.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
