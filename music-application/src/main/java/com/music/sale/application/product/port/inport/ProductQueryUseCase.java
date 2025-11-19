package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.ProductOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryUseCase {
    ProductOutput getByProductId(Long productId);
    Page<ProductOutput> getBySellerId(Long sellerId, Pageable pageable);
    Page<ProductOutput> getByStoreId(Long storeId, Pageable pageable);
    Page<ProductOutput> searchByKeyword(String keyword, Pageable pageable);
}
