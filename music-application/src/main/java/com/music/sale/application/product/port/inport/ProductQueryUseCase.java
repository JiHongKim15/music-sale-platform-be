package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.output.GetProductOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryUseCase {
    GetProductOutput getByProductId(Long productId);
    Page<GetProductOutput> getBySellerId(Long sellerId, Pageable pageable);
    Page<GetProductOutput> getByStoreId(Long storeId, Pageable pageable);
    Page<GetProductOutput> searchByKeyword(String keyword, Pageable pageable);
}
