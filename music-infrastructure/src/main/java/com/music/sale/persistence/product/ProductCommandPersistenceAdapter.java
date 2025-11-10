package com.music.sale.persistence.product;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import com.music.sale.persistence.product.mapper.ProductCommandPersistenceMapper;
import com.music.sale.persistence.product.repository.ProductItemCommandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductCommandPersistenceAdapter {

    private final ProductItemCommandRepository commandRepo;
    private final ProductCommandPersistenceMapper mapper;

    public ProductCommandPersistenceAdapter(ProductItemCommandRepository commandRepo,
                                            ProductCommandPersistenceMapper mapper) {
        this.commandRepo = commandRepo;
        this.mapper = mapper;
    }

    public ProductItem save(ProductItem item) {
        ProductItemEntity entity = mapper.toEntity(item);
        ProductItemEntity saved = commandRepo.save(entity);
        return mapper.toDomain(saved);
    }

    public ProductItem update(Long id, ProductItem item) {
        // 전체 업데이트(merge) — 존재 확인
        ProductItemEntity current = commandRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductItem not found: " + id));

        // 덮어쓰기: id 고정
        ProductItemEntity patch = mapper.toEntity(item);
        patch.setId(current.getId());

        ProductItemEntity saved = commandRepo.save(patch);
        return mapper.toDomain(saved);
    }

    public void deleteById(Long id) {
        commandRepo.deleteById(id);
    }
}
