package com.music.sale.persistence.store.repository;

import com.music.sale.persistence.store.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    // 판매자 ID로 상점 찾기
    List<StoreEntity> findBySellerId(Long sellerId);

    // 상점 상태별 조회
    Page<StoreEntity> findByStatus(StoreEntity.StoreStatus status, Pageable pageable);

    // 상점명 검색 (부분 일치)
    Page<StoreEntity> findByNameContaining(String name, Pageable pageable);

    // 주소로 상점 검색 (부분 일치)
    Page<StoreEntity> findByBaseAddressContaining(String address, Pageable pageable);

    // 위치 기반 상점 검색 (반경 내 상점 찾기)
    @Query("""
        SELECT s FROM StoreEntity s
        WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *
               cos(radians(s.longitude) - radians(:longitude)) +
               sin(radians(:latitude)) * sin(radians(s.latitude)))) <= :distance
        AND s.status = com.music.sale.persistence.store.entity.StoreEntity$StoreStatus.ACTIVE
    """)
    Page<StoreEntity> findStoresWithinDistance(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("distance") Double distanceInKm,
            Pageable pageable
    );

    // 다양한 조건으로 상점 검색
    @Query("""
        SELECT s FROM StoreEntity s
        WHERE (:keyword IS NULL OR
               s.name LIKE %:keyword% OR
               s.description LIKE %:keyword% OR
               s.baseAddress LIKE %:keyword%)
        AND s.status = com.music.sale.persistence.store.entity.StoreEntity$StoreStatus.ACTIVE
    """)
    Page<StoreEntity> searchStores(@Param("keyword") String keyword, Pageable pageable);
}