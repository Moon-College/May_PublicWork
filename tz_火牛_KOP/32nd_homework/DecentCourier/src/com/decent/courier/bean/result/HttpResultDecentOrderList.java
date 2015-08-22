package com.decent.courier.bean.result;

import com.decent.courier.common.DecentOrderPagination;

public class HttpResultDecentOrderList extends HttpBaseResult {
	private DecentOrderPagination pagination;

	public DecentOrderPagination getPagination() {
		return pagination;
	}

	public void setPagination(DecentOrderPagination pagination) {
		this.pagination = pagination;
	}

}
