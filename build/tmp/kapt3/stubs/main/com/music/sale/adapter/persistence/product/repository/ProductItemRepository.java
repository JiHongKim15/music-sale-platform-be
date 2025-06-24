package com.music.sale.adapter.persistence.product.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\b\u0012\u0004\u0012\u00020\u00020\u0004J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&JK\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\b\u001a\u00020\tH\'\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/music/sale/adapter/persistence/product/repository/ProductItemRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "", "Lorg/springframework/data/jpa/repository/JpaSpecificationExecutor;", "findByCatalogId", "Lorg/springframework/data/domain/Page;", "catalogId", "pageable", "Lorg/springframework/data/domain/Pageable;", "findByCondition", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "findBySellerId", "sellerId", "findByStockQuantityGreaterThan", "quantity", "", "findByStoreId", "storeId", "searchItems", "inStock", "", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/music/sale/domain/product/enum/ProductCondition;Ljava/lang/Boolean;Lorg/springframework/data/domain/Pageable;)Lorg/springframework/data/domain/Page;", "music"})
public abstract interface ProductItemRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.product.entity.ProductItemEntity, java.lang.Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> findByCatalogId(long catalogId, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> findBySellerId(long sellerId, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> findByStoreId(long storeId, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> findByStockQuantityGreaterThan(int quantity, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
}