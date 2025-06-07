package com.music.sale.adapter.persistence.seller.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.seller.model.Seller
import jakarta.persistence.*

@Entity
@Table(name = "sellers")
class SellerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val companyName: String,

    @Column(nullable = false)
    val businessNumber: String,

    @Column(nullable = false)
    val contactEmail: String,

    @Column(nullable = false)
    val contactPhone: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: SellerStatus

) : BaseEntity() {

    enum class SellerStatus {
        PENDING, APPROVED, REJECTED, SUSPENDED
    }

    // 도메인 객체로 변환
    fun toDomain(): Seller {
        return Seller(
            id = id,
            userId = userId,
            companyName = Seller.CompanyName(companyName),
            businessNumber = Seller.BusinessNumber(businessNumber),
            contactEmail = Seller.ContactEmail(contactEmail),
            contactPhone = Seller.ContactPhone(contactPhone),
            status = Seller.SellerStatus.valueOf(status.name),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        // 도메인 객체로부터 엔티티 생성
        fun fromDomain(seller: Seller): SellerEntity {
            return SellerEntity(
                id = seller.id,
                userId = seller.userId,
                companyName = seller.companyName.value,
                businessNumber = seller.businessNumber.value,
                contactEmail = seller.contactEmail.value,
                contactPhone = seller.contactPhone.value,
                status = SellerStatus.valueOf(seller.status.name)
            )
        }

        // ID만으로 엔티티 생성
        fun fromId(id: Long): SellerEntity {
            return SellerEntity(
                id = id,
                userId = 0L,
                companyName = "Default Company",
                businessNumber = "0000000000",
                contactEmail = "default@example.com",
                contactPhone = "0000000000",
                status = SellerStatus.PENDING
            )
        }
    }
} 