package com.junwen.courier.http;

import com.junwen.courier.model.request.HttpParams;

public class HttpRequest implements IHttpRequest {

	/**
	 * 复杂上传
	 */
	@Override
	public HttpResponse doMultiQueryByPost(String url, HttpParams httpParams,
			boolean isCook) {
		HttpResponse response = new HttpResponse();
		return response;
	}

	/**
	 * 带有COOK得POST网络请求
	 */
	@Override
	public HttpResponse doQueryByPost(String url, HttpParams httpParams,
			boolean isCook) {
		HttpResponse response = new HttpResponse();
		return response;
	}

	/**
	 * 带有参数的POST网络请求
	 */
	@Override
	public HttpResponse doQueryByPost(String url, HttpParams httpParams) {
		return doQueryByPost(url, httpParams, false);
	}

	/**
	 * 带有URL的POST网络请求
	 */
	@Override
	public HttpResponse doQueryByPost(String url) {
		return doQueryByPost(url, null);
	}

	/**
	 * 带有COOK的GET请求
	 */
	@Override
	public HttpResponse doQueryByGet(String url, HttpParams httpParams,
			boolean isCook) {
		HttpResponse response = new HttpResponse();
		return response;
	}

	/**
	 * 带有参数的GET请求
	 */
	@Override
	public HttpResponse doQueryByGet(String url, HttpParams httpParams) {
		return doQueryByGet(url, httpParams, false);
	}

	/**
	 * 带有URL的GET请求
	 */
	@Override
	public HttpResponse doQueryByGet(String url) {
		return doQueryByGet(url, null);
	}

}
