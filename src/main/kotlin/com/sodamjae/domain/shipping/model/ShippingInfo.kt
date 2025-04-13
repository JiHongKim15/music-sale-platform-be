package com.sodamjae.domain.shipping.model

import java.math.BigDecimal

/**
 * 배송 정보 값 객체 (Value Object)
 * 상품의 배송 관련 정보를 캡슐화하여 관리
 */
data class ShippingInfo(
    val isShippable: Boolean,                 // 배송 가능 여부
    val shippingFee: ShippingFee,             // 배송비
    val estimatedDays: EstimatedDeliveryDays, // 예상 배송 기간
    val freeShippingThreshold: BigDecimal?,   // 무료 배송 임계값 (특정 금액 이상 주문 시)
    val restrictedAreas: List<String> = emptyList(), // 배송 제한 지역
    val canPickup: Boolean = false,           // 매장 픽업 가능 여부
    val internationalShipping: Boolean = false // 해외 배송 가능 여부
) {
    // 값 객체 - 배송비
    data class ShippingFee(val value: BigDecimal) {
        init {
            require(value >= BigDecimal.ZERO) { "배송비는 0 이상이어야 합니다." }
        }

        fun isFree(): Boolean = value.compareTo(BigDecimal.ZERO) == 0
    }

    // 값 객체 - 예상 배송 기간
    data class EstimatedDeliveryDays(val min: Int, val max: Int) {
        init {
            require(min >= 0) { "최소 배송일은 0 이상이어야 합니다." }
            require(max >= min) { "최대 배송일은 최소 배송일 이상이어야 합니다." }
        }

        override fun toString(): String = if (min == max) "${min}일" else "${min}-${max}일"
    }

    // 배송비 계산 비즈니스 로직
    fun calculateShippingFee(orderAmount: BigDecimal): BigDecimal {
        return if (freeShippingThreshold != null && orderAmount >= freeShippingThreshold) {
            BigDecimal.ZERO
        } else {
            shippingFee.value
        }
    }

    // 배송 가능 여부 확인 비즈니스 로직
    fun canShipTo(area: String): Boolean {
        return isShippable && !restrictedAreas.contains(area)
    }
} 