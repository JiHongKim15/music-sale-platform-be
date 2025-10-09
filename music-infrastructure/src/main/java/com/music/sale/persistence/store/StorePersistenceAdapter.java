package com.music.sale.persistence.store;

import com.music.sale.application.store.port.outport.StorePort;
import com.music.sale.domain.store.Store;
import com.music.sale.persistence.store.mapper.StorePersistenceMapper;
import com.music.sale.persistence.store.repository.StoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StorePersistenceAdapter implements StorePort {

    private final StoreRepository storeRepository;
    private final StorePersistenceMapper mapper;

    public StorePersistenceAdapter(StoreRepository storeRepository, StorePersistenceMapper mapper) {
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    @Override
    public Store findById(Long id) {
        return storeRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Store save(Store store) {
        var storeEntity = mapper.toEntity(store);
        var savedStore = storeRepository.save(storeEntity);
        return mapper.toDomain(savedStore);
    }
}