package com.music.sale.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductImage {
    private final Long id;
    private final Long productId;
    private final String url;
    private final boolean isThumbnail;
    private final int imageOrder;
    private final long fileSize;
    private final String fileName;
    private final String fileType;
}

