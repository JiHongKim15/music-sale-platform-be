package com.music.sale.web.product;

import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.application.product.port.inport.ProductQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductWebMapper;
import com.music.sale.web.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductQueryController {

    private final ProductQueryUseCase productQueryUseCase;
    private final ProductWebMapper productWebMapper;

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getByProductId(@PathVariable Long productId) {
        ProductOutput output = productQueryUseCase.getByProductId(productId);
        ProductResponse response = productWebMapper.toProductResponse(output);
        return ApiResponse.success(response);
    }

    @GetMapping("/seller/{sellerId}")
    public ApiResponse<Page<ProductResponse>> getBySellerId(@PathVariable Long sellerId, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.getBySellerId(sellerId, pageable)
                .map(productWebMapper::toProductResponse);
        return ApiResponse.success(page);
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<Page<ProductResponse>> getByStoreId(@PathVariable Long storeId, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.getByStoreId(storeId, pageable)
                .map(productWebMapper::toProductResponse);
        return ApiResponse.success(page);
    }

    @GetMapping("/search")
    public ApiResponse<Page<ProductResponse>> searchByKeyword(@RequestParam String keyword, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.searchByKeyword(keyword, pageable)
                .map(productWebMapper::toProductResponse);
        return ApiResponse.success(page);
    }
}
