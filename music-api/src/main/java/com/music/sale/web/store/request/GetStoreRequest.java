package com.music.sale.web.store.request;

import com.music.sale.common.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class GetStoreRequest {
    private int pageNumber = 1;
    private int pageSize = 10;
    private String sortBy = "createdAt";
    private SortDirection sortDirection = SortDirection.DESC;

    // 기본 생성자
    public GetStoreRequest() {}

    // 모든 필드 생성자
    public GetStoreRequest(int pageNumber, int pageSize, String sortBy, SortDirection sortDirection) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public PageRequest toPageRequest() {
        String sortProperty = (sortBy != null && !sortBy.isEmpty()) ? sortBy : "id";

        Sort.Direction direction = (sortDirection == SortDirection.ASC)
            ? Sort.Direction.ASC
            : Sort.Direction.DESC;

        int actualPageNumber = (pageNumber < 1) ? 0 : pageNumber - 1;
        int actualPageSize = (pageSize <= 0) ? 10 : pageSize;

        return PageRequest.of(actualPageNumber, actualPageSize, Sort.by(direction, sortProperty));
    }

    // Getters and Setters
    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public SortDirection getSortDirection() { return sortDirection; }
    public void setSortDirection(SortDirection sortDirection) { this.sortDirection = sortDirection; }
}
