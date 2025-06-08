package com.music.sale.adapter.persistence.product.mapper;

/**
 * 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/music/sale/adapter/persistence/product/mapper/ProductPersistenceMapper;", "", "categoryMapper", "Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;", "(Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;)V", "toCatalogEntity", "Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "product", "Lcom/music/sale/domain/product/Product;", "toDomain", "entity", "Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "toEntity", "music"})
public class ProductPersistenceMapper {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper categoryMapper = null;
    
    public ProductPersistenceMapper(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper categoryMapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.product.Product toDomain(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.entity.ProductItemEntity entity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.entity.ProductItemEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity toCatalogEntity(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product) {
        return null;
    }
}