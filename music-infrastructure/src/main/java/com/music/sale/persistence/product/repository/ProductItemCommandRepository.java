package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductItemEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemCommandRepository extends JpaRepository<ProductItemEntity, Long> {
    @Modifying
    @Query("update ProductItemEntity p set p.viewCount = p.viewCount + 1 where p.id = :id")
    void increaseViewCount(@Param("id") Long id);
}
