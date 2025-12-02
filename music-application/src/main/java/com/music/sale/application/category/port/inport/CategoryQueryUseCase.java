// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.port.inport;

import com.music.sale.domain.category.Category;
import com.music.sale.domain.category.enums.CategoryType;
import java.util.List;

public interface CategoryQueryUseCase {
  Category getCategoryById(Long id);

  List<Category> getAllCategories();

  List<Category> getCategoriesByType(CategoryType type);

  List<Category> getRootCategories();

  List<Category> getCategoriesByParentId(Long parentId);
}
