package com.music.sale.domain.shipping.model

import java.math.BigDecimal

/**
 * 배송 정책 도메인 모델
 * 여러 상품이 공유할 수 있는 배송 정책을 표현
 */
class ShippingPolicy(
    val id: Long?,
    val name: PolicyName,
    val shippingFee: Fee,
    val freeShippingThreshold: BigDecimal?,
    val deliveryDays: DeliveryDays,
    val sellerId: Long,
    val isDefault: Boolean,
    val canPickup: Boolean,
    val internationalShipping: Boolean,
    val restrictedAreas: List<String>
) {
    companion object {
        fun create(
            name: String,
            shippingFee: BigDecimal,
            freeShippingThreshold: BigDecimal?,
            minDeliveryDays: Int,
            maxDeliveryDays: Int,
            sellerId: Long,
            isDefault: Boolean = false,
            canPickup: Boolean = false,
            internationalShipping: Boolean = false,
            restrictedAreas: List<String> = emptyList()
        ): ShippingPolicy {
            return ShippingPolicy(
                id = null,
                name = PolicyName(name),
                shippingFee = Fee(shippingFee),
                freeShippingThreshold = freeShippingThreshold,
                deliveryDays = DeliveryDays(minDeliveryDays, maxDeliveryDays),
                sellerId = sellerId,
                isDefault = isDefault,
                canPickup = canPickup,
                internationalShipping = internationalShipping,
                restrictedAreas = restrictedAreas
            )
        }

        fun reconstitute(
            id: Long,
            name: String,
            shippingFee: BigDecimal,
            freeShippingThreshold: BigDecimal?,
            minDeliveryDays: Int,
            maxDeliveryDays: Int,
            sellerId: Long,
            isDefault: Boolean,
            canPickup: Boolean,
            internationalShipping: Boolean,
            restrictedAreas: List<String>
        ): ShippingPolicy {
            return ShippingPolicy(
                id = id,
                name = PolicyName(name),
                shippingFee = Fee(shippingFee),
                freeShippingThreshold = freeShippingThreshold,
                deliveryDays = DeliveryDays(minDeliveryDays, maxDeliveryDays),
                sellerId = sellerId,
                isDefault = isDefault,
                canPickup = canPickup,
                internationalShipping = internationalShipping,
                restrictedAreas = restrictedAreas
            )
        }
    }

    // Value Objects
    data class PolicyName(val value: String) {
        init {
            require(value.isNotBlank()) { "정책 이름은 비어있을 수 없습니다." }
            require(value.length <= 50) { "정책 이름은 50자를 초과할 수 없습니다." }
        }
    }

    data class Fee(val value: BigDecimal) {
        init {
            require(value >= BigDecimal.ZERO) { "배송비는 0 이상이어야 합니다." }
        }

        fun isFree(): Boolean = value.compareTo(BigDecimal.ZERO) == 0
    }

    data class DeliveryDays(val min: Int, val max: Int) {
        init {
            require(min >= 0) { "최소 배송일은 0 이상이어야 합니다." }
            require(max >= min) { "최대 배송일은 최소 배송일 이상이어야 합니다." }
        }

        override fun toString(): String = if (min == max) "${min}일" else "${min}-${max}일"
    }

    // 비즈니스 메소드
    fun getId(): Long? = id
    fun getName(): PolicyName = name
    fun getShippingFee(): Fee = shippingFee
    fun getFreeShippingThreshold(): BigDecimal? = freeShippingThreshold
    fun getDeliveryDays(): DeliveryDays = deliveryDays
    fun getSellerId(): Long = sellerId
    fun isDefault(): Boolean = isDefault
    fun canPickup(): Boolean = canPickup
    fun supportsInternationalShipping(): Boolean = internationalShipping
    fun getRestrictedAreas(): List<String> = restrictedAreas

    // 비즈니스 로직
    fun calculateShippingFee(orderAmount: BigDecimal): BigDecimal {
        return if (freeShippingThreshold != null && orderAmount >= freeShippingThreshold) {
            BigDecimal.ZERO
        } else {
            shippingFee.value
        }
    }

    fun canShipTo(area: String): Boolean {
        return !restrictedAreas.contains(area)
    }
} 