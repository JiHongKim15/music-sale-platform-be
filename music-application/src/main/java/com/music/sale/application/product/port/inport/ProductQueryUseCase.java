package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.ProductOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryUseCase {
    ProductOutput getById(Long productId);
    Page<ProductOutput> getBySeller(Long sellerId, Pageable pageable);
    Page<ProductOutput> getByStore(Long storeId, Pageable pageable);
    Page<ProductOutput> searchByName(String keyword, Pageable pageable);
}
