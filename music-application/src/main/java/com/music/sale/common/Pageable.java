// Copyright (C) 2024 Your Name or Company
package com.music.sale.common;

public interface Pageable {
    int getPageNumber();
    int getPageSize();
    String getSort();
    SortDirection getSortDirection();
}

