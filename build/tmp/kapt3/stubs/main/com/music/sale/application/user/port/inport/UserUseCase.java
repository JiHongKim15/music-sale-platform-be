package com.music.sale.application.user.port.inport;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/music/sale/application/user/port/inport/UserUseCase;", "", "createUserByEmail", "Lcom/music/sale/application/user/dto/UserOutput;", "input", "Lcom/music/sale/application/user/dto/CreateUserByEmailInput;", "createUserByProvider", "Lcom/music/sale/application/user/dto/CreateUserByProviderInput;", "getUser", "id", "", "music"})
public abstract interface UserUseCase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.application.user.dto.UserOutput createUserByEmail(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.dto.CreateUserByEmailInput input);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.application.user.dto.UserOutput createUserByProvider(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.dto.CreateUserByProviderInput input);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.application.user.dto.UserOutput getUser(long id);
}