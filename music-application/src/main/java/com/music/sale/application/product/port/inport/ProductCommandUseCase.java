package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.input.UpdateProductInput;
import com.music.sale.application.product.dto.output.CreateProductOutput;
import com.music.sale.application.product.dto.output.UpdateProductOutput;

public interface ProductCommandUseCase {
    CreateProductOutput createProduct(CreateProductInput input, Long currentUserId);
    UpdateProductOutput updateProduct(Long productId, UpdateProductInput input, Long currentUserId);
    void deleteProduct(Long productId, Long currentUserId);
}
