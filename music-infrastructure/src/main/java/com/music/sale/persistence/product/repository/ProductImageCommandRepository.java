package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageCommandRepository extends JpaRepository<ProductImageEntity, Long> {

    boolean existsByIdAndProductId(Long id, Long productId);
}
