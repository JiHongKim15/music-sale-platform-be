package com.music.sale.web.product.response;

import lombok.Builder;

@Builder
public record ProductResponse(
    Long id, String title, String content, String storeName, Long sellerId, Long storeId) {}
