package com.music.sale.web.product;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.application.product.port.inport.ProductCommandUseCaseJ;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.command.CreateProductCommand;
import com.music.sale.web.product.command.UpdateProductCommand;
import com.music.sale.web.product.mapper.ProductWebMapper;
import com.music.sale.web.product.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    private final ProductCommandUseCaseJ productCommandUseCaseJ;
    private final ProductWebMapper productWebMapper;

    // 상품 등록
    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid CreateProductCommand command) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정

        CreateProductInput input = productWebMapper.toCreateInput(command);
        ProductOutputJ output = productCommandUseCaseJ.createProduct(input, currentUserId);

        ProductResponse response = productWebMapper.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_CREATED");
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody @Valid UpdateProductCommand command) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정

        UpdateProductInput input = productWebMapper.toUpdateInput(command);
        ProductOutputJ output = productCommandUseCaseJ.updateProduct(productId, input, currentUserId);

        ProductResponse response = productWebMapper.toResponse(output);
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
