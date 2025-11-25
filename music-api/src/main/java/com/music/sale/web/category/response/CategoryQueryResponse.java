package com.music.sale.web.category.response;

import java.util.List;

public record CategoryQueryResponse(
        Long id,
        String name,
        String displayName,
        Integer ordering,
        String type,
        Long parentId,
        List<CategoryQueryResponse> children
) {
}
