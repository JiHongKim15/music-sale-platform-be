package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import java.util.List;

public interface ProductImageCommandUseCase {
    List<ProductImageOutput> uploadImage(List<UpdateProductImageInput> inputs);
    void deleteImage(Long productId, Long imageId);

}
