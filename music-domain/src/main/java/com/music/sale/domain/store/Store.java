package com.music.sale.domain.store;

public class Store {
    private final Long id;

    public Store(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Store store = (Store) obj;
        return id != null ? id.equals(store.id) : store.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Store{" +
               "id=" + id +
               '}';
    }
}