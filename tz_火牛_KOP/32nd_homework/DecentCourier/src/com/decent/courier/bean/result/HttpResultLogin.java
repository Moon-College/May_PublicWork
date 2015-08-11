package com.decent.courier.bean.result;

/**
 * http登录结果，根据pdf文档对应的字段编写
 * 
 * @author K450J
 * 
 */
public class HttpResultLogin extends HttpBaseResult {
	/**
	 * 用户名字
	 */
	private String name;
	/**
	 * 最近一次登录时间
	 */
	private String lastLoginTime;
	/**
	 * 业务员总平均分
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
