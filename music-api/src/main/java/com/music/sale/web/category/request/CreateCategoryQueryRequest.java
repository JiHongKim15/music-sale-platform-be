package com.music.sale.web.category.request;

import com.music.sale.domain.category.CategoryType;

public record CreateCategoryQueryRequest(
        String name,
        CategoryType type,
        Long parentId) {}

