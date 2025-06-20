package com.music.sale.application.store.port.out

import com.music.sale.domain.store.model.Store

interface StorePort {
    fun findStoreById(id: Long): Store
} 