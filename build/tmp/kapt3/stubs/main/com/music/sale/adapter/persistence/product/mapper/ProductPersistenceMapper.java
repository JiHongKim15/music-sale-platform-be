package com.music.sale.adapter.persistence.product.mapper;

/**
 * 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/music/sale/adapter/persistence/product/mapper/ProductPersistenceMapper;", "", "categoryMapper", "Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;", "userPersistenceMapper", "Lcom/music/sale/adapter/persistence/user/mapper/UserPersistenceMapper;", "storePersistenceMapper", "Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;", "userPersistenceAdapter", "Lcom/music/sale/adapter/persistence/user/UserPersistenceAdapter;", "(Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;Lcom/music/sale/adapter/persistence/user/mapper/UserPersistenceMapper;Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;Lcom/music/sale/adapter/persistence/user/UserPersistenceAdapter;)V", "toDomain", "Lcom/music/sale/domain/product/Product;", "entity", "Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "toEntity", "saveCondition", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductItemCondition;", "catalogEntity", "Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "product", "music"})
public class ProductPersistenceMapper {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper categoryMapper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper userPersistenceMapper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper storePersistenceMapper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.UserPersistenceAdapter userPersistenceAdapter = null;
    
    public ProductPersistenceMapper(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper categoryMapper, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper userPersistenceMapper, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper storePersistenceMapper, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.UserPersistenceAdapter userPersistenceAdapter) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.product.Product toDomain(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.entity.ProductItemEntity entity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.entity.ProductItemEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition saveCondition, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity catalogEntity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.entity.ProductItemEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity catalogEntity) {
        return null;
    }
}