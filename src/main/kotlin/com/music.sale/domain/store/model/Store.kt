package com.music.sale.domain.store.model

import java.time.LocalDateTime

/**
 * 상점(매장) 도메인 모델
 */
class Store(
    val id: Long? = null,
    val name: StoreName,
    val description: Description,
    val address: Address,
    val contactNumber: ContactNumber,
    val businessNumber: BusinessNumber,
    val imageUrl: String?,
    val status: StoreStatus,
    val sellerId: Long,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    // Value Objects
    data class StoreName(val value: String) {
        init {
            require(value.isNotBlank()) { "상점명은 비어있을 수 없습니다." }
            require(value.length <= 50) { "상점명은 50자를 초과할 수 없습니다." }
        }
    }

    data class Description(val value: String) {
        init {
            require(value.length <= 1000) { "설명은 1000자를 초과할 수 없습니다." }
        }
    }

    data class Address(
        val zipcode: String,
        val baseAddress: String,
        val detailAddress: String,
        val latitude: Double? = null,
        val longitude: Double? = null
    ) {
        init {
            require(zipcode.isNotBlank()) { "우편번호는 비어있을 수 없습니다." }
            require(baseAddress.isNotBlank()) { "기본 주소는 비어있을 수 없습니다." }
        }
    }

    data class ContactNumber(val value: String) {
        init {
            require(value.isNotBlank()) { "연락처는 비어있을 수 없습니다." }
            require(value.matches(Regex("""^\d{2,3}-\d{3,4}-\d{4}$"""))) { "연락처 형식이 올바르지 않습니다." }
        }
    }

    data class BusinessNumber(val value: String) {
        init {
            require(value.isNotBlank()) { "사업자 번호는 비어있을 수 없습니다." }
            require(value.matches(Regex("""^\d{3}-\d{2}-\d{5}$"""))) { "사업자 번호 형식이 올바르지 않습니다." }
        }
    }

    // 상점 상태 enum
    enum class StoreStatus {
        PENDING,   // 승인 대기 중
        ACTIVE,    // 활성화됨
        SUSPENDED, // 일시 정지됨
        CLOSED     // 폐점
    }

    // 비즈니스 메소드
    fun getName(): StoreName = name
    fun getDescription(): Description = description
    fun getAddress(): Address = address
    fun getContactNumber(): ContactNumber = contactNumber
    fun getBusinessNumber(): BusinessNumber = businessNumber
    fun getStatus(): StoreStatus = status
} 