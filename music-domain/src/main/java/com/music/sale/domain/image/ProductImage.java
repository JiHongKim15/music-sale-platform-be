package com.music.sale.domain.image;

import java.util.Objects;

public record ProductImage(Long id) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductImage image = (ProductImage) obj;
        return Objects.equals(id, image.id);
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + id +
            '}';
    }

} // class
