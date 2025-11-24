package com.music.sale.web.product;

import com.music.sale.application.product.port.inport.ProductImageQueryUseCase;
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
public class ProductImageQueryController {

    private final ProductImageQueryUseCase productImageQueryUseCase;

    @GetMapping("/{productId}/images")
    public ApiResponse<List<ImageQueryOutPut>> getImages(@PathVariable Long productId) {
        List<ImageQueryOutPut> outputs = productImageQueryUseCase.getImagesByProductId(productId);
        return ApiResponse.success(outputs, "이미지 조회 성공");
    }

}

