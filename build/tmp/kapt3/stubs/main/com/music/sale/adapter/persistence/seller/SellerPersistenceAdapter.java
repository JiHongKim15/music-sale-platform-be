package com.music.sale.adapter.persistence.seller;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/SellerPersistenceAdapter;", "Lcom/music/sale/application/seller/port/outport/SellerPort;", "()V", "findById", "Lcom/music/sale/domain/user/User;", "id", "", "save", "seller", "Lcom/music/sale/domain/seller/Seller;", "music"})
public class SellerPersistenceAdapter implements com.music.sale.application.seller.port.outport.SellerPort {
    
    public SellerPersistenceAdapter() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.user.User findById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.user.User save(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller seller) {
        return null;
    }
}