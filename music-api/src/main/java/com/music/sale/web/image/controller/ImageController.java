package com.music.sale.web.image.controller;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.image.mapper.ImageWebMapper;
import com.music.sale.web.image.request.ImageUploadRequest;
import com.sun.security.auth.UserPrincipal;
import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageWebMapper imageWebMapper;
    private final ImageUseCase imageUseCase;

    /**
     * 상품 이미지 업로드
     * @param productId
     * @param request
     * (meta{순서, 썸네일여부}, multipart)
     * @return
     */
    @PostMapping(value = "/api/v1/products/{productId}/images", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<List<ImageOutput>>> uploadImages(
        @PathVariable Long productId,
        @ModelAttribute @Valid ImageUploadRequest request,
        @AuthenticationPrincipal UserPrincipal loginUser) {
        request.validate();
        checkUserAuthorization(loginUser, productId);

        // 1. DTO 변환 및 파일 데이터 추출
        List<UploadImageInput> inputs = imageWebMapper.toUploadImageInputs(
                productId,
                request.getImages(),
                request.getMeta()
        );
        // 2. UseCase 호출 (업로드 및 영속화)
        List<ImageOutput> outputs = imageUseCase.uploadImage(inputs);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(outputs, "이미지 업로드가 완료되었습니다."));
    }

    /**
     * 상품 이미지 삭제
     * @param productId 상품 ID
     * @param imageId 이미지 ID
     * @param loginUser 로그인 사용자
     * @return 삭제 완료 응답
     */
    @DeleteMapping("/api/v1/products/{productId}/images/{imageId}")
    public ResponseEntity<ApiResponse<Void>> deleteImage(
            @PathVariable Long productId,
            @PathVariable Long imageId,
            @AuthenticationPrincipal UserPrincipal loginUser) {

        // 권한 검증 (기존 메서드 재사용)
        checkUserAuthorization(loginUser, productId);

        // 이미지 삭제
        imageUseCase.deleteImage(productId, imageId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(null, "이미지가 성공적으로 삭제되었습니다."));
    }
    
    // TODO: 실제 상품 서비스와 연동하여 권한 체크 로직 구현 필요 -> 서비스단에서 처리 후 호출 예정
    /**
     * 사용자 권한 검증: 로그인 여부, 상품 등록자 여부
     * @param loginUser
     * @param productId
     */
    private void checkUserAuthorization(UserPrincipal loginUser, Long productId) {
        Objects.requireNonNull(loginUser, "로그인 사용자 정보는 필수입니다.");

//        // 상품 조회
//        Product product = productService.getProductById(productId)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."));
//
//        // 등록자와 현재 로그인 유저 비교
//        if (!product.getCreatedBy().equals(loginUser.getId())) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "상품 이미지를 등록할 권한이 없습니다.");
//        }
    }

}
