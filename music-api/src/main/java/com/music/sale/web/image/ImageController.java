package com.music.sale.web.image;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    /**
     * 상품 이미지 업로드 (consumes는 기본 적용이지만 명시함으로서 동일 주소의 요청(json, form 등) 구분 가능)
     * @param productId 상품 ID
     * @param files     업로드할 이미지 파일 리스트
     * @return ResponseEntity
     */
    @PostMapping(value = "/api/v1/products/{productId}/images", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImages(
        @PathVariable Long productId,
        @RequestPart("files") List<MultipartFile> files) {
        // 파일 처리 로직
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
