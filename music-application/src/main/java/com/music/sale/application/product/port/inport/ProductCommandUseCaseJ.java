package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.CreateProductInputJ;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInputJ;

public interface ProductCommandUseCaseJ {
    ProductOutputJ createProduct(CreateProductInputJ input, Long currentUserId);
    ProductOutputJ updateProduct(Long productId, UpdateProductInputJ input, Long currentUserId);
    void deleteProduct(Long productId, Long currentUserId);
}
