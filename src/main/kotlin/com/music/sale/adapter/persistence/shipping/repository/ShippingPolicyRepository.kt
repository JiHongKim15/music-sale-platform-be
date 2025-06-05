package com.music.sale.adapter.persistence.shipping.repository

import com.music.sale.adapter.persistence.shipping.entity.ShippingPolicyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingPolicyRepository : JpaRepository<ShippingPolicyEntity, Long> {

    // 판매자 ID로 배송 정책 목록 조회
    fun findBySellerId(sellerId: Long): List<ShippingPolicyEntity>

    // 판매자의 기본 배송 정책 조회
    fun findBySellerIdAndIsDefaultTrue(sellerId: Long): ShippingPolicyEntity?

    // 배송 정책 이름과 판매자 ID로 조회
    fun findByNameAndSellerId(name: String, sellerId: Long): ShippingPolicyEntity?
} 