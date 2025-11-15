package com.music.sale.persistence.store;

import com.music.sale.domain.store.Store;
import com.music.sale.persistence.store.entity.StoreEntity;
import com.music.sale.persistence.store.mapper.StorePersistenceMapper;
import com.music.sale.persistence.store.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("StorePersistenceAdapter 테스트")
class StorePersistenceAdapterTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StorePersistenceMapper mapper;

    @InjectMocks
    private StorePersistenceAdapter storePersistenceAdapter;

    private Store store;
    private StoreEntity storeEntity;

    @BeforeEach
    void setUp() {
        store = new Store(1L);
        storeEntity = new StoreEntity(
                1L,
                "Test Store",
                "Test Description",
                "12345",
                "Test Address",
                null,
                null,
                null,
                "010-1234-5678",
                "123-45-67890",
                null,
                StoreEntity.StoreStatus.ACTIVE,
                1L
        );
    }

    @Test
    @DisplayName("ID로 Store 조회 - 성공")
    void findById_Success() {
        // given
        Long storeId = 1L;
        given(storeRepository.findById(storeId)).willReturn(Optional.of(storeEntity));
        given(mapper.toDomain(storeEntity)).willReturn(store);

        // when
        Store result = storePersistenceAdapter.findById(storeId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(storeId);
        verify(storeRepository).findById(storeId);
        verify(mapper).toDomain(storeEntity);
    }

    @Test
    @DisplayName("ID로 Store 조회 - 존재하지 않음")
    void findById_NotFound() {
        // given
        Long storeId = 999L;
        given(storeRepository.findById(storeId)).willReturn(Optional.empty());

        // when
        Store result = storePersistenceAdapter.findById(storeId);

        // then
        assertThat(result).isNull();
        verify(storeRepository).findById(storeId);
    }

    @Test
    @DisplayName("Store 저장 - 성공")
    void save_Success() {
        // given
        given(mapper.toEntity(store)).willReturn(storeEntity);
        given(storeRepository.save(storeEntity)).willReturn(storeEntity);
        given(mapper.toDomain(storeEntity)).willReturn(store);

        // when
        Store result = storePersistenceAdapter.save(store);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(store.id());
        verify(mapper).toEntity(store);
        verify(storeRepository).save(storeEntity);
        verify(mapper).toDomain(storeEntity);
    }
}
