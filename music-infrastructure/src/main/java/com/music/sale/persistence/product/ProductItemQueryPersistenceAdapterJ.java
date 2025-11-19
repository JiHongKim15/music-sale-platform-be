package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductQueryPortJ;
import com.music.sale.domain.product.ProductItemJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import com.music.sale.persistence.product.mapper.ProductItemQueryPersistenceMapperJ;
import com.music.sale.persistence.product.repository.ProductItemQueryRepositoryJ;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductItemQueryPersistenceAdapterJ implements ProductQueryPortJ {

    private final ProductItemQueryRepositoryJ productItemQueryRepositoryJ;
    private final ProductItemQueryPersistenceMapperJ mapper;

    @Override
    public Optional<ProductItemJ> findByProductId(Long id) {
        return productItemQueryRepositoryJ.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<ProductItemJ> findByStatus(ProductStatusJ status, Pageable pageable) {
        return productItemQueryRepositoryJ.findByStatus(status, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<ProductItemJ> findBySellerId(Long sellerId, Pageable pageable) {
        return productItemQueryRepositoryJ.findBySellerId(sellerId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<ProductItemJ> findByStoreId(Long storeId, Pageable pageable) {
        return productItemQueryRepositoryJ.findByStoreId(storeId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<ProductItemJ> searchByKeyword(String keyword, Pageable pageable) {
        return productItemQueryRepositoryJ.findByNameContainingIgnoreCase(keyword, pageable).map(mapper::toDomain);
    }
}
