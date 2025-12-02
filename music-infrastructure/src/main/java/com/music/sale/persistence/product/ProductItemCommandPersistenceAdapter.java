package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import com.music.sale.persistence.product.mapper.ProductItemPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductItemCommandPersistenceAdapter implements ProductCommandPort {

  private final ProductItemCommandRepository productItemCommandRepository;
  private final ProductItemPersistenceMapper mapper;

  @Override
  public ProductItem saveProduct(ProductItem item) {
    ProductItemEntity entity = mapper.toEntity(item);
    ProductItemEntity saved = productItemCommandRepository.save(entity);
    return mapper.toDomain(saved);
  }

  @Override
  public ProductItem updateProduct(ProductItem productItem) {
    ProductItemEntity entity = mapper.toEntity(productItem);
    ProductItemEntity saved = productItemCommandRepository.save(entity);
    return mapper.toDomain(saved);
  }

  @Override
  public void deleteByProductId(Long id) {
    productItemCommandRepository.deleteById(id);
  }
}
