package com.music.sale.adapter.persistence.store.repository

import com.music.sale.adapter.persistence.store.entity.StoreEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<StoreEntity, Long> {

    // 판매자 ID로 상점 찾기
    fun findBySellerId(sellerId: Long): List<StoreEntity>

    // 상점 상태별 조회
    fun findByStatus(status: StoreEntity.StoreStatus, pageable: Pageable): Page<StoreEntity>

    // 상점명 검색 (부분 일치)
    fun findByNameContaining(name: String, pageable: Pageable): Page<StoreEntity>

    // 주소로 상점 검색 (부분 일치)
    fun findByBaseAddressContaining(address: String, pageable: Pageable): Page<StoreEntity>

    // 위치 기반 상점 검색 (반경 내 상점 찾기)
    @Query(
        """
        SELECT s FROM StoreEntity s
        WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * 
               cos(radians(s.longitude) - radians(:longitude)) + 
               sin(radians(:latitude)) * sin(radians(s.latitude)))) <= :distance
        AND s.status = com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus.ACTIVE
    """
    )
    fun findStoresWithinDistance(
        @Param("latitude") latitude: Double,
        @Param("longitude") longitude: Double,
        @Param("distance") distanceInKm: Double,
        pageable: Pageable
    ): Page<StoreEntity>

    // 다양한 조건으로 상점 검색
    @Query(
        """
        SELECT s FROM StoreEntity s
        WHERE (:keyword IS NULL OR 
               s.name LIKE %:keyword% OR 
               s.description LIKE %:keyword% OR 
               s.baseAddress LIKE %:keyword%)
        AND s.status = com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus.ACTIVE
    """
    )
    fun searchStores(
        @Param("keyword") keyword: String?,
        pageable: Pageable
    ): Page<StoreEntity>
} 