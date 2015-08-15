package com.tz.ays.michael.bean.response;
/**
 * 登录时服务器返回的对象模板类
 * @author michael
 *
 */
public class HttpResultLoginBean extends HttpBaseResult{

	/**当前用户*/
	private String name;
	/**上次登录时间*/
	private String lastLoginTime;
	/**积分*/
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
	@Override
	public String toString() {
		return "HttpResultLoginBean [name=" + name + ", lastLoginTime="
				+ lastLoginTime + ", score=" + score + "]";
	}
}
