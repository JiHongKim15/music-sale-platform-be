package com.sodamjae.domain.shop

import com.sodamjae.domain.user.User

class Shop private constructor(
    val id: ShopId?,
    private var name: Name,
    private var description: Description,
    private var address: Address,
    private var phoneNumber: PhoneNumber,
    private var businessHours: BusinessHours,
    private var isShippingAvailable: Boolean,
    val seller: User
) {
    // Getters for persistence
    fun getName(): Name = name
    fun getDescription(): Description = description
    fun getAddress(): Address = address
    fun getPhoneNumber(): PhoneNumber = phoneNumber
    fun getBusinessHours(): BusinessHours = businessHours
    fun isShippingAvailable(): Boolean = isShippingAvailable

    companion object {
        fun create(
            name: Name,
            description: Description,
            address: Address,
            phoneNumber: PhoneNumber,
            businessHours: BusinessHours,
            isShippingAvailable: Boolean,
            seller: User
        ): Shop {
            return Shop(
                id = null,
                name = name,
                description = description,
                address = address,
                phoneNumber = phoneNumber,
                businessHours = businessHours,
                isShippingAvailable = isShippingAvailable,
                seller = seller
            )
        }
    }

    fun updateShopInfo(
        name: Name,
        description: Description,
        address: Address,
        phoneNumber: PhoneNumber,
        businessHours: BusinessHours,
        isShippingAvailable: Boolean
    ) {
        this.name = name
        this.description = description
        this.address = address
        this.phoneNumber = phoneNumber
        this.businessHours = businessHours
        this.isShippingAvailable = isShippingAvailable
    }

    // Value Objects
    @JvmInline
    value class ShopId(val value: Long)

    @JvmInline
    value class Name(val value: String) {
        init {
            require(value.isNotBlank()) { "매장 이름은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Description(val value: String) {
        init {
            require(value.isNotBlank()) { "매장 설명은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Address(val value: String) {
        init {
            require(value.isNotBlank()) { "주소는 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class PhoneNumber(val value: String) {
        init {
            require(value.matches(Regex("^\\d{2,3}-\\d{3,4}-\\d{4}$"))) { "전화번호 형식이 올바르지 않습니다" }
        }
    }

    @JvmInline
    value class BusinessHours(val value: String)
} 