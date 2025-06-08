package com.music.sale.adapter.persistence.seller.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u0006H&J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0014\u001a\u00020\u0003H&\u00a8\u0006\u0015"}, d2 = {"Lcom/music/sale/adapter/persistence/seller/repository/SellerRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity;", "", "findByBusinessNumber", "businessNumber", "", "findByCompanyNameContaining", "Lorg/springframework/data/domain/Page;", "companyName", "pageable", "Lorg/springframework/data/domain/Pageable;", "findByContactEmailContaining", "email", "findByContactPhone", "phone", "findByStatus", "status", "Lcom/music/sale/adapter/persistence/seller/entity/SellerEntity$SellerStatus;", "findByUserId", "userId", "music"})
public abstract interface SellerRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.seller.entity.SellerEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.seller.entity.SellerEntity findByUserId(long userId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.seller.entity.SellerEntity findByBusinessNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String businessNumber);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.seller.entity.SellerEntity> findByStatus(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.seller.entity.SellerEntity.SellerStatus status, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.seller.entity.SellerEntity> findByCompanyNameContaining(@org.jetbrains.annotations.NotNull()
    java.lang.String companyName, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.seller.entity.SellerEntity> findByContactEmailContaining(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.seller.entity.SellerEntity findByContactPhone(@org.jetbrains.annotations.NotNull()
    java.lang.String phone);
}