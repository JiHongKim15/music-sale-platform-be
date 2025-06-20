package com.music.sale.common

interface Pageable {
    val pageNumber: Int
    val pageSize: Int
    val sort: String?
    val sortDirection: SortDirection?
}
