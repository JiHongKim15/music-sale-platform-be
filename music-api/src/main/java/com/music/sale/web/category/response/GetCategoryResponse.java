package com.music.sale.web.category.response;

import com.music.sale.domain.category.Category;
import com.music.sale.domain.category.enums.CategoryType;

public record GetCategoryResponse(
    Long id,
    String name,
    CategoryType type,
    Category parent,
    String path,
    Integer depth,
    Boolean isActive) {}
