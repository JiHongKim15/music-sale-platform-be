package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;

public interface ProductCommandUseCase {
    ProductOutput createProduct(CreateProductInput input, Long currentUserId);
    ProductOutput updateProduct(Long productId, UpdateProductInput input, Long currentUserId);
    void deleteProduct(Long productId, Long currentUserId);
}
