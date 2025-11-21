package com.music.sale.web.category;

import com.music.sale.application.category.port.inport.CategoryQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.category.CategoryType;
import com.music.sale.web.category.mapper.CategoryQueryWebMapper;
import com.music.sale.web.category.response.CategoryQueryResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryQueryController {

    private final CategoryQueryUseCase categoryQueryUseCase;
    private final CategoryQueryWebMapper categoryQueryWebMapper;

    public CategoryQueryController(
            CategoryQueryUseCase categoryQueryUseCase,
            CategoryQueryWebMapper categoryQueryWebMapper) {
        this.categoryQueryUseCase = categoryQueryUseCase;
        this.categoryQueryWebMapper = categoryQueryWebMapper;
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryQueryResponse> getCategoryById(@PathVariable Long id) {
        var category = categoryQueryUseCase.getCategoryById(id);
        return ApiResponse.success(categoryQueryWebMapper.toResponse(category), "CATEGORY_FOUND");
    }

    @GetMapping("/all")
    public ApiResponse<List<CategoryQueryResponse>> getAllCategories() {
        var categories = categoryQueryUseCase.getAllCategories();
        return ApiResponse.success(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()),
                "CATEGORIES_FOUND");
    }

    @GetMapping("/type")
    public ApiResponse<List<CategoryQueryResponse>> getCategoriesByType(
            @RequestParam CategoryType type) {
        var categories = categoryQueryUseCase.getCategoriesByType(type);
        return ApiResponse.success(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()),
                "CATEGORIES_BY_TYPE_FOUND");
    }

    @GetMapping("/root")
    public ApiResponse<List<CategoryQueryResponse>> getRootCategories() {
        var categories = categoryQueryUseCase.getRootCategories();
        return ApiResponse.success(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()),
                "ROOT_CATEGORIES_FOUND");
    }

    @GetMapping("/parent/{parentId}")
    public ApiResponse<List<CategoryQueryResponse>> getCategoriesByParentId(
            @PathVariable Long parentId) {
        var categories = categoryQueryUseCase.getCategoriesByParentId(parentId);
        return ApiResponse.success(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()),
                "CHILD_CATEGORIES_FOUND");
    }
}

