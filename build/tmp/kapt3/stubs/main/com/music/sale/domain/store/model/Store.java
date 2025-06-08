package com.music.sale.domain.store.model;

/**
 * 상점(매장) 도메인 모델
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 %2\u00020\u0001:\u0003$%&B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B{\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016\u00a8\u0006'"}, d2 = {"Lcom/music/sale/domain/store/model/Store;", "", "id", "", "(J)V", "name", "", "description", "address", "Lcom/music/sale/domain/store/model/Store$Address;", "contactNumber", "businessNumber", "imageUrl", "status", "Lcom/music/sale/domain/store/model/Store$StoreStatus;", "sellerId", "createdAt", "updatedAt", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/music/sale/domain/store/model/Store$Address;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/music/sale/domain/store/model/Store$StoreStatus;JLjava/lang/String;Ljava/lang/String;)V", "getAddress", "()Lcom/music/sale/domain/store/model/Store$Address;", "getBusinessNumber", "()Ljava/lang/String;", "getContactNumber", "getCreatedAt", "getDescription", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getImageUrl", "getName", "getSellerId", "()J", "getStatus", "()Lcom/music/sale/domain/store/model/Store$StoreStatus;", "getUpdatedAt", "Address", "Companion", "StoreStatus", "music"})
public final class Store {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.store.model.Store.Address address = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contactNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessNumber = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String imageUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.store.model.Store.StoreStatus status = null;
    private final long sellerId = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createdAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String updatedAt = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.store.model.Store.Companion Companion = null;
    
    public Store(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.store.model.Store.Address address, @org.jetbrains.annotations.NotNull()
    java.lang.String contactNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String businessNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.store.model.Store.StoreStatus status, long sellerId, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String updatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.store.model.Store.Address getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContactNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusinessNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.store.model.Store.StoreStatus getStatus() {
        return null;
    }
    
    public final long getSellerId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUpdatedAt() {
        return null;
    }
    
    public Store(long id) {
        super();
    }
    
    public Store() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJD\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lcom/music/sale/domain/store/model/Store$Address;", "", "zipcode", "", "baseAddress", "detailAddress", "latitude", "", "longitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "getBaseAddress", "()Ljava/lang/String;", "getDetailAddress", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude", "getZipcode", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)Lcom/music/sale/domain/store/model/Store$Address;", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class Address {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String zipcode = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String baseAddress = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String detailAddress = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Double latitude = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Double longitude = null;
        
        public Address(@org.jetbrains.annotations.NotNull()
        java.lang.String zipcode, @org.jetbrains.annotations.NotNull()
        java.lang.String baseAddress, @org.jetbrains.annotations.NotNull()
        java.lang.String detailAddress, @org.jetbrains.annotations.Nullable()
        java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
        java.lang.Double longitude) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getZipcode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBaseAddress() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDetailAddress() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double getLatitude() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double getLongitude() {
            return null;
        }
        
        public Address() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.store.model.Store.Address copy(@org.jetbrains.annotations.NotNull()
        java.lang.String zipcode, @org.jetbrains.annotations.NotNull()
        java.lang.String baseAddress, @org.jetbrains.annotations.NotNull()
        java.lang.String detailAddress, @org.jetbrains.annotations.Nullable()
        java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
        java.lang.Double longitude) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/music/sale/domain/store/model/Store$Companion;", "", "()V", "empty", "Lcom/music/sale/domain/store/model/Store;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.store.model.Store empty() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/domain/store/model/Store$StoreStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "ACTIVE", "SUSPENDED", "CLOSED", "music"})
    public static enum StoreStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ ACTIVE /* = new ACTIVE() */,
        /*public static final*/ SUSPENDED /* = new SUSPENDED() */,
        /*public static final*/ CLOSED /* = new CLOSED() */;
        
        StoreStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.store.model.Store.StoreStatus> getEntries() {
            return null;
        }
    }
}