package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductItemJ;

public interface ProductCommandPortJ {
    ProductItemJ saveProduct(ProductItemJ productItemJ);
    ProductItemJ updateProduct(ProductItemJ productItemJ);
    void deleteByProductId(Long productId);
}
