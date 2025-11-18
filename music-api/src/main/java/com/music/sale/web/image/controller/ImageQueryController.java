package com.music.sale.web.image.controller;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import com.music.sale.application.image.port.inport.ImageQueryUseCase;
import com.music.sale.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ImageQueryController {

    private final ImageQueryUseCase imageQueryUseCase;

    /**
     * 상품의 이미지 전체 목록 조회
     * @param productId
     * @return List<ImageQueryOutPut>
     */
    @GetMapping("/{productId}/images")
    public ApiResponse<List<ImageQueryOutPut>> getImages(@PathVariable Long productId) {
        List<ImageQueryOutPut> outputs = imageQueryUseCase.getImagesByProductId(productId);
        return ApiResponse.success(outputs, "이미지 조회 성공");
    }

}


