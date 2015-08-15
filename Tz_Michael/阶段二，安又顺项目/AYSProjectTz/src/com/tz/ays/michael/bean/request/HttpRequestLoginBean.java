package com.tz.ays.michael.bean.request;
/**
 * 登录时的模板对象
 * @author michael
 *
 */
public class HttpRequestLoginBean implements IHttpParams{

	/**用户名*/
	private String userName;
	/**用户密码*/
	private String password;
	
	public HttpRequestLoginBean(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public HttpRequestLoginBean() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
