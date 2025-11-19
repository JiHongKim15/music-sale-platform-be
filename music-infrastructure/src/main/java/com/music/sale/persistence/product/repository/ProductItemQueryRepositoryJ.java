package com.music.sale.persistence.product.repository;

import com.music.sale.domain.product.enums.ProductStatusJ;
import com.music.sale.persistence.product.entity.ProductItemEntityJ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemQueryRepositoryJ extends JpaRepository<ProductItemEntityJ, Long> {

    boolean existsByNameAndSellerId(String name, Long sellerId);

    Page<ProductItemEntityJ> findByStatus(ProductStatusJ status, Pageable pageable);

    Page<ProductItemEntityJ> findBySellerId(Long sellerId, Pageable pageable);

    Page<ProductItemEntityJ> findByStoreId(Long storeId, Pageable pageable);

    Page<ProductItemEntityJ> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
