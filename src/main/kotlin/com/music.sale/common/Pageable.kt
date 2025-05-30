package com.music.sale.common

import org.hibernate.query.SortDirection

interface Pageable {
    val pageNumber: Int
    val pageSize: Int
    val sort: String
    val sortDirection: SortDirection
}
