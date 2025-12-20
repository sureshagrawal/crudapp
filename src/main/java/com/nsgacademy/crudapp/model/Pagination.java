package com.nsgacademy.crudapp.model;

public class Pagination {
    private int page;
    private int pageSize;

    public Pagination(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset(){
        return (page-1) * pageSize;
    }
}
