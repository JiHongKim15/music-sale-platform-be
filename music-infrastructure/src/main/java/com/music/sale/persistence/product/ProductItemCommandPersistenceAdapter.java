package com.music.sale.persistence.product;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import com.music.sale.persistence.product.mapper.ProductItemCommandPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemCommandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductItemCommandPersistenceAdapter {

    private final ProductItemCommandRepository productItemCommandRepository;
    private final ProductItemCommandPersistenceMapper mapper;

    public ProductItem save(ProductItem item) {
        ProductItemEntity entity = mapper.toEntityForCreate(item);
        ProductItemEntity saved = productItemCommandRepository.save(entity);
        return mapper.toDomain(saved);
    }


    public ProductItem update(ProductItem productItem) {
        ProductItemEntity existing = productItemCommandRepository.findById(productItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("ProductItem not found: " + productItem.getId()));

        ProductItemEntity entityToSave = mapper.toEntityForUpdate(productItem, existing);

        ProductItemEntity saved = productItemCommandRepository.save(entityToSave);
        return mapper.toDomain(saved);
    }


    public void deleteById(Long id) {
        productItemCommandRepository.deleteById(id);
    }

    public void increaseViewCount(Long productId) {
        productItemCommandRepository.increaseViewCount(productId);
    }
}
