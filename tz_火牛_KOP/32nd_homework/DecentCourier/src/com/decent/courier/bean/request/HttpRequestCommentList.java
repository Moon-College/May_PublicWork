package com.decent.courier.bean.request;

public class HttpRequestCommentList implements HttpParams {
	private Integer currentPage;
	private Integer itemsPerPage;
	private String startTime;
	private String endTime;

	public HttpRequestCommentList() {
		super();
	}

	public HttpRequestCommentList(Integer currentPage, Integer itemsPerPage,
			String startTime, String endTime) {
		super();
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public HttpRequestCommentList(Integer currentPage, Integer itemsPerPage) {
		super();
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
