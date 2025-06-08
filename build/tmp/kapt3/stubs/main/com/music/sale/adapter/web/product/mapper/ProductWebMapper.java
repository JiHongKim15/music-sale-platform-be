package com.music.sale.adapter.web.product.mapper;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u000eH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/adapter/web/product/mapper/ProductWebMapper;", "", "()V", "toCreateProductInput", "Lcom/music/sale/application/product/dto/CreateProductInput;", "request", "Lcom/music/sale/adapter/web/product/request/CreateProductRequest;", "toSearchCondition", "Lcom/music/sale/application/product/dto/SearchProductInput;", "Lcom/music/sale/adapter/web/product/request/SearchProductRequest;", "toUpdateProductInput", "Lcom/music/sale/application/product/dto/UpdateProductInput;", "id", "", "Lcom/music/sale/adapter/web/product/request/UpdateProductRequest;", "music"})
public class ProductWebMapper {
    
    public ProductWebMapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.CreateProductInput toCreateProductInput(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.CreateProductRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.UpdateProductInput toUpdateProductInput(long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.UpdateProductRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.SearchProductInput toSearchCondition(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.SearchProductRequest request) {
        return null;
    }
}