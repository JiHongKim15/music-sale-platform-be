package com.music.sale.application.store.service;

import com.music.sale.application.store.dto.CreateStoreInput;
import com.music.sale.application.store.dto.StoreOutput;
import com.music.sale.application.store.dto.UpdateStoreInput;
import com.music.sale.application.store.port.inport.StoreUseCase;
import com.music.sale.application.store.port.outport.StorePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreService implements StoreUseCase {

    private static final Logger log = LoggerFactory.getLogger(StoreService.class);
    private final StorePort storePort;

    public StoreService(StorePort storePort) {
        this.storePort = storePort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StoreOutput> getStores(PageRequest pageable) {
        log.info("Getting stores with pageable: {}", pageable);
        // TODO: Implement get stores logic
        return Page.empty(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreOutput getStoreById(Long id) {
        log.info("Getting store by id: {}", id);
        // TODO: Implement get store by id logic
        return null;
    }

    @Override
    public StoreOutput createStore(CreateStoreInput input) {
        log.info("Creating store with input: {}", input);
        // TODO: Implement create store logic
        return null;
    }

    @Override
    public StoreOutput updateStore(UpdateStoreInput input) {
        log.info("Updating store with input: {}", input);
        // TODO: Implement update store logic
        return null;
    }

    @Override
    public void deleteStore(Long id) {
        log.info("Deleting store with id: {}", id);
        // TODO: Implement delete store logic
    }
}
