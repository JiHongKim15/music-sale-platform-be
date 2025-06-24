// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.store.mapper

import com.music.sale.adapter.persistence.store.entity.StoreEntity
import com.music.sale.domain.store.model.Store
import org.springframework.stereotype.Component

@Component
class StorePersistenceMapper {
    fun toDomain(entity: StoreEntity): Store {
        return Store(
            id = entity.id,
        )
    }

    fun toEntity(store: Store): StoreEntity {
        return StoreEntity(
            id = store.id,
            name = "Default Store",
            description = "Default Description",
            zipcode = "00000",
            baseAddress = "Default Address",
            detailAddress = null,
            latitude = null,
            longitude = null,
            contactNumber = "000-0000-0000",
            businessNumber = "000-00-00000",
            imageUrl = null,
            status = StoreEntity.StoreStatus.ACTIVE,
            sellerId = 1L,
        )
    }
}
