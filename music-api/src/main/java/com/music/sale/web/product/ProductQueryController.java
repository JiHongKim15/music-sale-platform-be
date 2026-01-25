package com.music.sale.web.product;

import com.music.sale.application.product.port.inport.ProductQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductWebMapper;
import com.music.sale.web.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductQueryController {

  private final ProductQueryUseCase productQueryUseCase;
  private final ProductWebMapper productWebMapper;

  @Operation(summary = "상품 상세 조회", description = "상품 ID로 상품 상세 정보를 조회합니다.")
  @GetMapping("/{productId}")
  public ApiResponse<ProductResponse> getByProductId(@PathVariable Long productId) {
    ProductResponse response =
        productWebMapper.toProductResponse(productQueryUseCase.getByProductId(productId));
    return ApiResponse.success(response);
  }

  @Operation(summary = "전체 상품 조회", description = "모든 상품을 페이징하여 조회합니다.")
  @GetMapping
  public ApiResponse<Page<ProductResponse>> getAll(Pageable pageable) {
    Page<ProductResponse> page =
        productQueryUseCase.getAll(pageable).map(productWebMapper::toProductResponse);
    return ApiResponse.success(page);
  }

  @Operation(summary = "판매자별 상품 조회", description = "특정 판매자의 상품을 페이징하여 조회합니다.")
  @GetMapping("/seller/{sellerId}")
  public ApiResponse<Page<ProductResponse>> getBySellerId(
      @PathVariable Long sellerId, Pageable pageable) {
    Page<ProductResponse> page =
        productQueryUseCase
            .getBySellerId(sellerId, pageable)
            .map(productWebMapper::toProductResponse);
    return ApiResponse.success(page);
  }

  @Operation(summary = "스토어별 상품 조회", description = "특정 스토어의 상품을 페이징하여 조회합니다.")
  @GetMapping("/store/{storeId}")
  public ApiResponse<Page<ProductResponse>> getByStoreId(
      @PathVariable Long storeId, Pageable pageable) {
    Page<ProductResponse> page =
        productQueryUseCase
            .getByStoreId(storeId, pageable)
            .map(productWebMapper::toProductResponse);
    return ApiResponse.success(page);
  }

  @Operation(summary = "상품 검색", description = "키워드로 상품을 검색하여 페이징하여 조회합니다.")
  @GetMapping("/search")
  public ApiResponse<Page<ProductResponse>> searchByKeyword(
      @RequestParam String keyword, Pageable pageable) {
    Page<ProductResponse> page =
        productQueryUseCase
            .searchByKeyword(keyword, pageable)
            .map(productWebMapper::toProductResponse);
    return ApiResponse.success(page);
  }
}
