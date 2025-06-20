package com.music.sale.adapter.persistence.product;

@org.springframework.stereotype.Component()
@org.springframework.transaction.annotation.Transactional()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/music/sale/adapter/persistence/product/ProductPersistenceAdapter;", "Lcom/music/sale/application/product/port/out/ProductPort;", "productCatalogRepository", "Lcom/music/sale/adapter/persistence/product/repository/ProductCatalogRepository;", "productItemRepository", "Lcom/music/sale/adapter/persistence/product/repository/ProductItemRepository;", "productItemQueryDslRepository", "Lcom/music/sale/adapter/persistence/product/repository/ProductItemQueryDslRepository;", "mapper", "Lcom/music/sale/adapter/persistence/product/mapper/ProductPersistenceMapper;", "(Lcom/music/sale/adapter/persistence/product/repository/ProductCatalogRepository;Lcom/music/sale/adapter/persistence/product/repository/ProductItemRepository;Lcom/music/sale/adapter/persistence/product/repository/ProductItemQueryDslRepository;Lcom/music/sale/adapter/persistence/product/mapper/ProductPersistenceMapper;)V", "deleteById", "", "id", "", "findAll", "Lorg/springframework/data/domain/Page;", "Lcom/music/sale/domain/product/Product;", "pageable", "Lcom/music/sale/common/Pageable;", "findById", "save", "productCondition", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductItemCondition;", "searchProducts", "searchCondition", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "update", "product", "music"})
public class ProductPersistenceAdapter implements com.music.sale.application.product.port.out.ProductPort {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository productCatalogRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.product.repository.ProductItemRepository productItemRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.product.repository.ProductItemQueryDslRepository productItemQueryDslRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.product.mapper.ProductPersistenceMapper mapper = null;
    
    public ProductPersistenceAdapter(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository productCatalogRepository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.repository.ProductItemRepository productItemRepository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.repository.ProductItemQueryDslRepository productItemQueryDslRepository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.mapper.ProductPersistenceMapper mapper) {
        super();
    }
    
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.data.domain.Page<com.music.sale.domain.product.Product> findAll(@org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable) {
        return null;
    }
    
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.data.domain.Page<com.music.sale.domain.product.Product> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SearchProductCondition searchCondition, @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.product.Product save(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition productCondition) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.product.Product update(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.product.Product findById(long id) {
        return null;
    }
    
    @java.lang.Override()
    public void deleteById(long id) {
    }
}