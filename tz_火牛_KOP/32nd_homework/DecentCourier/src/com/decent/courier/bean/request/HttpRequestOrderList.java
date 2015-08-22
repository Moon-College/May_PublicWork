package com.decent.courier.bean.request;

public class HttpRequestOrderList implements HttpParams {
	private Integer currentPage;// �ڼ�ҳ
	private Integer itemsPerPage;// ÿҳ��ʾ����Ŀ��
	private Integer status;// ����״̬ ֵ��ȷ��
	private Integer startTime;// ��ѯ��ʼ����
	private Integer endTime;// ��ѯ��������
	private Integer hasComment;// �Ƿ�������

	public HttpRequestOrderList(Integer currentPage, Integer itemsPerPage) {
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
	}

	public HttpRequestOrderList(Integer currentPage, Integer itemsPerPage,
			Integer status, Integer startTime, Integer endTime,
			Integer hasComment) {
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hasComment = hasComment;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer isHasComment() {
		return hasComment;
	}

	public void setHasComment(Integer hasComment) {
		this.hasComment = hasComment;
	}

	@Override
	public String toString() {
		return "HttpRequestOrderList [currentPage=" + currentPage
				+ ", itemsPerPage=" + itemsPerPage + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", hasComment=" + hasComment + "]";
	}


}
