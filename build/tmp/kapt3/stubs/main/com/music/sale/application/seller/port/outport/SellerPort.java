package com.music.sale.application.seller.port.outport;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/music/sale/application/seller/port/outport/SellerPort;", "", "findById", "Lcom/music/sale/domain/user/User;", "id", "", "save", "seller", "Lcom/music/sale/domain/seller/Seller;", "music"})
public abstract interface SellerPort {
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.user.User findById(long id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.user.User save(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller seller);
}