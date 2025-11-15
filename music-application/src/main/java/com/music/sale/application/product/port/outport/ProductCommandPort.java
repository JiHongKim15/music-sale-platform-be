package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductItem;

public interface ProductCommandPort {
    ProductItem save(ProductItem productItem);
    ProductItem update(ProductItem productItem);
    void deleteById(Long productId);
    boolean existsByNameAndSellerId(String name, Long sellerId);
}
