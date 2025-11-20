package com.music.sale.web.category;

import com.music.sale.application.category.port.inport.CategoryQueryUseCase;
import com.music.sale.domain.category.CategoryType;
import com.music.sale.web.category.mapper.CategoryQueryWebMapper;
import com.music.sale.web.category.response.CategoryQueryResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryQueryResponse> getCategoryById(@PathVariable Long id) {
        var category = categoryQueryUseCase.getCategoryById(id);
        return ResponseEntity.ok(categoryQueryWebMapper.toResponse(category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryQueryResponse>> getAllCategories() {
        var categories = categoryQueryUseCase.getAllCategories();
        return ResponseEntity.ok(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/type")
    public ResponseEntity<List<CategoryQueryResponse>> getCategoriesByType(
            @RequestParam CategoryType type) {
        var categories = categoryQueryUseCase.getCategoriesByType(type);
        return ResponseEntity.ok(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/root")
    public ResponseEntity<List<CategoryQueryResponse>> getRootCategories() {
        var categories = categoryQueryUseCase.getRootCategories();
        return ResponseEntity.ok(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CategoryQueryResponse>> getCategoriesByParentId(
            @PathVariable Long parentId) {
        var categories = categoryQueryUseCase.getCategoriesByParentId(parentId);
        return ResponseEntity.ok(
                categories.stream()
                        .map(categoryQueryWebMapper::toResponse)
                        .collect(Collectors.toList()));
    }
}

