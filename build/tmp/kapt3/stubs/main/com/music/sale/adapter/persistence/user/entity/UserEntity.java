package com.music.sale.adapter.persistence.user.entity;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "users")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0007\u001a\u00020\b8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0019"}, d2 = {"Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "email", "", "name", "role", "Lcom/music/sale/domain/user/enum/UserRole;", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/music/sale/domain/user/enum/UserRole;)V", "getEmail", "()Ljava/lang/String;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "setName", "(Ljava/lang/String;)V", "getRole", "()Lcom/music/sale/domain/user/enum/UserRole;", "setRole", "(Lcom/music/sale/domain/user/enum/UserRole;)V", "toDomain", "Lcom/music/sale/domain/user/User;", "Companion", "music"})
public class UserEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false, unique = true)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String email = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.adapter.persistence.user.entity.UserEntity.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    public void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.user.User toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2 = {"Lcom/music/sale/adapter/persistence/user/entity/UserEntity$Companion;", "", "()V", "empty", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "fromDomain", "user", "Lcom/music/sale/domain/user/User;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.user.entity.UserEntity fromDomain(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.user.User user) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.user.entity.UserEntity empty() {
            return null;
        }
    }
}