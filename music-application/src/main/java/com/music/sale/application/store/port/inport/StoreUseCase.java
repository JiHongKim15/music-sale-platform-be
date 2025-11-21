package com.music.sale.application.store.port.inport;

import com.music.sale.application.store.dto.CreateStoreInput;
import com.music.sale.application.store.dto.StoreOutput;
import com.music.sale.application.store.dto.UpdateStoreInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StoreUseCase {

    Page<StoreOutput> getStores(PageRequest pageable);

    StoreOutput getStoreById(Long id);

    StoreOutput createStore(CreateStoreInput input);

    StoreOutput updateStore(UpdateStoreInput input);

    void deleteStore(Long id);
}
