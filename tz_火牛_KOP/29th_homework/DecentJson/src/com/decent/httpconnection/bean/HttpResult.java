package com.decent.httpconnection.bean;

public class HttpResult {
	/**
	 * url¥ÌŒÛ
	 */
	public static final int URL_ERROR = 8888;
	/**
	 * http∑√Œ ok
	 */
	public static final int HTTP_OK = 200;
	private int resultCode;
	private String content;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
