package com.decent.datastore.bean;

import android.app.Application;

/*
 * �����û���¼��session
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
	 * ��¼һ�µ�¼����Ϣ
	 * @param loginUser ��¼�û���Ϣ
	 */
	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}

}
