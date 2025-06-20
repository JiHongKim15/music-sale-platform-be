// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.store.mapper

import com.music.sale.adapter.persistence.store.entity.StoreEntity
import com.music.sale.domain.store.model.Store
import org.springframework.stereotype.Component

@Component
class StorePersistenceMapper {
    fun toDomain(entity: StoreEntity): Store {
        TODO("Not yet implemented")
    }

    fun toEntity(store: Store): StoreEntity {
        TODO("Not yet implemented")
    }
}
