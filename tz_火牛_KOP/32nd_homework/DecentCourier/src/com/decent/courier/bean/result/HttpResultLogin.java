package com.decent.courier.bean.result;

/**
 * http��¼���������pdf�ĵ���Ӧ���ֶα�д
 * 
 * @author K450J
 * 
 */
public class HttpResultLogin extends HttpBaseResult {
	/**
	 * �û�����
	 */
	private String name;
	/**
	 * ���һ�ε�¼ʱ��
	 */
	private String lastLoginTime;
	/**
	 * ҵ��Ա��ƽ����
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
