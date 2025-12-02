package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductImageQueryUseCase;
import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageQueryService implements ProductImageQueryUseCase {

  private final ProductImageQueryPort productImageQueryPort;
  private final ProductMapper productMapper;

  public List<ProductImageOutput> getImagesByProductItemId(Long productItemId) {
    return productImageQueryPort.findImagesByProductItemId(productItemId).stream()
        .map(productMapper::toProductImageOutput)
        .toList();
  }
}
