package com.decent.courier.bean.result;

import com.decent.courier.common.DecentCommentPagination;

public class HttpResultCommentList extends HttpBaseResult {
	private DecentCommentPagination pagination;

	public DecentCommentPagination getPagination() {
		return pagination;
	}

	public void setPagination(DecentCommentPagination pagination) {
		this.pagination = pagination;
	}
	
}
