package com.music.sale.application.image.port.outport;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import com.music.sale.domain.image.ProductImage;

import java.util.List;

/**
 * 이미지 조회용 포트 (영속성 계층이 구현)
 */
public interface ImageQueryPort {

    /**
     * productId로 이미지 조회 (imageOrder 오름차순)
     */
    List<ImageQueryOutPut> findImagesByProductId(Long ProductId);
}
