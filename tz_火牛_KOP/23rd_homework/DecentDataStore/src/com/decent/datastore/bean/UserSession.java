package com.decent.datastore.bean;

import android.app.Application;

/*
 * 类似用户登录的session
 */
public class UserSession extends Application {
	private long loginTimeMillis;
	private Users loginUser;

	public long getLoginTimeMillis() {
		return loginTimeMillis;
	}

	public void setLoginTimeMillis(long loginTimeMillis) {
		this.loginTimeMillis = loginTimeMillis;
	}

	public Users getLoginUser() {
		return loginUser;
	}

	/**
	 * 记录一下登录的信息
	 * @param loginUser 登录用户信息
	 */
	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}

}
