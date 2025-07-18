package com.music.sale.persistence.store

import com.music.sale.application.store.port.outport.StorePort
import com.music.sale.domain.store.Store
import com.music.sale.persistence.store.mapper.StorePersistenceMapper
import com.music.sale.persistence.store.repository.StoreRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
open class StorePersistenceAdapter(
        private val storeRepository: StoreRepository,
        private val mapper: StorePersistenceMapper,
) : StorePort {
    override fun findById(id: Long): Store {
        return storeRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)
    }

    override fun save(store: Store): Store {
        val storeEntity = mapper.toEntity(store)
        val savedStore = storeRepository.save(storeEntity)
        return mapper.toDomain(savedStore)
    }
}
