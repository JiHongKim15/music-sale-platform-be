package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductImageCommandPort;
import com.music.sale.domain.product.ProductImage;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import com.music.sale.persistence.product.mapper.ProductImagePersistenceMapper;
import com.music.sale.persistence.product.repository.ProductImageCommandRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductImageCommandPersistenceAdapter implements ProductImageCommandPort {

  private final ProductImageCommandRepository productImageCommandRepository;
  private final ProductImagePersistenceMapper mapper;

  @Override
  public String generateUrl(Long productId, String fileName) {
    String safeFileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
    return String.format("https://cdn.music-sale.com/products/%d/%s", productId, safeFileName);
  }

  @Override
  public List<ProductImage> saveAll(List<ProductImage> images) {
    List<ProductImageEntity> entitiesToSave =
        images.stream().map(mapper::toEntity).collect(Collectors.toList());

    List<ProductImageEntity> savedEntities = productImageCommandRepository.saveAll(entitiesToSave);

    return savedEntities.stream().map(mapper::toDomain).collect(Collectors.toList());
  }

  @Override
  public boolean existsByIdAndProductItemId(Long imageId, Long productItemId) {
    return productImageCommandRepository.existsByIdAndProductItemId(imageId, productItemId);
  }

  @Override
  public void deleteById(Long imageId) {
    productImageCommandRepository.deleteById(imageId);
  }
}
