package com.music.sale.adapter.persistence.seller.entity;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "sellers")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\b\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\t\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\n\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\f8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "user", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "companyName", "", "businessNumber", "contactEmail", "contactPhone", "status", "Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$SellerStatus;", "(Ljava/lang/Long;Lcom/music/sale/adapter/persistence/user/entity/UserEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$SellerStatus;)V", "getBusinessNumber", "()Ljava/lang/String;", "getCompanyName", "getContactEmail", "getContactPhone", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStatus", "()Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$SellerStatus;", "getUser", "()Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "toDomain", "Lcom/music/sale/domain/seller/model/Seller;", "Companion", "SellerStatus", "music"})
public class SellerEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false)
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.entity.UserEntity user = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String companyName = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessNumber = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contactEmail = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contactPhone = null;
    @jakarta.persistence.Column(nullable = false)
    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.seller.entity.SellerEntity.SellerStatus status = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.adapter.persistence.seller.entity.SellerEntity.Companion Companion = null;
    
    public SellerEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.entity.UserEntity user, @org.jetbrains.annotations.NotNull()
    java.lang.String companyName, @org.jetbrains.annotations.NotNull()
    java.lang.String businessNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String contactEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String contactPhone, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.seller.entity.SellerEntity.SellerStatus status) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.user.entity.UserEntity getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getCompanyName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getBusinessNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getContactEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getContactPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.seller.entity.SellerEntity.SellerStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.seller.model.Seller toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$Companion;", "", "()V", "fromDomain", "Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity;", "seller", "Lcom/music/sale/domain/seller/model/Seller;", "fromId", "id", "", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.seller.entity.SellerEntity fromDomain(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.seller.model.Seller seller) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.seller.entity.SellerEntity fromId(long id) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$SellerStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "APPROVED", "REJECTED", "SUSPENDED", "music"})
    public static enum SellerStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ APPROVED /* = new APPROVED() */,
        /*public static final*/ REJECTED /* = new REJECTED() */,
        /*public static final*/ SUSPENDED /* = new SUSPENDED() */;
        
        SellerStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.adapter.persistence.seller.entity.SellerEntity.SellerStatus> getEntries() {
            return null;
        }
    }
}