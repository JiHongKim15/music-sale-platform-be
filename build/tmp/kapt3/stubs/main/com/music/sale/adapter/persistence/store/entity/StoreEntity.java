package com.music.sale.adapter.persistence.store.entity;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "store")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0017\u0018\u00002\u00020\u0001:\u0001&B\u007f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013R\u0016\u0010\b\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u000e\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0016\u0010\r\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0016\u0010\u0012\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0016\u0010\u0010\u001a\u00020\u00118\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u0007\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015\u00a8\u0006\'"}, d2 = {"Lcom/music/sale/adapter/persistence/store/entity/StoreEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "name", "", "description", "zipcode", "baseAddress", "detailAddress", "latitude", "", "longitude", "contactNumber", "businessNumber", "imageUrl", "status", "Lcom/music/sale/adapter/persistence/store/entity/StoreEntity$StoreStatus;", "sellerId", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/music/sale/adapter/persistence/store/entity/StoreEntity$StoreStatus;J)V", "getBaseAddress", "()Ljava/lang/String;", "getBusinessNumber", "getContactNumber", "getDescription", "getDetailAddress", "getId", "()J", "getImageUrl", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude", "getName", "getSellerId", "getStatus", "()Lcom/music/sale/adapter/persistence/store/entity/StoreEntity$StoreStatus;", "getZipcode", "StoreStatus", "music"})
public class StoreEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private final long id = 0L;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @jakarta.persistence.Column(nullable = false, length = 1000)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String zipcode = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String baseAddress = null;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String detailAddress = null;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double latitude = null;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double longitude = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contactNumber = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessNumber = null;
    @jakarta.persistence.Column(nullable = true)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String imageUrl = null;
    @jakarta.persistence.Column(nullable = false)
    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus status = null;
    @jakarta.persistence.Column(nullable = false)
    private final long sellerId = 0L;
    
    public StoreEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String zipcode, @org.jetbrains.annotations.NotNull()
    java.lang.String baseAddress, @org.jetbrains.annotations.Nullable()
    java.lang.String detailAddress, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String contactNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String businessNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus status, long sellerId) {
        super();
    }
    
    public long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getZipcode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getBaseAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getDetailAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Double getLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Double getLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getContactNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getBusinessNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus getStatus() {
        return null;
    }
    
    public long getSellerId() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/adapter/persistence/store/entity/StoreEntity$StoreStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "ACTIVE", "SUSPENDED", "CLOSED", "music"})
    public static enum StoreStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ ACTIVE /* = new ACTIVE() */,
        /*public static final*/ SUSPENDED /* = new SUSPENDED() */,
        /*public static final*/ CLOSED /* = new CLOSED() */;
        
        StoreStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus> getEntries() {
            return null;
        }
    }
}