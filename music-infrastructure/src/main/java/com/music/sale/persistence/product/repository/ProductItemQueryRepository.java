package com.music.sale.persistence.product.repository;

import com.music.sale.domain.product.enums.ProductStatus;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemQueryRepository extends JpaRepository<ProductItemEntity, Long> {

    boolean existsByNameAndSellerId(String name, Long sellerId);

    Page<ProductItemEntity> findByStatus(ProductStatus status, Pageable pageable);

    Page<ProductItemEntity> findBySellerId(Long sellerId, Pageable pageable);

    Page<ProductItemEntity> findByStoreId(Long storeId, Pageable pageable);

    Page<ProductItemEntity> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
