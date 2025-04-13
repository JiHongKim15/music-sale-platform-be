package com.sodamjae.adapter.persistence.shipping.entity

import com.sodamjae.adapter.persistence.common.BaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

/**
 * 배송 정책 JPA 엔티티
 * 여러 상품이 공유할 수 있는 배송 정책 정보를 관리
 */
@Entity
@Table(name = "shipping_policies")
class ShippingPolicyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(nullable = false)
    val name: String,  // 정책 이름 (예: "기본 배송", "프리미엄 배송")
    
    @Column(nullable = false, precision = 10, scale = 2)
    val shippingFee: BigDecimal,  // 기본 배송비
    
    @Column(nullable = true, precision = 10, scale = 2)
    val freeShippingThreshold: BigDecimal?,  // 무료 배송 기준금액
    
    @Column(nullable = false)
    val minDeliveryDays: Int,  // 최소 배송일
    
    @Column(nullable = false)
    val maxDeliveryDays: Int,  // 최대 배송일
    
    @Column(nullable = false)
    val sellerId: Long,  // 판매자 ID (소유자)

    @Column(nullable = false)
    val isDefault: Boolean = false, // 판매자의 기본 정책 여부
    
    @Column(nullable = false)
    val canPickup: Boolean = false, // 매장 픽업 가능 여부
    
    @Column(nullable = false)
    val internationalShipping: Boolean = false, // 해외 배송 가능 여부
    
    @Column(nullable = true, length = 1000)
    val restrictedAreas: String? = null // 쉼표로 구분된 배송 제한 지역 목록
) : BaseEntity() 