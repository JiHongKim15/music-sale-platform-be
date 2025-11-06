package com.music.sale.web.image;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @PostMapping(value = "/api/v1/products/{productId}/images", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImages(
        @PathVariable Long productId,
        @RequestPart("files") List<MultipartFile> files) {
        // 파일 처리 로직
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
