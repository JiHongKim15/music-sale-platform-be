package com.music.sale.adapter.persistence.store.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH&J4\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u00142\b\b\u0001\u0010\u0016\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tH\'J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0019"}, d2 = {"Lcom/music/sale/adapter/persistence/store/repository/StoreRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/store/entity/StoreEntity;", "", "findByBaseAddressContaining", "Lorg/springframework/data/domain/Page;", "address", "", "pageable", "Lorg/springframework/data/domain/Pageable;", "findByNameContaining", "name", "findBySellerId", "", "sellerId", "findByStatus", "status", "Lcom/music/sale/adapter/persistence/store/entity/StoreEntity$StoreStatus;", "findStoresWithinDistance", "latitude", "", "longitude", "distanceInKm", "searchStores", "keyword", "music"})
public abstract interface StoreRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.store.entity.StoreEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.store.entity.StoreEntity> findBySellerId(long sellerId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.store.entity.StoreEntity> findByStatus(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus status, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.store.entity.StoreEntity> findByNameContaining(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.store.entity.StoreEntity> findByBaseAddressContaining(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.springframework.data.jpa.repository.Query(value = "\n        SELECT s FROM StoreEntity s\n        WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *\n               cos(radians(s.longitude) - radians(:longitude)) +\n               sin(radians(:latitude)) * sin(radians(s.latitude)))) <= :distance\n        AND s.status = com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus.ACTIVE\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.store.entity.StoreEntity> findStoresWithinDistance(@org.springframework.data.repository.query.Param(value = "latitude")
    double latitude, @org.springframework.data.repository.query.Param(value = "longitude")
    double longitude, @org.springframework.data.repository.query.Param(value = "distance")
    double distanceInKm, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
    
    @org.springframework.data.jpa.repository.Query(value = "\n        SELECT s FROM StoreEntity s\n        WHERE (:keyword IS NULL OR\n               s.name LIKE %:keyword% OR\n               s.description LIKE %:keyword% OR\n               s.baseAddress LIKE %:keyword%)\n        AND s.status = com.music.sale.adapter.persistence.store.entity.StoreEntity.StoreStatus.ACTIVE\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract org.springframework.data.domain.Page<com.music.sale.adapter.persistence.store.entity.StoreEntity> searchStores(@org.springframework.data.repository.query.Param(value = "keyword")
    @org.jetbrains.annotations.Nullable()
    java.lang.String keyword, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable);
}