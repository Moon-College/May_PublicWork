package com.junwen.courier.http;

import android.preference.PreferenceActivity.Header;

import com.junwen.courier.model.response.HttpResult;

public class HttpResponse {

	/**
	 * 异常
	 */
	private Exception exception;
	/**
	 * 结果实体
	 */
	private HttpResult result;
	/**
	 * 头部信息数组
	 */
	private Header[] headers;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public HttpResult getResult() {
		return result;
	}

	public void setResult(HttpResult result) {
		this.result = result;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

}
