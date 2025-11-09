package com.music.sale.web.image;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.image.mapper.ImageWebMapper;
import com.music.sale.web.image.request.ImageMetaRequest;
import com.sun.security.auth.UserPrincipal;
//import com.music.sale.security.UserPrincipal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private static final int MAX_IMAGE_FILES = 5;
    private final ImageWebMapper imageWebMapper;
    private final ImageUseCase imageUseCase;

    /**
     * 상품 이미지 업로드
     * @param productId
     * @param files
     * @param metas
     * @return
     */
    @PostMapping(value = "/api/v1/products/{productId}/images", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImages(
        @PathVariable Long productId,
        @RequestPart("images") List<MultipartFile> files,
        @RequestPart("meta") List<ImageMetaRequest> metas,
        @AuthenticationPrincipal UserPrincipal loginUser) {
        validateImageFiles(files, metas);
        checkUserAuthorization(loginUser, productId);

        // 1. DTO 변환 및 파일 데이터 추출
        List<UploadImageInput> inputs = imageWebMapper.toUploadImageInputs(productId, files, metas);

        // 2. UseCase 호출 (업로드 및 영속화)
        List<ImageOutput> outputs = imageUseCase.uploadImage(inputs);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(outputs, "이미지 업로드가 완료되었습니다."));
    } // uploadImages

    /**
     * 사용자 권한 검증: 로그인 여부, 상품 등록자 여부
     * @param loginUser
     * @param productId
     */
    private void checkUserAuthorization(UserPrincipal loginUser, Long productId) {
        if (loginUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

//        // 상품 조회
//        Product product = productService.getProductById(productId)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."));
//
//        // 등록자와 현재 로그인 유저 비교
//        if (!product.getCreatedBy().equals(loginUser.getId())) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "상품 이미지를 등록할 권한이 없습니다.");
//        }
    }

     /**
     * 요청 단위 검증: null/빈 체크, 최대 개수 검사, 각 파일의 유효성 검사 호출
     */
    private void validateImageFiles(List<MultipartFile> files, List<ImageMetaRequest> metas) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("이미지를 최소 1개 이상 올려주세요.");
        }
        if (files.size() > MAX_IMAGE_FILES) {
            throw new IllegalArgumentException("이미지는 최대 " + MAX_IMAGE_FILES + "개 까지 올릴 수 있습니다.");
        }
        if (files.size() != metas.size()) {
            throw new IllegalArgumentException("파일과 메타정보 개수가 일치하지 않습니다.");
        }
        for (MultipartFile f : files) {
            validateImageFile(f);
        }
    } // validateImageFiles

    /**
     * 파일 단위 검증: contentType, 크기 등
     */
    private void validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일은 업로드할 수 없습니다.");
        }
        String contentType = file.getContentType();
        if (contentType == null ||
            !(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg"))) {
            throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다. JPEG, PNG만 허용됩니다.");
        }
        if (file.getSize() > 5L * 1024 * 1024) {
            throw new IllegalArgumentException("이미지 파일 크기는 최대 5MB까지 허용됩니다.");
        }
    } // validateImageFile

} // class
