package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import com.music.sale.persistence.product.mapper.ProductItemCommandPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemCommandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductItemCommandPersistenceAdapter implements ProductCommandPort {

    private final ProductItemCommandRepository productItemCommandRepository;
    private final ProductItemCommandPersistenceMapper mapper;

    @Override
    public ProductItem saveProduct(ProductItem item) {
        ProductItemEntity entity = mapper.toEntityForCreate(item);
        ProductItemEntity saved = productItemCommandRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public ProductItem updateProduct(ProductItem productItem) {
        ProductItemEntity entity = mapper.toEntityForUpdate(productItem);
        ProductItemEntity saved = productItemCommandRepository.save(entity);
        return mapper.toDomain(saved);
    }


    @Override
    public void deleteByProductId(Long id) {
        productItemCommandRepository.deleteById(id);
    }
}
