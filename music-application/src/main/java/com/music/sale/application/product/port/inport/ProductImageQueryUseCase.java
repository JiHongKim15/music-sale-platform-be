package com.music.sale.application.product.port.inport;

import com.music.sale.application.product.dto.output.ProductImageOutput;

import java.util.List;

public interface ProductImageQueryUseCase {

    /**
     * 상품의 이미지 전체 목록 조회
     */
    List<ProductImageOutput> getImagesByProductItemId(Long productItemId);

}
