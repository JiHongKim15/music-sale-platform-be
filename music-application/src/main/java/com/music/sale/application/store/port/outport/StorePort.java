package com.music.sale.application.store.port.outport;

import com.music.sale.domain.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StorePort {
    Store findById(Long id);  // null 반환 가능 (Kotlin nullable과 동일)

    Page<Store> findAll(PageRequest pageable);

    Store save(Store store);

    void deleteById(Long id);
}