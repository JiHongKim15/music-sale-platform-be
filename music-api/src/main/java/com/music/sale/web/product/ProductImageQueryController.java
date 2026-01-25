package com.music.sale.web.product;

import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.port.inport.ProductImageQueryUseCase;
import com.music.sale.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product Image", description = "상품 이미지 API")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductImageQueryController {

  private final ProductImageQueryUseCase productImageQueryUseCase;

  @Operation(summary = "상품 이미지 목록 조회", description = "상품의 모든 이미지를 조회합니다.")
  @GetMapping("/{productId}/images")
  public ApiResponse<List<ProductImageOutput>> getImages(@PathVariable Long productId) {
    List<ProductImageOutput> outputs = productImageQueryUseCase.getImagesByProductItemId(productId);
    return ApiResponse.success(outputs);
  }
}
