// Copyright (C) 2024 Your Name or Company
package com.music.sale.common

interface Pageable {
    val pageNumber: Int
    val pageSize: Int
    val sort: String?
    val sortDirection: SortDirection?
}
