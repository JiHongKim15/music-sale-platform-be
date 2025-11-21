package com.music.sale.application.product.port.outport;

import com.music.sale.application.product.dto.ProductImageSaveResult;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import java.util.List;

public interface ProductImageCommandPort {

    /**
     * 상품 ID와 파일명을 기반으로 외부에서 접근 가능한 URL 문자열 생성 반환.
     * @param productId 연결된 상품 ID
     * @param fileName 원본 파일명
     * @return 파일 접근 URL
     */
    String generateUrl(Long productId, String fileName);

    /**
     * 이미지 저장 후 ID와 URL을 함께 반환
     * @return 저장된 이미지 정보 리스트
     */
    List<ProductImageSaveResult> saveAll(List<UpdateProductImageInput> inputs);

    /**
     * 이미지 ID와 상품 ID로 이미지 존재 여부 확인
     * @param imageId
     * @param productId
     * @return
     */
    boolean existsByIdAndProductId(Long imageId, Long productId);

    /**
     * 이미지 ID로 이미지 삭제
     * @param imageId
     */
    void deleteById(Long imageId);

}
