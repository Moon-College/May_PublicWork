package com.tz.ays.michael.bean.response;
/**
 * 所有(正常)返回对象的基类
 * @author michael
 *
 */
public class HttpBaseResult {

	/**状态码：0成功，其他失败*/
	private int ret;
	/**返回内容主题*/
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
	@Override
	public String toString() {
		return "HttpBaseResult [ret=" + ret + ", msg=" + msg + "]";
	}
}
