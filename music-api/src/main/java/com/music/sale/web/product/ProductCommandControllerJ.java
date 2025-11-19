package com.music.sale.web.product;

import com.music.sale.application.product.dto.CreateProductInputJ;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInputJ;
import com.music.sale.application.product.port.inport.ProductCommandUseCaseJ;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.command.CreateProductCommandJ;
import com.music.sale.web.product.command.UpdateProductCommandJ;
import com.music.sale.web.product.mapper.ProductWebMapperJ;
import com.music.sale.web.product.response.ProductResponseJ;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductCommandControllerJ {

    private final ProductCommandUseCaseJ productCommandUseCaseJ;
    private final ProductWebMapperJ productWebMapperJ;

    // 상품 등록
    @PostMapping
    public ApiResponse<ProductResponseJ> createProduct(@RequestBody @Valid CreateProductCommandJ command) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정

        CreateProductInputJ input = productWebMapperJ.toCreateInput(command);
        ProductOutputJ output = productCommandUseCaseJ.createProduct(input, currentUserId);

        ProductResponseJ response = productWebMapperJ.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_CREATED");
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponseJ> updateProduct(@PathVariable Long productId, @RequestBody @Valid UpdateProductCommandJ command) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정

        UpdateProductInputJ input = productWebMapperJ.toUpdateInput(command);
        ProductOutputJ output = productCommandUseCaseJ.updateProduct(productId, input, currentUserId);

        ProductResponseJ response = productWebMapperJ.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_UPDATED");
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long productId) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정
        productCommandUseCaseJ.deleteProduct(productId, currentUserId);
        return ApiResponse.success(null, "PRODUCT_DELETED");
    }
}
