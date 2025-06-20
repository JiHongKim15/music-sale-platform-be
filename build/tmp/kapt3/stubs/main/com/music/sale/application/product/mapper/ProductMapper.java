package com.music.sale.application.product.mapper;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0011H\u0016\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/application/product/mapper/ProductMapper;", "", "()V", "toOutput", "Lcom/music/sale/application/product/dto/ProductOutput;", "product", "Lcom/music/sale/domain/product/Product;", "toSaveProductCondition", "Lcom/music/sale/adapter/persistence/product/dto/SaveProductItemCondition;", "input", "Lcom/music/sale/application/product/dto/CreateProductInput;", "seller", "Lcom/music/sale/domain/user/User;", "store", "Lcom/music/sale/domain/store/model/Store;", "toSearchProductCondition", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "Lcom/music/sale/application/product/dto/SearchProductInput;", "music"})
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
    public com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition toSaveProductCondition(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.CreateProductInput input, @org.jetbrains.annotations.Nullable()
    com.music.sale.domain.user.User seller, @org.jetbrains.annotations.Nullable()
    com.music.sale.domain.store.model.Store store) {
        return null;
    }
}