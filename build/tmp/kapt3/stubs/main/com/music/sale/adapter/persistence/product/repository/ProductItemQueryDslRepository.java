package com.music.sale.adapter.persistence.product.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/music/sale/adapter/persistence/product/repository/ProductItemQueryDslRepository;", "", "searchProducts", "Lorg/springframework/data/domain/Page;", "Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "searchCriteria", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "pageable", "Lorg/springframework/data/domain/Pageable;", "music"})
public abstract interface ProductItemQueryDslRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SearchProductCondition searchCriteria, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
}