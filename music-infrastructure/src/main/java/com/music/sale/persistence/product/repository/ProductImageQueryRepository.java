package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageQueryRepository extends JpaRepository<ProductImageEntity, Long> {

    List<ProductImageEntity> findByProductItemIdOrderByImageOrderAsc(Long productItemId);

}
