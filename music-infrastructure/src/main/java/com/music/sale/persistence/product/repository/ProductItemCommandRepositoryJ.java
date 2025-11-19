package com.music.sale.persistence.product.repository;

import com.music.sale.persistence.product.entity.ProductItemEntityJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemCommandRepositoryJ extends JpaRepository<ProductItemEntityJ, Long> {
}
