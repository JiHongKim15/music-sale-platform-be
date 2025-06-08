package com.music.sale.application.product.port.in;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0004\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0010H&\u00a8\u0006\u0011"}, d2 = {"Lcom/music/sale/application/product/port/in/ProductUseCase;", "", "createProduct", "Lcom/music/sale/application/product/dto/ProductOutput;", "input", "Lcom/music/sale/application/product/dto/CreateProductInput;", "deleteProduct", "id", "", "getProducts", "Lorg/springframework/data/domain/Page;", "pageable", "Lcom/music/sale/common/Pageable;", "searchProducts", "Lcom/music/sale/application/product/dto/SearchProductInput;", "updateProduct", "Lcom/music/sale/application/product/dto/UpdateProductInput;", "music"})
public abstract interface ProductUseCase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput> getProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.SearchProductInput input, @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.application.product.dto.ProductOutput createProduct(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.CreateProductInput input);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.application.product.dto.ProductOutput updateProduct(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.UpdateProductInput input);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.application.product.dto.ProductOutput deleteProduct(long id);
}