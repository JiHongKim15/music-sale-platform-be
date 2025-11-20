package com.music.sale.web.category.mapper;

import com.music.sale.domain.category.Category;
import com.music.sale.web.category.response.CategoryQueryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryQueryWebMapper {

    public CategoryQueryResponse toResponse(Category category) {
        return new CategoryQueryResponse(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getParent(),
                category.getPath(),
                category.getDepth(),
                category.isActive());
    }
}

