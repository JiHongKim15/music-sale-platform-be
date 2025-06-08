package com.music.sale.application.product.port.out;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/application/product/port/out/ProductPort;", "", "delete", "", "product", "Lcom/music/sale/domain/product/Product;", "findAll", "Lorg/springframework/data/domain/Page;", "pageable", "Lcom/music/sale/common/Pageable;", "findById", "id", "", "save", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductCondition;", "searchProducts", "searchCriteria", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "music"})
public abstract interface ProductPort {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.domain.product.Product> findAll(@org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.domain.product.Product> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SearchProductCondition searchCriteria, @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.product.Product save(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SaveProductCondition product);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.product.Product findById(long id);
    
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product);
}