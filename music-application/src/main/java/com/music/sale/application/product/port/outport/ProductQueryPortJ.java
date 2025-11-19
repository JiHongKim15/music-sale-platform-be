package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductItemJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductQueryPortJ {
    Optional<ProductItemJ> findByProductId(Long id);
    Page<ProductItemJ> findByStatus(ProductStatusJ status, Pageable pageable);
    Page<ProductItemJ> findBySellerId(Long sellerId, Pageable pageable);
    Page<ProductItemJ> findByStoreId(Long storeId, Pageable pageable);
    Page<ProductItemJ> searchByKeyword(String keyword, Pageable pageable);
}
