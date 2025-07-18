package com.music.sale.application.store.port.outport

import com.music.sale.domain.store.Store

interface StorePort {
    fun findById(id: Long): Store?

    fun save(store: Store): Store
}
