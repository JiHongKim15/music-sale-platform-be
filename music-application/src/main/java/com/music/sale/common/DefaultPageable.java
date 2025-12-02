// Copyright (C) 2024 Your Name or Company
package com.music.sale.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DefaultPageable implements Pageable {
  @Builder.Default Integer pageNumber = 1;
  @Builder.Default Integer pageSize = 10;
  String sort;
  SortDirection sortDirection;

  @Override
  public int getPageNumber() {
    return pageNumber;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public String getSort() {
    return sort;
  }

  @Override
  public SortDirection getSortDirection() {
    return sortDirection;
  }
}
