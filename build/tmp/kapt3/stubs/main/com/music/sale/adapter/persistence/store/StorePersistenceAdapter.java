package com.music.sale.adapter.persistence.store;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/music/sale/adapter/persistence/store/StorePersistenceAdapter;", "Lcom/music/sale/application/store/port/out/StorePort;", "storeRepository", "Lcom/music/sale/adapter/persistence/store/repository/StoreRepository;", "mapper", "Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;", "(Lcom/music/sale/adapter/persistence/store/repository/StoreRepository;Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;)V", "findStoreById", "Lcom/music/sale/domain/store/model/Store;", "id", "", "music"})
public class StorePersistenceAdapter implements com.music.sale.application.store.port.out.StorePort {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.store.repository.StoreRepository storeRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper mapper = null;
    
    public StorePersistenceAdapter(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.store.repository.StoreRepository storeRepository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper mapper) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.store.model.Store findStoreById(long id) {
        return null;
    }
}