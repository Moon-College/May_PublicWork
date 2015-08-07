package com.junwen.courier.model.response;

public class HttpResult {

	/**
	 * 成功或者失败
	 */
	private int ret;

	/**
	 * 消息
	 */
	private String msg;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
