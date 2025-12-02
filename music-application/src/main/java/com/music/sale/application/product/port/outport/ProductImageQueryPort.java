package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductImage;
import java.util.List;

public interface ProductImageQueryPort {

  List<ProductImage> findImagesByProductItemId(Long productItemId);
}
