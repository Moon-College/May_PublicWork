package com.junwen.courier.model.request;

public class LoginRequest implements HttpParams {
	/**
	 * 账号
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;

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

}
