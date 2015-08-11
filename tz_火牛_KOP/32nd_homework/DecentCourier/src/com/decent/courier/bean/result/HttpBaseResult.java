package com.decent.courier.bean.result;

/**
 * http返回接口的基本类,看了同城app的所有返回接口说明都是有ret和msg两个基本的字段的
 * @author K450J
 *
 */
public class HttpBaseResult {
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
