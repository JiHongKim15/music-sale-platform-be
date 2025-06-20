
@org.springframework.stereotype.Component()
@org.springframework.transaction.annotation.Transactional()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"LStorePersistenceAdapter;", "Lcom/music/sale/application/store/port/outport/StorePort;", "storeRepository", "Lcom/music/sale/adapter/persistence/store/repository/StoreRepository;", "mapper", "Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;", "(Lcom/music/sale/adapter/persistence/store/repository/StoreRepository;Lcom/music/sale/adapter/persistence/store/mapper/StorePersistenceMapper;)V", "findById", "Lcom/music/sale/domain/store/model/Store;", "id", "", "save", "store", "music"})
public class StorePersistenceAdapter implements com.music.sale.application.store.port.outport.StorePort {
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
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.store.model.Store findById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.store.model.Store save(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.store.model.Store store) {
        return null;
    }
}