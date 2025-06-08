package com.music.sale.adapter.persistence.user.entity;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "auth_users")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0017\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\tR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR \u0010\b\u001a\u0004\u0018\u00010\u00068\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u00020\u00068\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0018"}, d2 = {"Lcom/music/sale/adapter/persistence/user/entity/AuthUserEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "userId", "provider", "", "providerId", "password", "(Ljava/lang/Long;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "getProvider", "setProvider", "getProviderId", "setProviderId", "getUserId", "()J", "Companion", "music"})
public class AuthUserEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false, unique = true)
    private final long userId = 0L;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String provider;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private java.lang.String providerId;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private java.lang.String password;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.adapter.persistence.user.entity.AuthUserEntity.Companion Companion = null;
    
    public AuthUserEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, long userId, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.Nullable()
    java.lang.String providerId, @org.jetbrains.annotations.Nullable()
    java.lang.String password) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    public long getUserId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getProvider() {
        return null;
    }
    
    public void setProvider(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getProviderId() {
        return null;
    }
    
    public void setProviderId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getPassword() {
        return null;
    }
    
    public void setPassword(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b\u00a8\u0006\f"}, d2 = {"Lcom/music/sale/adapter/persistence/user/entity/AuthUserEntity$Companion;", "", "()V", "formEmail", "Lcom/music/sale/adapter/persistence/user/entity/AuthUserEntity;", "user", "Lcom/music/sale/domain/user/User;", "password", "", "fromProvider", "provider", "providerId", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.user.entity.AuthUserEntity fromProvider(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
        java.lang.String provider, @org.jetbrains.annotations.NotNull()
        java.lang.String providerId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.user.entity.AuthUserEntity formEmail(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
        java.lang.String password) {
            return null;
        }
    }
}