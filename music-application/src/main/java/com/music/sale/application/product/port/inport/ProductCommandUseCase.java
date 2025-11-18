package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;

public interface ProductCommandUseCase {
    ProductOutput create(CreateProductInput input, Long currentUserId);
    ProductOutput update(Long productId, UpdateProductInput input, Long currentUserId);
    void delete(Long productId, Long currentUserId);
}
