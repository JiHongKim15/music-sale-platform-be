package com.music.sale.application.product.port.out;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH&\u00a8\u0006\u0013"}, d2 = {"Lcom/music/sale/application/product/port/out/ProductPort;", "", "deleteById", "", "id", "", "findAll", "Lorg/springframework/data/domain/Page;", "Lcom/music/sale/domain/product/Product;", "pageable", "Lcom/music/sale/common/Pageable;", "findById", "save", "productCondition", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductItemCondition;", "searchProducts", "searchCondition", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "update", "music"})
public abstract interface ProductPort {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.domain.product.Product> findAll(@org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.domain.product.Product> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SearchProductCondition searchCondition, @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.product.Product save(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition productCondition);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.product.Product update(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product productCondition);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.product.Product findById(long id);
    
    public abstract void deleteById(long id);
}