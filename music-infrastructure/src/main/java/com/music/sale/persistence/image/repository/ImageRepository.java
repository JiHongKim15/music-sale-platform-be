package com.music.sale.persistence.image.repository;

import com.music.sale.persistence.image.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ProductImageEntity, Long> {

    boolean existsByIdAndProductId(Long id, Long productId);
    List<ProductImageEntity> findAllByProductIdOrderByImageOrderAsc(Long productId);
}
