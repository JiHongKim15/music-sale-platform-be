package com.music.sale.adapter.persistence.product.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\u0010"}, d2 = {"Lcom/music/sale/adapter/persistence/product/repository/ProductCatalogRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "", "findByNameContaining", "Lorg/springframework/data/domain/Page;", "name", "", "pageable", "Lorg/springframework/data/domain/Pageable;", "findByProductTypeId", "productTypeId", "searchProducts", "category", "keyword", "attribute", "music"})
public abstract interface ProductCatalogRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity> findByProductTypeId(long productTypeId, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity> findByNameContaining(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity> searchProducts(@org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.String keyword, @org.jetbrains.annotations.Nullable()
    java.lang.String attribute);
}