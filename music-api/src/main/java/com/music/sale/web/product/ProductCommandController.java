package com.music.sale.web.product;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.application.product.port.inport.ProductCommandUseCase;
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

    private final ProductCommandUseCase productCommandUseCase;
    private final ProductWebMapper productWebMapper;

    // 상품 등록
    @PostMapping
    public ApiResponse<ProductResponse> create(@RequestBody @Valid CreateProductCommand command) {
        Long currentUserId = command.getSellerId();

        CreateProductInput input = productWebMapper.toCreateInput(command);
        ProductOutput output = productCommandUseCase.create(input, currentUserId);

        ProductResponse response = productWebMapper.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_CREATED");
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> update(@PathVariable Long productId, @RequestBody @Valid UpdateProductCommand command) {
        Long currentUserId = 1L; // TODO: Session에서 가져오도록 수정 예정

        UpdateProductInput input = productWebMapper.toUpdateInput(command);
        ProductOutput output = productCommandUseCase.update(productId, input, currentUserId);

        ProductResponse response = productWebMapper.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_UPDATED");
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ApiResponse<Void> delete(@PathVariable Long productId, @RequestParam Long currentUserId) {
        productCommandUseCase.delete(productId, currentUserId);
        return ApiResponse.success(null, "PRODUCT_DELETED");
    }
}
