package com.music.sale.domain.order

class Shipping private constructor(
    val address: Address,
    val method: ShippingMethod,
    var trackingNumber: TrackingNumber? = null
) {
    fun updateTrackingNumber(trackingNumber: TrackingNumber) {
        this.trackingNumber = trackingNumber
    }

    enum class ShippingMethod {
        GENERAL, EXPRESS, PICKUP
    }

    @JvmInline
    value class Address(val value: String) {
        init {
            require(value.isNotBlank()) { "배송 주소는 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class TrackingNumber(val value: String) {
        init {
            require(value.isNotBlank()) { "운송장 번호는 비어있을 수 없습니다" }
        }
    }

    companion object {
        fun create(
            address: Address,
            method: ShippingMethod
        ): Shipping {
            return Shipping(
                address = address,
                method = method
            )
        }
    }
} 