package com.music.sale.domain.image;

import java.util.Objects;

public record ProductImage(
    Long id,
    String fileName,
    String fileType,
    long fileSize,
    boolean isThumbnail,
    int imageOrder
) {

        /**
         * 비즈니스 규칙: 썸네일은 하나만
         */
        public boolean canBeThumbnail() {
            return isThumbnail;
        }

        /**
         * 파일 확장자 추출
         */
        public String extension() {
            int dotIndex = fileName.lastIndexOf('.');
            return dotIndex > 0 ? fileName.substring(dotIndex + 1) : "";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ProductImage image = (ProductImage) obj;
            return Objects.equals(id, image.id);
        }

        @Override
        public String toString() {
            return "ProductImage{" +
                    "id=" + id +
                    ", fileName='" + fileName + '\'' +
                    ", isThumbnail=" + isThumbnail +
                    '}';
        }

}
