package com.tz.asy.http.common;

import java.util.List;

/**
 * 业务员标记页数
 * Created by Administrator on 2015/5/19.
 */
public class CourierPagination {

    private Integer currentPage;
    private Integer lastPage;
    private Integer itemsPerPage;
    private Integer totalItems;
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }


    @Override
    public String toString() {
        return "CourierPagination{" +
                "currentPage=" + currentPage +
                ", lastPage=" + lastPage +
                ", itemsPerPage=" + itemsPerPage +
                ", totalItems=" + totalItems +
                '}';
    }
}
