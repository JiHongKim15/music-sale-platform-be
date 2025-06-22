package com.music.sale.adapter.persistence.seller;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/SellerPersistenceAdapter;", "Lcom/music/sale/application/seller/port/outport/SellerPort;", "userPort", "Lcom/music/sale/application/user/port/outport/UserPort;", "(Lcom/music/sale/application/user/port/outport/UserPort;)V", "findById", "Lcom/music/sale/domain/user/User;", "id", "", "save", "seller", "Lcom/music/sale/domain/seller/Seller;", "music"})
public class SellerPersistenceAdapter implements com.music.sale.application.seller.port.outport.SellerPort {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.user.port.outport.UserPort userPort = null;
    
    public SellerPersistenceAdapter(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.port.outport.UserPort userPort) {
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