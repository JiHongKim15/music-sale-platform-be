package com.music.sale.web.category.request;

import com.music.sale.domain.category.enums.CategoryType;

public record CreateCategoryRequest(String name, CategoryType type, Long parentId) {}
