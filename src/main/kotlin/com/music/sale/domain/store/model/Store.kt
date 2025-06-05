package com.music.sale.domain.store.model

/**
 * 상점(매장) 도메인 모델
 */
class Store(
    val id: Long? = null,
    val name: String,
    val description: String,
    val address: String,
    val contactNumber: String,
    val businessNumber: String,
    val imageUrl: String?,
    val status: String,
    val sellerId: Long
) {
    companion object {
        fun empty() = Store(
            id = null,
            name = "",
            description = "",
            address = "",
            contactNumber = "",
            businessNumber = "",
            imageUrl = null,
            status = "ACTIVE",
            sellerId = 0L
        )
    }
}