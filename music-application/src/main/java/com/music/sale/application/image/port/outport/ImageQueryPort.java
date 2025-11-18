package com.music.sale.application.image.port.outport;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import java.util.List;

public interface ImageQueryPort {

    /**
     * productId로 이미지 조회 (imageOrder 오름차순)
     */
    List<ImageQueryOutPut> findImagesByProductId(Long ProductId);
}
