package com.music.sale.application.user.port.out;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J \u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/application/user/port/out/UserPort;", "", "findByEmail", "Lcom/music/sale/domain/user/User;", "email", "", "findById", "id", "", "saveEmail", "user", "password", "saveProvider", "provider", "providerId", "music"})
public abstract interface UserPort {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.user.User saveProvider(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.NotNull()
    java.lang.String providerId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.user.User saveEmail(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String password);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.user.User findById(long id);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.user.User findByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email);
}