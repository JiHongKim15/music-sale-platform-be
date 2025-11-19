package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductCommandPortJ;
import com.music.sale.domain.product.ProductItemJ;
import com.music.sale.persistence.product.entity.ProductItemEntityJ;
import com.music.sale.persistence.product.mapper.ProductItemCommandPersistenceMapperJ;
import com.music.sale.persistence.product.repository.ProductItemCommandRepositoryJ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductItemCommandPersistenceAdapterJ implements ProductCommandPortJ {

    private final ProductItemCommandRepositoryJ productItemCommandRepositoryJ;
    private final ProductItemCommandPersistenceMapperJ mapper;

    @Override
    public ProductItemJ saveProduct(ProductItemJ item) {
        ProductItemEntityJ entity = mapper.toEntityForCreate(item);
        ProductItemEntityJ saved = productItemCommandRepositoryJ.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public ProductItemJ updateProduct(ProductItemJ productItemJ) {
        ProductItemEntityJ entity = mapper.toEntityForUpdate(productItemJ);
        ProductItemEntityJ saved = productItemCommandRepositoryJ.save(entity);
        return mapper.toDomain(saved);
    }


    @Override
    public void deleteByProductId(Long id) {
        productItemCommandRepositoryJ.deleteById(id);
    }
}
