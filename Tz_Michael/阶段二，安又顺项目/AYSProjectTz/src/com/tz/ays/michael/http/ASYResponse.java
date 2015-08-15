package com.tz.ays.michael.http;

import java.util.Arrays;

import org.apache.http.Header;

/**
 * 返回对象的最外层基类
 * @author michael
 *
 */
public class ASYResponse {

	/**正常返回时的对象值*/
	private String result;
	/**发生异常时的对象值*/
	private Exception exception;
	/**服务器返回的响应头*/
	private Header[] headers;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	@Override
	public String toString() {
		return "ASYResponse [result=" + result + ", exception=" + exception
				+ ", headers=" + Arrays.toString(headers) + "]";
	}
}
