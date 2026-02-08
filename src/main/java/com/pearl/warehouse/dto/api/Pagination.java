package com.pearl.warehouse.dto.api;
import lombok.*;
@Getter
@Setter
public class Pagination {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public Pagination(int page, int size, long totalElements, int totalPages, boolean last) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}
