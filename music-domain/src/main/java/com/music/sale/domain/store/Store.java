package com.music.sale.domain.store;

import java.util.Objects;

public record Store(Long id) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Store store = (Store) obj;
        return Objects.equals(id, store.id);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                '}';
    }
}
