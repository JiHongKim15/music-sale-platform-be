package com.music.sale.persistence.store.mapper;

import com.music.sale.domain.store.Store;
import com.music.sale.persistence.store.entity.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StorePersistenceMapper 테스트")
class StorePersistenceMapperTest {

    private StorePersistenceMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StorePersistenceMapper();
    }

    @Test
    @DisplayName("Entity에서 Domain으로 변환")
    void toDomain() {
        // given
        StoreEntity entity = new StoreEntity(
            1L,
            "Test Store",
            "Test Description",
            "12345",
            "Test Address",
            "Detail Address",
            37.5665,
            126.9780,
            "010-1234-5678",
            "123-45-67890",
            "http://test-image.com",
            StoreEntity.StoreStatus.ACTIVE,
            1L
        );

        // when
        Store domain = mapper.toDomain(entity);

        // then
        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(entity.getId());
    }

    @Test
    @DisplayName("Domain에서 Entity로 변환")
    void toEntity() {
        // given
        Store domain = new Store(1L);

        // when
        StoreEntity entity = mapper.toEntity(domain);

        // then
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(domain.getId());
        assertThat(entity.getName()).isEqualTo("Default Store");
        assertThat(entity.getDescription()).isEqualTo("Default Description");
        assertThat(entity.getZipcode()).isEqualTo("00000");
        assertThat(entity.getBaseAddress()).isEqualTo("Default Address");
        assertThat(entity.getDetailAddress()).isNull();
        assertThat(entity.getLatitude()).isNull();
        assertThat(entity.getLongitude()).isNull();
        assertThat(entity.getContactNumber()).isEqualTo("000-0000-0000");
        assertThat(entity.getBusinessNumber()).isEqualTo("000-00-00000");
        assertThat(entity.getImageUrl()).isNull();
        assertThat(entity.getStatus()).isEqualTo(StoreEntity.StoreStatus.ACTIVE);
        assertThat(entity.getSellerId()).isEqualTo(1L);
    }
}