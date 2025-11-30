package com.music.sale.web.product.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        Long id,
        String title,
        String content,
        String storeName,
        Long sellerId,
        Long storeId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
