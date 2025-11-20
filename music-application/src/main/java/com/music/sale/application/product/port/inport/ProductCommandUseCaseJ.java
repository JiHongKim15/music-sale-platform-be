package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInput;

public interface ProductCommandUseCaseJ {
    ProductOutputJ createProduct(CreateProductInput input, Long currentUserId);
    ProductOutputJ updateProduct(Long productId, UpdateProductInput input, Long currentUserId);
    void deleteProduct(Long productId, Long currentUserId);
}
