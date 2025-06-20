package com.music.sale.common;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/music/sale/common/Pageable;", "", "pageNumber", "", "getPageNumber", "()I", "pageSize", "getPageSize", "sort", "", "getSort", "()Ljava/lang/String;", "sortDirection", "Lcom/music/sale/common/SortDirection;", "getSortDirection", "()Lcom/music/sale/common/SortDirection;", "music"})
public abstract interface Pageable {
    
    public abstract int getPageNumber();
    
    public abstract int getPageSize();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getSort();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.common.SortDirection getSortDirection();
}