package com.sodamjae.domain.seller.model

import java.time.LocalDateTime

/**
 * 판매자 도메인 모델
 */
class Seller(
    val id: Long? = null,
    val userId: Long,
    val companyName: CompanyName,
    val businessNumber: BusinessNumber,
    val contactEmail: ContactEmail,
    val contactPhone: ContactPhone,
    val status: SellerStatus,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    // Value Objects
    data class CompanyName(val value: String) {
        init {
            require(value.isNotBlank()) { "회사명은 비어있을 수 없습니다." }
            require(value.length <= 50) { "회사명은 50자를 초과할 수 없습니다." }
        }
    }

    data class BusinessNumber(val value: String) {
        init {
            require(value.isNotBlank()) { "사업자 번호는 비어있을 수 없습니다." }
            require(value.matches(Regex("""^\d{3}-\d{2}-\d{5}$"""))) { "사업자 번호 형식이 올바르지 않습니다." }
        }
    }

    data class ContactEmail(val value: String) {
        init {
            require(value.isNotBlank()) { "이메일은 비어있을 수 없습니다." }
            require(value.matches(Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$"""))) { "이메일 형식이 올바르지 않습니다." }
        }
    }

    data class ContactPhone(val value: String) {
        init {
            require(value.isNotBlank()) { "연락처는 비어있을 수 없습니다." }
            require(value.matches(Regex("""^\d{2,3}-\d{3,4}-\d{4}$"""))) { "연락처 형식이 올바르지 않습니다." }
        }
    }

    // 판매자 상태 enum
    enum class SellerStatus {
        PENDING,   // 승인 대기 중
        APPROVED,  // 승인됨
        REJECTED,  // 거부됨
        SUSPENDED  // 일시 정지됨
    }

    // 비즈니스 메소드
    fun getCompanyName(): CompanyName = companyName
    fun getBusinessNumber(): BusinessNumber = businessNumber
    fun getContactEmail(): ContactEmail = contactEmail
    fun getContactPhone(): ContactPhone = contactPhone
    fun getStatus(): SellerStatus = status
} 