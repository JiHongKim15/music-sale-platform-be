package com.music.sale.adapter.persistence.store

import com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper
import com.music.sale.adapter.persistence.store.repository.StoreRepository
import com.music.sale.application.store.port.out.StorePort
import com.music.sale.domain.store.model.Store
import org.springframework.stereotype.Component

@Component
class StorePersistenceAdapter(
    private val storeRepository: StoreRepository,
    private val mapper: StorePersistenceMapper
) : StorePort {
    override fun findStoreById(id: Long): Store {
        return mapper.toDomain(storeRepository.getReferenceById(id))
    }
}
