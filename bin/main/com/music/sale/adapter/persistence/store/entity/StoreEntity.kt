package com.music.sale.adapter.persistence.store.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.store.model.Store
import jakarta.persistence.*

@Entity
@Table(name = "stores")
class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, length = 1000)
    val description: String,

    @Column(nullable = false)
    val zipcode: String,

    @Column(nullable = false)
    val baseAddress: String,

    @Column(nullable = true)
    val detailAddress: String? = null,

    @Column(nullable = true)
    val latitude: Double? = null,

    @Column(nullable = true)
    val longitude: Double? = null,

    @Column(nullable = false)
    val contactNumber: String,

    @Column(nullable = false)
    val businessNumber: String,

    @Column(nullable = true)
    val imageUrl: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: StoreStatus,

    @Column(nullable = false)
    val sellerId: Long

) : BaseEntity() {

    enum class StoreStatus {
        PENDING, ACTIVE, SUSPENDED, CLOSED
    }

    fun toDomain(): Store {
        return Store(
            id = id,
            name = name,
            description = description,
            address = Store.Address(
                zipcode = zipcode,
                baseAddress = baseAddress,
                detailAddress = detailAddress ?: "",
                latitude = latitude,
                longitude = longitude
            ),
            contactNumber = contactNumber,
            businessNumber = businessNumber,
            imageUrl = imageUrl,
            status = Store.StoreStatus.valueOf(status.name),
            sellerId = sellerId,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString()
        )
    }

    companion object {
        fun fromDomain(store: Store): StoreEntity {
            return StoreEntity(
                id = store.id ?: 0L,
                name = store.name,
                description = store.description,
                zipcode = store.address.zipcode,
                baseAddress = store.address.baseAddress,
                detailAddress = store.address.detailAddress,
                latitude = store.address.latitude,
                longitude = store.address.longitude,
                contactNumber = store.contactNumber,
                businessNumber = store.businessNumber,
                imageUrl = store.imageUrl,
                status = StoreStatus.valueOf(store.status.name),
                sellerId = store.sellerId
            )
        }

        fun fromId(id: Long): StoreEntity {
            return StoreEntity(
                id = id,
                name = "Default Store",
                description = "Default Store Description",
                zipcode = "00000",
                baseAddress = "Default Address",
                detailAddress = null,
                latitude = null,
                longitude = null,
                contactNumber = "0000000000",
                businessNumber = "0000000000",
                imageUrl = null,
                status = StoreStatus.PENDING,
                sellerId = 0L
            )
        }
    }
} 