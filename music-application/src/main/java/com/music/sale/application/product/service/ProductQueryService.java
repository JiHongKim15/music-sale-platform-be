package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductQueryUseCase;
import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.application.product.port.outport.ProductQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService implements ProductQueryUseCase {
  private final ProductQueryPort queryPort;
  private final ProductCommandPort productCommandPort;
  private final ProductMapper mapper;

  @Override
  @Transactional
  public ProductOutput getByProductId(Long productId) {
    return queryPort
        .findByProductId(productId)
        .map(mapper::toOutput)
        .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));
  }

  @Override
  public Page<ProductOutput> getAll(Pageable pageable) {
    return queryPort.findAll(pageable).map(mapper::toOutput);
  }

  @Override
  public Page<ProductOutput> getBySellerId(Long sellerId, Pageable pageable) {
    return queryPort.findBySellerId(sellerId, pageable).map(mapper::toOutput);
  }

  @Override
  public Page<ProductOutput> getByStoreId(Long storeId, Pageable pageable) {
    return queryPort.findByStoreId(storeId, pageable).map(mapper::toOutput);
  }

  @Override
  public Page<ProductOutput> searchByKeyword(String keyword, Pageable pageable) {
    return queryPort.searchByKeyword(keyword, pageable).map(mapper::toOutput);
  }
}
