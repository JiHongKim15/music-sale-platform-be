package com.music.sale.web.product;

import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.port.inport.ProductImageCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.product.mapper.ProductImageWebMapper;
import com.music.sale.web.product.request.ProductImageUploadRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductImageCommandController {

  private final ProductImageWebMapper productImageWebMapper;
  private final ProductImageCommandUseCase productImageCommandUseCase;

  @PostMapping(value = "/{productId}/images", consumes = "multipart/form-data")
  public ApiResponse<List<ProductImageOutput>> uploadImages(
      @PathVariable Long productId, @ModelAttribute @Valid ProductImageUploadRequest request) {

    List<UpdateProductImageInput> inputs =
        productImageWebMapper.toUploadImageInputs(productId, request.files(), request.metas());
    List<ProductImageOutput> outputs = productImageCommandUseCase.uploadImage(inputs);

    return ApiResponse.success(outputs);
  }

  @DeleteMapping("/{productId}/images/{imageId}")
  public ApiResponse<Void> deleteImage(@PathVariable Long productId, @PathVariable Long imageId) {

    productImageCommandUseCase.deleteImage(productId, imageId);
    return ApiResponse.success();
  }

  // TODO: 실제 상품 서비스와 연동하여 권한 체크 로직 구현 필요 -> 서비스단에서 처리 후 호출 예정

}
