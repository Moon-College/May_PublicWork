package com.dd.courier.bean.result;

public class HttpBaseResult {
	//这是返回实体类的基类
	private int ret;
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
