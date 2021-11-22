package io.riza.xtramile.patients.adaptersutils;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResult<T> {

    private List<T> data;
    private int totalPages;
    private long totalItems;
    private long currentPage;

    public void setData(List<T> collect) {
        this.data = collect;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
