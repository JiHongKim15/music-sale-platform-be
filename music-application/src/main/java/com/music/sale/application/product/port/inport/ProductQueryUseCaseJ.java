package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.ProductOutputJ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryUseCaseJ {
    ProductOutputJ getByProductId(Long productId);
    Page<ProductOutputJ> getBySellerId(Long sellerId, Pageable pageable);
    Page<ProductOutputJ> getByStoreId(Long storeId, Pageable pageable);
    Page<ProductOutputJ> searchByKeyword(String keyword, Pageable pageable);
}
