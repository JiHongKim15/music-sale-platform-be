package com.music.sale.web.image.controller;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.image.mapper.ImageWebMapper;
import com.music.sale.web.image.request.ImageUploadRequest;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ImageCommandController {

    private final ImageWebMapper imageWebMapper;
    private final ImageUseCase imageUseCase;

    @PostMapping(value = "/{productId}/images", consumes = "multipart/form-data")
    public ApiResponse<List<ImageOutput>> uploadImages(
        @PathVariable Long productId,
        @ModelAttribute @Valid ImageUploadRequest request) {
        request.validate();

        List<UploadImageInput> inputs = imageWebMapper.toUploadImageInputs(
                productId,
                request.getImages(),
                request.getMeta()
        );
        List<ImageOutput> outputs = imageUseCase.uploadImage(inputs);

        return ApiResponse.success(outputs, "이미지 업로드가 완료되었습니다.");
    }

    @DeleteMapping("/{productId}/images/{imageId}")
    public ApiResponse<Void> deleteImage(
            @PathVariable Long productId,
            @PathVariable Long imageId) {

        imageUseCase.deleteImage(productId, imageId);
        return ApiResponse.success(null, "이미지가 성공적으로 삭제되었습니다.");
    }
    
    // TODO: 실제 상품 서비스와 연동하여 권한 체크 로직 구현 필요 -> 서비스단에서 처리 후 호출 예정

}
