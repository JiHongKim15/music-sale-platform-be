package com.music.sale.application.product.port.outport;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.domain.product.enums.ProductStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryPort {
  Optional<ProductItem> findByProductId(Long id);

  Page<ProductItem> findAll(Pageable pageable);

  Page<ProductItem> findByStatus(ProductStatus status, Pageable pageable);

  Page<ProductItem> findBySellerId(Long sellerId, Pageable pageable);

  Page<ProductItem> findByStoreId(Long storeId, Pageable pageable);

  Page<ProductItem> searchByKeyword(String keyword, Pageable pageable);
}
