package com.music.sale.application.category.service;

import com.music.sale.application.category.port.inport.CategoryQueryUseCase;
import com.music.sale.application.category.port.outport.CategoryQueryPort;
import com.music.sale.domain.category.Category;
import com.music.sale.domain.category.enums.CategoryType;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryQueryService implements CategoryQueryUseCase {
  private final CategoryQueryPort categoryQueryPort;

  public CategoryQueryService(CategoryQueryPort categoryQueryPort) {
    this.categoryQueryPort = categoryQueryPort;
  }

  @Override
  public Category getCategoryById(Long id) {
    return categoryQueryPort.getCategoryById(id);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryQueryPort.findAll();
  }

  @Override
  public List<Category> getCategoriesByType(CategoryType type) {
    return categoryQueryPort.findByType(type);
  }

  @Override
  public List<Category> getRootCategories() {
    return categoryQueryPort.findRootCategories();
  }

  @Override
  public List<Category> getCategoriesByParentId(Long parentId) {
    return categoryQueryPort.findByParentId(parentId);
  }
}
