package com.music.sale.web.product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.sale.application.product.port.inport.ProdcutImageQueryUseCase;
import com.music.sale.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductImageQueryController {

    private final ProdcutImageQueryUseCase prodcutImageQueryUseCase;

    @GetMapping("/{productId}/images")
    public ApiResponse<List<ImageQueryOutPut>> getImages(@PathVariable Long productId) {
        List<ImageQueryOutPut> outputs = prodcutImageQueryUseCase.getImagesByProductId(productId);
        return ApiResponse.success(outputs, "이미지 조회 성공");
    }

}

