package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageQueryRepository extends JpaRepository<ProductImageEntity, Long> {

  List<ProductImageEntity> findByProductItemIdOrderByImageOrderAsc(Long productItemId);
}
