package com.music.sale.web.product;

import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.port.inport.ProductQueryUseCaseJ;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductWebMapperJ;
import com.music.sale.web.product.response.ProductResponseJ;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductQueryControllerJ {

    private final ProductQueryUseCaseJ productQueryUseCaseJ;
    private final ProductWebMapperJ productWebMapperJ;

    // 단건 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponseJ> getByProductId(@PathVariable Long productId) {
        ProductOutputJ output = productQueryUseCaseJ.getByProductId(productId);
        ProductResponseJ response = productWebMapperJ.toResponse(output);
        return ApiResponse.success(response, "PRODUCT_FOUND");
    }

    // 판매자 기준 목록
    @GetMapping("/seller/{sellerId}")
    public ApiResponse<Page<ProductResponseJ>> getBySellerId(@PathVariable Long sellerId, Pageable pageable) {
        Page<ProductResponseJ> page = productQueryUseCaseJ.getBySellerId(sellerId, pageable)
                .map(productWebMapperJ::toResponse);
        return ApiResponse.success(page, "PRODUCT_LIST_BY_SELLER");
    }

    // 스토어 기준 목록
    @GetMapping("/store/{storeId}")
    public ApiResponse<Page<ProductResponseJ>> getByStoreId(@PathVariable Long storeId, Pageable pageable) {
        Page<ProductResponseJ> page = productQueryUseCaseJ.getByStoreId(storeId, pageable)
                .map(productWebMapperJ::toResponse);
        return ApiResponse.success(page, "PRODUCT_LIST_BY_STORE");
    }

    // 이름 검색
    @GetMapping("/search")
    public ApiResponse<Page<ProductResponseJ>> searchByKeyword(@RequestParam String keyword, Pageable pageable) {
        Page<ProductResponseJ> page = productQueryUseCaseJ.searchByKeyword(keyword, pageable)
                .map(productWebMapperJ::toResponse);
        return ApiResponse.success(page, "PRODUCT_SEARCH_RESULT");
    }
}
