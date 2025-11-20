package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductItem;

public interface ProductCommandPortJ {
    ProductItem saveProduct(ProductItem productItem);
    ProductItem updateProduct(ProductItem productItem);
    void deleteByProductId(Long productId);
}
