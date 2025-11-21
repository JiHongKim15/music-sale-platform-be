// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.port.outport;

import com.music.sale.domain.category.Category;
import com.music.sale.domain.category.CategoryType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryQueryPort {
    Category getCategoryById(Long id);

    List<Category> findAll();

    Category findById(Long id);

    List<Category> findByType(CategoryType type);

    List<Category> findRootCategories();

    List<Category> findByParentId(Long parentId);

    Category save(Category category);

    void delete(Long id);
}

