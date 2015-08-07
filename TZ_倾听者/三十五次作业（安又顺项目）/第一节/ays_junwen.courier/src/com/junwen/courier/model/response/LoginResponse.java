package com.junwen.courier.model.response;

public class LoginResponse extends HttpResult {

	/**
	 * 名字
	 */
	private String name;
	/**
	 * 用户最近一次登陆时间
	 */
	private String lastLoginTime;
	/**
	 * 用户积分
	 */
	private float score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	
}
