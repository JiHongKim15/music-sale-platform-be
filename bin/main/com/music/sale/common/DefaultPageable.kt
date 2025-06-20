package com.music.sale.common

data class DefaultPageable(
    override val pageNumber: Int = 1,
    override val pageSize: Int = 10,
    override val sort: String? = null,
    override val sortDirection: SortDirection? = null
) : Pageable 