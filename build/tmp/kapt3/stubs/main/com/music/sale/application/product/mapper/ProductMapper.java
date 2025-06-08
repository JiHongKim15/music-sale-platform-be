package com.music.sale.application.product.mapper;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/application/product/mapper/ProductMapper;", "", "()V", "toOutput", "Lcom/music/sale/application/product/dto/ProductOutput;", "product", "Lcom/music/sale/domain/product/Product;", "toSaveProductCondition", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductCondition;", "input", "Lcom/music/sale/application/product/dto/CreateProductInput;", "Lcom/music/sale/application/product/dto/UpdateProductInput;", "toSearchProductCondition", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "Lcom/music/sale/application/product/dto/SearchProductInput;", "music"})
public class ProductMapper {
    
    public ProductMapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.ProductOutput toOutput(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.dto.SearchProductCondition toSearchProductCondition(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.SearchProductInput input) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.dto.SaveProductCondition toSaveProductCondition(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.product.Product product, @org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.UpdateProductInput input) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.dto.SaveProductCondition toSaveProductCondition(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.CreateProductInput input) {
        return null;
    }
}