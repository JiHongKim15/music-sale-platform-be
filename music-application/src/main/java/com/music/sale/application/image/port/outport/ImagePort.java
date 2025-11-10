package com.music.sale.application.image.port.outport;

import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.domain.image.ProductImage;
import java.util.List;

public interface ImagePort {

    /**
     * 상품 ID와 파일명을 기반으로 외부에서 접근 가능한 URL 문자열 생성 반환.
     * @param productId 연결된 상품 ID
     * @param fileName 원본 파일명
     * @return 파일 접근 URL
     */
    String generateUrl(Long productId, String fileName);

    /**
     * ProductImage 도메인 객체 리스트를 저장하고, 저장된 객체의 ID를 반환.
     * @param images 저장할 도메인 엔티티 리스트
     * @return 저장된 이미지 id 리스트
     */
    List<ProductImage> saveAll(List<UploadImageInput> inputs);


} // class
