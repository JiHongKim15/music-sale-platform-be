package com.music.sale.web.category.mapper;

import com.music.sale.domain.category.Category;
import com.music.sale.web.category.response.CategoryQueryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryQueryWebMapper {

    public CategoryQueryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }

        List<CategoryQueryResponse> childrenResponses =
                category.getChildren().stream()
                        .map(this::toResponse) // Recursive call
                        .collect(java.util.stream.Collectors.toList());

        return new CategoryQueryResponse(
                category.getId(),
                category.getName(),
                category.getName(),
                null,
                category.getType().name(),
                category.getParent() != null ? category.getParent().getId() : null,
                childrenResponses
        );
    }
}

