package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import com.music.sale.domain.product.ProductImage;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import com.music.sale.persistence.product.mapper.ProductImagePersistenceMapper;
import com.music.sale.persistence.product.repository.ProductImageQueryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductImageQueryAdapter implements ProductImageQueryPort {

  private final ProductImageQueryRepository productImageQueryRepository;
  private final ProductImagePersistenceMapper mapper;

  @Override
  public List<ProductImage> findImagesByProductItemId(Long productItemId) {
    List<ProductImageEntity> entities =
        productImageQueryRepository.findByProductItemIdOrderByImageOrderAsc(productItemId);

    return entities.stream().map(mapper::toDomain).collect(Collectors.toList());
  }
}
