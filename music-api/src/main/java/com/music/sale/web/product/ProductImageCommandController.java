package com.music.sale.web.product;

import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.port.inport.ProductImageCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductImageWebMapper;
import com.music.sale.web.product.request.ProductImageUploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Image", description = "상품 이미지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductImageCommandController {

  private final ProductImageWebMapper productImageWebMapper;
  private final ProductImageCommandUseCase productImageCommandUseCase;

  @Operation(summary = "상품 이미지 업로드", description = "상품에 이미지를 업로드합니다.")
  @PostMapping(value = "/{productId}/images", consumes = "multipart/form-data")
  public ApiResponse<List<ProductImageOutput>> uploadImages(
      @PathVariable Long productId, @ModelAttribute @Valid ProductImageUploadRequest request) {
    List<UpdateProductImageInput> inputs =
        productImageWebMapper.toUploadImageInputs(productId, request.files(), request.metas());
    List<ProductImageOutput> outputs = productImageCommandUseCase.uploadImage(inputs);
    return ApiResponse.success(outputs);
  }

  @Operation(summary = "상품 이미지 삭제", description = "상품의 이미지를 삭제합니다.")
  @DeleteMapping("/{productId}/images/{imageId}")
  public ApiResponse<Void> deleteImage(@PathVariable Long productId, @PathVariable Long imageId) {
    productImageCommandUseCase.deleteImage(productId, imageId);
    return ApiResponse.success();
  }
}
