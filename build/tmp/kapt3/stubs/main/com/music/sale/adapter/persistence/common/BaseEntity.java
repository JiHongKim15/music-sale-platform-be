package com.music.sale.adapter.persistence.common;

@jakarta.persistence.MappedSuperclass()
@jakarta.persistence.EntityListeners(value = {org.springframework.data.jpa.domain.support.AuditingEntityListener.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u00020\n8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lcom/music/sale/adapter/persistence/common/BaseEntity;", "", "()V", "createdAt", "Ljava/time/LocalDateTime;", "getCreatedAt", "()Ljava/time/LocalDateTime;", "setCreatedAt", "(Ljava/time/LocalDateTime;)V", "createdBy", "", "getCreatedBy", "()Ljava/lang/String;", "setCreatedBy", "(Ljava/lang/String;)V", "updatedAt", "getUpdatedAt", "setUpdatedAt", "updatedBy", "getUpdatedBy", "setUpdatedBy", "music"})
public abstract class BaseEntity {
    @org.springframework.data.annotation.CreatedDate()
    @jakarta.persistence.Column(name = "created_at", nullable = false, updatable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.LocalDateTime createdAt;
    @org.springframework.data.annotation.CreatedBy()
    @jakarta.persistence.Column(name = "created_by", nullable = false, updatable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String createdBy = "-1";
    @org.springframework.data.annotation.LastModifiedDate()
    @jakarta.persistence.Column(name = "updated_at", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.LocalDateTime updatedAt;
    @org.springframework.data.annotation.LastModifiedBy()
    @jakarta.persistence.Column(name = "updated_by", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String updatedBy = "-1";
    
    public BaseEntity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.LocalDateTime getCreatedAt() {
        return null;
    }
    
    public void setCreatedAt(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getCreatedBy() {
        return null;
    }
    
    public void setCreatedBy(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.LocalDateTime getUpdatedAt() {
        return null;
    }
    
    public void setUpdatedAt(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getUpdatedBy() {
        return null;
    }
    
    public void setUpdatedBy(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
}