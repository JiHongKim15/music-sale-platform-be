package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.output.UploadProductImageOutput;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import java.util.List;

public interface ProductImageCommandUseCase {
    List<UploadProductImageOutput> uploadImage(List<UpdateProductImageInput> inputs);
    void deleteImage(Long productId, Long imageId);

}
