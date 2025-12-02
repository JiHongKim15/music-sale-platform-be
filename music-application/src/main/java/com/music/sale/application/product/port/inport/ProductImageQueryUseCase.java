package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.output.ProductImageOutput;
import java.util.List;

public interface ProductImageQueryUseCase {

  List<ProductImageOutput> getImagesByProductItemId(Long productItemId);
}
