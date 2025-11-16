package com.music.sale.web.product;

import com.music.sale.application.product.dto.ProductOutput;
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

    // 단건 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long productId) {
        ProductOutput output = productQueryUseCase.getById(productId);
        ProductResponse response = productWebMapper.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_FOUND");
    }

    // 판매자 기준 목록
    @GetMapping("/seller/{sellerId}")
    public ApiResponse<Page<ProductResponse>> getBySeller(@PathVariable Long sellerId, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.getBySeller(sellerId, pageable)
                .map(productWebMapper::toResponse);
        return ApiResponse.success(page, "PRODUCT_LIST_BY_SELLER");
    }

    // 스토어 기준 목록
    @GetMapping("/store/{storeId}")
    public ApiResponse<Page<ProductResponse>> getByStore(@PathVariable Long storeId, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.getByStore(storeId, pageable)
                .map(productWebMapper::toResponse);
        return ApiResponse.success(page, "PRODUCT_LIST_BY_STORE");
    }

    // 이름 검색
    @GetMapping("/search")
    public ApiResponse<Page<ProductResponse>> searchByName(@RequestParam String keyword, Pageable pageable) {
        Page<ProductResponse> page = productQueryUseCase.searchByName(keyword, pageable)
                .map(productWebMapper::toResponse);
        return ApiResponse.success(page, "PRODUCT_SEARCH_RESULT");
    }
}
