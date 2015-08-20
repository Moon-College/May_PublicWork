package com.tz.asy.bean.result;


import com.tz.asy.http.common.OrderPagination;

/**
 * Created by Administrator on 2015/5/20.
 */
public class ResultCourierOrders extends HttpBaseResult{

    private OrderPagination pagination;

    public OrderPagination getPagination() {
        return pagination;
    }

    public void setPagination(OrderPagination pagination) {
        this.pagination = pagination;
    }

}
