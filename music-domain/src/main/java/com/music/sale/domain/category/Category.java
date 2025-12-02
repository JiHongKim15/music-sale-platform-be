// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.category;

import com.music.sale.domain.category.enums.CategoryType;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Category {
  private final Long id;
  private final CategoryType type;
  private final List<Category> children = new ArrayList<>();
  private final String name;
  private Category parent;
  private String path;
  private int depth;
  private boolean isActive;

  public Category(
      Long id,
      String name,
      CategoryType type,
      Category parent,
      String path,
      int depth,
      boolean isActive) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.parent = parent;
    this.path = path;
    this.depth = depth;
    this.isActive = isActive;
  }

  public static Category createRoot(String name, CategoryType type) {
    return new Category(0L, name, type, null, "", 0, true);
  }

  public void addChild(Category child) {
    if (child.getDepth() != this.depth + 1) {
      throw new IllegalArgumentException("자식 카테고리의 깊이는 부모보다 1 커야 합니다");
    }
    if (child.getType() != this.type) {
      throw new IllegalArgumentException("자식 카테고리는 부모와 동일한 타입이어야 합니다");
    }
    children.add(child);
  }

  public void removeChild(Category child) {
    children.remove(child);
  }

  public void moveTo(Category newParent) {
    if (newParent == this) {
      throw new IllegalArgumentException("자기 자신을 부모로 지정할 수 없습니다");
    }
    if (newParent != null && newParent.isDescendantOf(this)) {
      throw new IllegalArgumentException("자식 카테고리를 부모로 지정할 수 없습니다");
    }
    if (newParent != null && newParent.getType() != this.type) {
      throw new IllegalArgumentException("이동할 카테고리는 동일한 타입이어야 합니다");
    }

    if (parent != null) {
      parent.removeChild(this);
    }
    if (newParent != null) {
      newParent.addChild(this);
    }
    this.parent = newParent;
    updatePath();
  }

  private void updatePath() {
    if (parent == null) {
      this.path = "/" + id;
    } else {
      this.path = parent.getPath() + "/" + id;
    }
    this.depth = (int) path.chars().filter(ch -> ch == '/').count() - 1;
  }

  public boolean isDescendantOf(Category category) {
    Category current = parent;
    while (current != null) {
      if (current == category) {
        return true;
      }
      current = current.getParent();
    }
    return false;
  }

  public void deactivate() {
    this.isActive = false;
  }

  public void activate() {
    this.isActive = true;
  }
}
