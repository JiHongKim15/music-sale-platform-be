package com.music.sale.domain.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Store 도메인 테스트")
class StoreTest {

    @Test
    @DisplayName("Store 객체 생성 테스트")
    void createStore() {
        // given
        Long storeId = 1L;

        // when
        Store store = new Store(storeId);

        // then
        assertThat(store.getId()).isEqualTo(storeId);
    }

    @Test
    @DisplayName("Store 객체 equals 테스트")
    void storeEquals() {
        // given
        Store store1 = new Store(1L);
        Store store2 = new Store(1L);
        Store store3 = new Store(2L);

        // when & then
        assertThat(store1).isEqualTo(store2);
        assertThat(store1).isNotEqualTo(store3);
    }

    @Test
    @DisplayName("Store 객체 hashCode 테스트")
    void storeHashCode() {
        // given
        Store store1 = new Store(1L);
        Store store2 = new Store(1L);

        // when & then
        assertThat(store1.hashCode()).isEqualTo(store2.hashCode());
    }

    @Test
    @DisplayName("Store 객체 toString 테스트")
    void storeToString() {
        // given
        Store store = new Store(1L);

        // when
        String result = store.toString();

        // then
        assertThat(result).contains("Store{");
        assertThat(result).contains("id=1");
    }
}