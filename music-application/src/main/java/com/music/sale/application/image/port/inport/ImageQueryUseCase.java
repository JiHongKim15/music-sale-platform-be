package com.music.sale.application.image.port.inport;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import java.util.List;

public interface ImageQueryUseCase {

    /**
     * 상품의 이미지 전체 목록 조회
     */
    List<ImageQueryOutPut> getImagesByProductId(Long productId);

}
