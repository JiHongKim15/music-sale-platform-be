package com.music.sale.application.image.port.inport;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import java.util.List;

/**
 * 이미지 조회 Use Case (API 계층이 호출)
 */
public interface ImageQueryUseCase {

    /**
     * 상품의 이미지 전체 목록 조회
     */
    List<ImageQueryOutPut> getImagesByProductId(Long productId);

}
