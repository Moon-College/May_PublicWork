package com.decent.courier.http;

import org.apache.http.Header;

import com.decent.courier.bean.result.HttpBaseResult;

/**
 * һ��Http��Ӧ�������������룬http ͷ�������쳣��Ϣ
 * 
 * @author K450J
 * 
 */
public class AsyResponse {
	private Exception exception;
	private String result;
	private Header[] headers;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

}
