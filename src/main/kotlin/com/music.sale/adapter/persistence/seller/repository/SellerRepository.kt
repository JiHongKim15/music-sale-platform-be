package com.music.sale.adapter.persistence.seller.repository

import com.music.sale.adapter.persistence.seller.entity.SellerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SellerRepository : JpaRepository<SellerEntity, Long> {

    // 사용자 ID로 판매자 찾기
    fun findByUserId(userId: Long): SellerEntity?

    // 사업자 번호로 판매자 찾기
    fun findByBusinessNumber(businessNumber: String): SellerEntity?

    // 판매자 상태별 조회
    fun findByStatus(status: SellerEntity.SellerStatus, pageable: Pageable): Page<SellerEntity>

    // 회사명으로 판매자 검색 (부분 일치)
    fun findByCompanyNameContaining(companyName: String, pageable: Pageable): Page<SellerEntity>

    // 이메일로 판매자 검색 (부분 일치)
    fun findByContactEmailContaining(email: String, pageable: Pageable): Page<SellerEntity>

    // 전화번호로 판매자 검색
    fun findByContactPhone(phone: String): SellerEntity?
} 