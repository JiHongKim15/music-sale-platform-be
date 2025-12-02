package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.exception.ProductImageErrorCode;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductImageCommandUseCase;
import com.music.sale.application.product.port.outport.ProductImageCommandPort;
import com.music.sale.common.BusinessException;
import com.music.sale.domain.product.ProductImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageCommandService implements ProductImageCommandUseCase {

  private final ProductImageCommandPort productImageCommandPort;
  private final ProductMapper productMapper;

  @Override
  public List<ProductImageOutput> uploadImage(List<UpdateProductImageInput> inputs) {
    List<ProductImage> imagesToSave =
        inputs.stream().map(this::toDomain).collect(Collectors.toList());

    List<ProductImage> savedImages = productImageCommandPort.saveAll(imagesToSave);

    return zipToOutput(savedImages, inputs);
  }

  private ProductImage toDomain(UpdateProductImageInput input) {
    String url = productImageCommandPort.generateUrl(input.productId(), input.fileName());
    return ProductImage.builder()
        .productId(input.productId())
        .url(url)
        .isThumbnail(input.isThumbnail())
        .imageOrder(input.imageOrder())
        .fileName(input.fileName())
        .fileType(input.fileType())
        .fileSize(input.fileSize())
        .build();
  }

  private List<ProductImageOutput> zipToOutput(
      List<ProductImage> savedImages, List<UpdateProductImageInput> inputs) {
    if (savedImages.size() != inputs.size()) {
      throw new IllegalStateException("저장된 이미지 수와 요청 수가 일치하지 않습니다.");
    }

    return IntStream.range(0, savedImages.size())
        .mapToObj(i -> productMapper.toProductImageOutput(savedImages.get(i), inputs.get(i)))
        .toList();
  }

  @Transactional
  public void deleteImage(Long productItemId, Long imageId) {
    if (!productImageCommandPort.existsByIdAndProductItemId(imageId, productItemId)) {
      throw new BusinessException(ProductImageErrorCode.IMAGE_NOT_FOUND);
    }

    productImageCommandPort.deleteById(imageId);
  }
}
