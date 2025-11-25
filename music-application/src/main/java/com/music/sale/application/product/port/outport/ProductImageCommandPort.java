package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductImage;

import java.util.List;

public interface ProductImageCommandPort {

    String generateUrl(Long productId, String fileName);

    List<ProductImage> saveAll(List<ProductImage> images);

    boolean existsByIdAndProductId(Long imageId, Long productId);

    void deleteById(Long imageId);

}
