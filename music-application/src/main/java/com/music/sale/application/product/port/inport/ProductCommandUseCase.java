package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.application.product.dto.input.UpdateProductInput;

public interface ProductCommandUseCase {
    ProductOutput createProduct(CreateProductInput input, Long currentUserId);
    ProductOutput updateProduct(Long productId, UpdateProductInput input, Long currentUserId);
    void deleteProduct(Long productId, Long currentUserId);
}
