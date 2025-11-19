// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product.repository

import com.music.sale.persistence.product.entity.ProductCatalogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCatalogCommandJpaRepository : JpaRepository<ProductCatalogEntity, Long> {
    // Command 작업에 필요한 기본적인 메서드들만 유지
    // 복잡한 검색은 Query용 Repository에서 처리
}
