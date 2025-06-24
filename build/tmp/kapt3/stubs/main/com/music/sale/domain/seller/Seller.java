package com.music.sale.domain.seller;

/**
 * 판매자 도메인 모델
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001:\u000589:;<BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0007H\u00c6\u0003J\t\u0010)\u001a\u00020\tH\u00c6\u0003J\t\u0010*\u001a\u00020\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\rH\u00c6\u0003J\t\u0010,\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003Jn\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u00c6\u0001\u00a2\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u000205H\u00d6\u0001J\t\u00106\u001a\u000207H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u00a8\u0006="}, d2 = {"Lcom/music/sale/domain/seller/Seller;", "", "id", "", "user", "Lcom/music/sale/domain/user/User;", "companyName", "Lcom/music/sale/domain/seller/Seller$CompanyName;", "businessNumber", "Lcom/music/sale/domain/seller/Seller$BusinessNumber;", "contactEmail", "Lcom/music/sale/domain/seller/Seller$ContactEmail;", "contactPhone", "Lcom/music/sale/domain/seller/Seller$ContactPhone;", "status", "Lcom/music/sale/domain/seller/Seller$SellerStatus;", "createdAt", "Ljava/time/LocalDateTime;", "updatedAt", "(Ljava/lang/Long;Lcom/music/sale/domain/user/User;Lcom/music/sale/domain/seller/Seller$CompanyName;Lcom/music/sale/domain/seller/Seller$BusinessNumber;Lcom/music/sale/domain/seller/Seller$ContactEmail;Lcom/music/sale/domain/seller/Seller$ContactPhone;Lcom/music/sale/domain/seller/Seller$SellerStatus;Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;)V", "getBusinessNumber", "()Lcom/music/sale/domain/seller/Seller$BusinessNumber;", "getCompanyName", "()Lcom/music/sale/domain/seller/Seller$CompanyName;", "getContactEmail", "()Lcom/music/sale/domain/seller/Seller$ContactEmail;", "getContactPhone", "()Lcom/music/sale/domain/seller/Seller$ContactPhone;", "getCreatedAt", "()Ljava/time/LocalDateTime;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStatus", "()Lcom/music/sale/domain/seller/Seller$SellerStatus;", "getUpdatedAt", "getUser", "()Lcom/music/sale/domain/user/User;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Lcom/music/sale/domain/user/User;Lcom/music/sale/domain/seller/Seller$CompanyName;Lcom/music/sale/domain/seller/Seller$BusinessNumber;Lcom/music/sale/domain/seller/Seller$ContactEmail;Lcom/music/sale/domain/seller/Seller$ContactPhone;Lcom/music/sale/domain/seller/Seller$SellerStatus;Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;)Lcom/music/sale/domain/seller/Seller;", "equals", "", "other", "hashCode", "", "toString", "", "BusinessNumber", "CompanyName", "ContactEmail", "ContactPhone", "SellerStatus", "music"})
public final class Seller {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.user.User user = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.seller.Seller.CompanyName companyName = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.seller.Seller.BusinessNumber businessNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.seller.Seller.ContactEmail contactEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.seller.Seller.ContactPhone contactPhone = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.seller.Seller.SellerStatus status = null;
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDateTime createdAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDateTime updatedAt = null;
    
    public Seller(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.CompanyName companyName, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.BusinessNumber businessNumber, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.ContactEmail contactEmail, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.ContactPhone contactPhone, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.SellerStatus status, @org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime createdAt, @org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime updatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.user.User getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.CompanyName getCompanyName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.BusinessNumber getBusinessNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.ContactEmail getContactEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.ContactPhone getContactPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.SellerStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime getUpdatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.user.User component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.CompanyName component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.BusinessNumber component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.ContactEmail component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.ContactPhone component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller.SellerStatus component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.seller.Seller copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.CompanyName companyName, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.BusinessNumber businessNumber, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.ContactEmail contactEmail, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.ContactPhone contactPhone, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.seller.Seller.SellerStatus status, @org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime createdAt, @org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime updatedAt) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/seller/Seller$BusinessNumber;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class BusinessNumber {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        public BusinessNumber(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.seller.Seller.BusinessNumber copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/seller/Seller$CompanyName;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class CompanyName {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        public CompanyName(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.seller.Seller.CompanyName copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/seller/Seller$ContactEmail;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class ContactEmail {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        public ContactEmail(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.seller.Seller.ContactEmail copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/seller/Seller$ContactPhone;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class ContactPhone {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        public ContactPhone(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.seller.Seller.ContactPhone copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/domain/seller/Seller$SellerStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "APPROVED", "REJECTED", "SUSPENDED", "music"})
    public static enum SellerStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ APPROVED /* = new APPROVED() */,
        /*public static final*/ REJECTED /* = new REJECTED() */,
        /*public static final*/ SUSPENDED /* = new SUSPENDED() */;
        
        SellerStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.seller.Seller.SellerStatus> getEntries() {
            return null;
        }
    }
}