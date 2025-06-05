package com.music.sale.adapter.persistence.store.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.store.model.Store
import jakarta.persistence.*

@Entity
@Table(name = "stores")
class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

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

    // 도메인 객체로 변환
    fun toDomain(): Store {
        return Store(
            id = id,
            name = Store.StoreName(name),
            description = Store.Description(description),
            address = Store.Address(
                zipcode = zipcode,
                baseAddress = baseAddress,
                detailAddress = detailAddress ?: "",
                latitude = latitude,
                longitude = longitude
            ),
            contactNumber = Store.ContactNumber(contactNumber),
            businessNumber = Store.BusinessNumber(businessNumber),
            imageUrl = imageUrl,
            status = Store.StoreStatus.valueOf(status.name),
            sellerId = sellerId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        // 도메인 객체로부터 엔티티 생성
        fun fromDomain(store: Store): StoreEntity {
            return StoreEntity(
                id = store.id,
                name = store.name.value,
                description = store.description.value,
                zipcode = store.address.zipcode,
                baseAddress = store.address.baseAddress,
                detailAddress = store.address.detailAddress,
                latitude = store.address.latitude,
                longitude = store.address.longitude,
                contactNumber = store.contactNumber.value,
                businessNumber = store.businessNumber.value,
                imageUrl = store.imageUrl,
                status = StoreStatus.valueOf(store.status.name),
                sellerId = store.sellerId
            )
        }
    }
} 