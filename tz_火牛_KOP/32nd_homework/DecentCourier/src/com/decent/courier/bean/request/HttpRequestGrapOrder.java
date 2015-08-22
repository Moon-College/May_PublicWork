package com.decent.courier.bean.request;

public class HttpRequestGrapOrder implements HttpParams {
	private long orderId;
	private String userName;
	private String password;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HttpRequestGrapOrder(long orderId, String userName, String password) {
		super();
		this.orderId = orderId;
		this.userName = userName;
		this.password = password;
	}

	public HttpRequestGrapOrder() {
		super();
	}

}
