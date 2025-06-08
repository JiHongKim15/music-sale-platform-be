package com.music.sale.adapter.persistence.user.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H&\u00a8\u0006\f"}, d2 = {"Lcom/music/sale/adapter/persistence/user/repository/UserRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "", "findByEmail", "email", "", "findById", "Ljava/util/Optional;", "id", "save", "userEntity", "music"})
public abstract interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.user.entity.UserEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.adapter.persistence.user.entity.UserEntity save(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.entity.UserEntity userEntity);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.user.entity.UserEntity findByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email);
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Optional<com.music.sale.adapter.persistence.user.entity.UserEntity> findById(long id);
}