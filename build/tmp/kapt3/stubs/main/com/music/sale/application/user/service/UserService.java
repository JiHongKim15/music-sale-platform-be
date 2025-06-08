package com.music.sale.application.user.service;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0017J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0004\n\u0002\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/application/user/service/UserService;", "", "userPort", "error/NonExistentClass", "userDomainService", "Lcom/music/sale/domain/user/UserDomainService;", "userMapper", "(Lerror/NonExistentClass;Lcom/music/sale/domain/user/UserDomainService;Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "createUserByEmail", "Lcom/music/sale/application/user/dto/UserOutput;", "input", "Lcom/music/sale/application/user/dto/CreateUserByEmailInput;", "createUserByProvider", "Lcom/music/sale/application/user/dto/CreateUserByProviderInput;", "getUser", "id", "", "music"})
public class UserService {
    @org.jetbrains.annotations.NotNull()
    private final error.NonExistentClass userPort = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.user.UserDomainService userDomainService = null;
    @org.jetbrains.annotations.NotNull()
    private final error.NonExistentClass userMapper = null;
    
    public UserService(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass userPort, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.UserDomainService userDomainService, @org.jetbrains.annotations.NotNull()
    error.NonExistentClass userMapper) {
        super();
    }
    
    @org.springframework.transaction.annotation.Transactional()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.user.dto.UserOutput createUserByEmail(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.dto.CreateUserByEmailInput input) {
        return null;
    }
    
    @org.springframework.transaction.annotation.Transactional()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.user.dto.UserOutput createUserByProvider(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.dto.CreateUserByProviderInput input) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.application.user.dto.UserOutput getUser(long id) {
        return null;
    }
}