// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.shop

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.user.entity.UserEntity
import com.music.sale.domain.shop.Shop
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "shops")
class ShopEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var description: String,
    @Column(nullable = false) var address: String,
    @Column(name = "phone_number", nullable = false) var phoneNumber: String,
    @Column(name = "business_hours") var businessHours: String,
    @Column(name = "shipping_available") var isShippingAvailable: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "seller_id") val seller: UserEntity,
) : BaseEntity() {
    fun toDomain(): Shop {
        return Shop.create(
            name = Shop.Name(name),
            description = Shop.Description(description),
            address = Shop.Address(address),
            phoneNumber = Shop.PhoneNumber(phoneNumber),
            businessHours = Shop.BusinessHours(businessHours),
            isShippingAvailable = isShippingAvailable,
            seller = seller.toDomain(),
        )
    }

    companion object {
        fun fromDomain(
            shop: Shop,
            sellerEntity: UserEntity,
        ): ShopEntity {
            return ShopEntity(
                id = shop.id?.value,
                name = shop.getName().value,
                description = shop.getDescription().value,
                address = shop.getAddress().value,
                phoneNumber = shop.getPhoneNumber().value,
                businessHours = shop.getBusinessHours().value,
                isShippingAvailable = shop.isShippingAvailable(),
                seller = sellerEntity,
            )
        }
    }
}
