package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemCommandRepository extends JpaRepository<ProductItemEntity, Long> {}
