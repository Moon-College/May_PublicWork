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
	private HttpBaseResult httpBaseResult;
	private Header[] headers;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public HttpBaseResult getHttpBaseResult() {
		return httpBaseResult;
	}

	public void setHttpBaseResult(HttpBaseResult httpBaseResult) {
		this.httpBaseResult = httpBaseResult;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

}
