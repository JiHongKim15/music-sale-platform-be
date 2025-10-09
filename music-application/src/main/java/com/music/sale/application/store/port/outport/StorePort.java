package com.music.sale.application.store.port.outport;

import com.music.sale.domain.store.Store;

public interface StorePort {
    Store findById(Long id);  // null 반환 가능 (Kotlin nullable과 동일)

    Store save(Store store);
}