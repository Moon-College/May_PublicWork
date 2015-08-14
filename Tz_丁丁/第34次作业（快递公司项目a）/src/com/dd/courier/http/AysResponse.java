package com.dd.courier.http;

import org.apache.http.Header;

import com.dd.courier.bean.result.HttpBaseResult;

public class AysResponse {
	private Exception exception;
	private HttpBaseResult result;
	private Header[] headers;
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public HttpBaseResult getResult() {
		return result;
	}
	public void setResult(HttpBaseResult result) {
		this.result = result;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
}
