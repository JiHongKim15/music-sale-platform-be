package com.music.sale.domain.store.model

/**
 * 상점(매장) 도메인 모델
 */
class Store(
    val id: Long? = null,
    val name: String = "",
    val description: String = "",
    val address: Address = Address(),
    val contactNumber: String = "",
    val businessNumber: String = "",
    val imageUrl: String? = null,
    val status: StoreStatus = StoreStatus.ACTIVE,
    val sellerId: Long = 0L,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {
    constructor(id: Long) : this(
        id = id,
        name = "",
        description = "",
        address = Address(),
        contactNumber = "",
        businessNumber = "",
        imageUrl = null,
        status = StoreStatus.ACTIVE,
        sellerId = 0L,
        createdAt = null,
        updatedAt = null
    )

    enum class StoreStatus {
        PENDING, ACTIVE, SUSPENDED, CLOSED
    }

    data class Address(
        val zipcode: String = "",
        val baseAddress: String = "",
        val detailAddress: String = "",
        val latitude: Double? = null,
        val longitude: Double? = null
    )

    companion object {
        fun empty() = Store(
            id = null,
            name = "",
            description = "",
            address = Address(),
            contactNumber = "",
            businessNumber = "",
            imageUrl = null,
            status = StoreStatus.ACTIVE,
            sellerId = 0L,
            createdAt = null,
            updatedAt = null
        )
    }
}