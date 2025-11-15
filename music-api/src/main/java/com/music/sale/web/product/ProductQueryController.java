package com.music.sale.web.product;

import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.port.inport.ProductQueryUseCase;
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
    public ProductResponse getById(@PathVariable Long productId) {
        ProductOutput output = productQueryUseCase.getById(productId);
        return productWebMapper.toResponse(output);
    }

    // 판매자 기준 목록
    @GetMapping("/seller/{sellerId}")
    public Page<ProductResponse> getBySeller(@PathVariable Long sellerId, Pageable pageable) {
        return productQueryUseCase.getBySeller(sellerId, pageable)
                .map(productWebMapper::toResponse);
    }

    // 스토어 기준 목록
    @GetMapping("/store/{storeId}")
    public Page<ProductResponse> getByStore(@PathVariable Long storeId, Pageable pageable) {
        return productQueryUseCase.getByStore(storeId, pageable)
                .map(productWebMapper::toResponse);
    }

    // 이름 검색
    @GetMapping("/search")
    public Page<ProductResponse> searchByName(@RequestParam String keyword, Pageable pageable) {
        return productQueryUseCase.searchByName(keyword, pageable)
                .map(productWebMapper::toResponse);
    }
}
