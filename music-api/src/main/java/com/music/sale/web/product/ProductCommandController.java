package com.music.sale.web.product;

import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.application.auth.security.CurrentUser;
import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.input.UpdateProductInput;
import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.application.product.port.inport.ProductCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductWebMapper;
import com.music.sale.web.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductCommandController {

  private final ProductCommandUseCase productCommandUseCase;
  private final ProductWebMapper productWebMapper;

  @Operation(summary = "상품 등록", description = "새로운 상품을 등록합니다.")
  @PostMapping
  public ApiResponse<ProductResponse> createProduct(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @RequestBody @Valid CreateProductInput input) {
    ProductOutput output = productCommandUseCase.createProduct(input, user.userId());
    ProductResponse response = productWebMapper.toProductResponse(output);
    return ApiResponse.success(response);
  }

  @Operation(summary = "상품 수정", description = "기존 상품 정보를 수정합니다.")
  @PutMapping("/{productId}")
  public ApiResponse<ProductResponse> updateProduct(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @PathVariable Long productId,
      @RequestBody @Valid UpdateProductInput input) {
    ProductOutput output = productCommandUseCase.updateProduct(productId, input, user.userId());
    ProductResponse response = productWebMapper.toProductResponse(output);
    return ApiResponse.success(response);
  }

  @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
  @DeleteMapping("/{productId}")
  public ApiResponse<Void> deleteProduct(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user, @PathVariable Long productId) {
    productCommandUseCase.deleteProduct(productId, user.userId());
    return ApiResponse.success();
  }
}
