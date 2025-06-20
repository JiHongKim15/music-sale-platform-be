package com.music.sale.adapter.persistence.user;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nH\u0017J \u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/music/sale/adapter/persistence/user/UserPersistenceAdapter;", "Lcom/music/sale/application/user/port/out/UserPort;", "userRepository", "Lcom/music/sale/adapter/persistence/user/repository/UserRepository;", "authUserRepository", "Lcom/music/sale/adapter/persistence/user/repository/AuthUserRepository;", "(Lcom/music/sale/adapter/persistence/user/repository/UserRepository;Lcom/music/sale/adapter/persistence/user/repository/AuthUserRepository;)V", "findByEmail", "Lcom/music/sale/domain/user/User;", "email", "", "findById", "id", "", "saveEmail", "user", "password", "saveProvider", "provider", "providerId", "music"})
public class UserPersistenceAdapter implements com.music.sale.application.user.port.out.UserPort {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.repository.AuthUserRepository authUserRepository = null;
    
    public UserPersistenceAdapter(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.repository.AuthUserRepository authUserRepository) {
        super();
    }
    
    @org.springframework.transaction.annotation.Transactional()
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.user.User saveProvider(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.NotNull()
    java.lang.String providerId) {
        return null;
    }
    
    @org.springframework.transaction.annotation.Transactional()
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.user.User saveEmail(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.user.User findById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.user.User findByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return null;
    }
}