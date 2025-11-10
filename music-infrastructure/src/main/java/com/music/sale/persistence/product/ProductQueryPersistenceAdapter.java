package com.music.sale.persistence.product;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.domain.product.enums.ProductStatus;
import com.music.sale.persistence.product.mapper.ProductQueryPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ProductQueryPersistenceAdapter {

    private final ProductItemQueryRepository queryRepo;
    private final ProductQueryPersistenceMapper mapper;

    public ProductQueryPersistenceAdapter(
            ProductItemQueryRepository queryRepo,
            ProductQueryPersistenceMapper mapper) {
        this.queryRepo = queryRepo;
        this.mapper = mapper;
    }

    public Optional<ProductItem> findById(Long id) {
        return queryRepo.findById(id).map(mapper::toDomain);
    }

    public Page<ProductItem> findByStatus(ProductStatus status, Pageable pageable) {
        return queryRepo.findByStatus(status, pageable).map(mapper::toDomain);
    }

    public Page<ProductItem> findBySellerId(Long sellerId, Pageable pageable) {
        return queryRepo.findBySellerId(sellerId, pageable).map(mapper::toDomain);
    }

    public Page<ProductItem> findByStoreId(Long storeId, Pageable pageable) {
        return queryRepo.findByStoreId(storeId, pageable).map(mapper::toDomain);
    }

    public Page<ProductItem> searchByName(String keyword, Pageable pageable) {
        return queryRepo.findByNameContainingIgnoreCase(keyword, pageable).map(mapper::toDomain);
    }

    public boolean existsByNameAndSellerId(String name, Long sellerId) {
        return queryRepo.existsByNameAndSellerId(name, sellerId);
    }
}
