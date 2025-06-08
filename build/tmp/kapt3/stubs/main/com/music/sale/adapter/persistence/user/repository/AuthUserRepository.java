package com.music.sale.adapter.persistence.user.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0003H&\u00a8\u0006\n"}, d2 = {"Lcom/music/sale/adapter/persistence/user/repository/AuthUserRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/user/entity/AuthUserEntity;", "", "findByProviderAndProviderId", "provider", "", "providerId", "findByUserId", "userId", "music"})
public abstract interface AuthUserRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.user.entity.AuthUserEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.user.entity.AuthUserEntity findByUserId(long userId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.user.entity.AuthUserEntity findByProviderAndProviderId(@org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.NotNull()
    java.lang.String providerId);
}