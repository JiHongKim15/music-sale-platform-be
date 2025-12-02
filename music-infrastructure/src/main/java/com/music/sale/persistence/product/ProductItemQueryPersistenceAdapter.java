package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductQueryPort;
import com.music.sale.domain.product.ProductItem;
import com.music.sale.domain.product.enums.ProductStatus;
import com.music.sale.persistence.product.mapper.ProductItemPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductItemQueryPersistenceAdapter implements ProductQueryPort {

  private final ProductItemQueryRepository productItemQueryRepository;
  private final ProductItemPersistenceMapper mapper;

  @Override
  public Optional<ProductItem> findByProductId(Long id) {
    return productItemQueryRepository.findById(id).map(mapper::toDomain);
  }

  @Override
  public Page<ProductItem> findByStatus(ProductStatus status, Pageable pageable) {
    return productItemQueryRepository.findByStatus(status, pageable).map(mapper::toDomain);
  }

  @Override
  public Page<ProductItem> findBySellerId(Long sellerId, Pageable pageable) {
    return productItemQueryRepository.findBySellerId(sellerId, pageable).map(mapper::toDomain);
  }

  @Override
  public Page<ProductItem> findByStoreId(Long storeId, Pageable pageable) {
    return productItemQueryRepository.findByStoreId(storeId, pageable).map(mapper::toDomain);
  }

  @Override
  public Page<ProductItem> searchByKeyword(String keyword, Pageable pageable) {
    return productItemQueryRepository
        .findByNameContainingIgnoreCase(keyword, pageable)
        .map(mapper::toDomain);
  }
}
