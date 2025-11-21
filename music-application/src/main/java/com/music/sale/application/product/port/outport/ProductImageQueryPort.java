package com.music.sale.application.product.port.outport;

import java.util.List;

public interface ProductImageQueryPort {

    /**
     * productId로 이미지 조회 (imageOrder 오름차순)
     */
    List<ImageQueryOutPut> findImagesByProductId(Long ProductId);
}
