package com.music.sale.persistence.store.mapper;

import com.music.sale.domain.store.Store;
import com.music.sale.persistence.store.entity.StoreEntity;
import org.springframework.stereotype.Component;

@Component
public class StorePersistenceMapper {

    public Store toDomain(StoreEntity entity) {
        return new Store(entity.getId());
    }

    public StoreEntity toEntity(Store store) {
        return new StoreEntity(
            store.getId(),
            "Default Store",
            "Default Description",
            "00000",
            "Default Address",
            null,
            null,
            null,
            "000-0000-0000",
            "000-00-00000",
            null,
            StoreEntity.StoreStatus.ACTIVE,
            1L
        );
    }
}