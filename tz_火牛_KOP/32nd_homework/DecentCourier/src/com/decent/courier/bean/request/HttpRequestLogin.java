package com.decent.courier.bean.request;

/**
 * 用户登录的http请求参数类
 * 
 * @author K450J
 * 
 */
public class HttpRequestLogin implements HttpParams {
	private String userName;
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
