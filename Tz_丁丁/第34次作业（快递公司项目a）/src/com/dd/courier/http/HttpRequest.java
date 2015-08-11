package com.dd.courier.http;

import com.dd.courier.bean.request.HttpParams;

public class HttpRequest implements IHttpRequest{

	/**
	 * @author 复杂的多文件和文本请求，用post
	 */
	public AysResponse doMultiQuestByPostMethod(String url, HttpParams params,
			boolean isCookie) {
		AysResponse resp = new AysResponse();
		//
		return resp;
	}

	/**
	 * post请求，需要传递url，参数，cookie
	 */
	public AysResponse doQuestByPostMethod(String url, HttpParams params,
			boolean isCookie) {
		AysResponse resp = new AysResponse();
		//
		return resp;
	}

	/**
	 * 重载的两个参数的post请求
	 */
	public AysResponse doQuestByPostMethod(String url, HttpParams params) {
		return doQuestByPostMethod(url, params, false);
	}

	/**
	 * 重载的一个参数的post请求
	 */
	public AysResponse doQuestByPostMethod(String url) {
		return doQuestByPostMethod(url,null);
	}

	
	/**
	 * get请求，需要传递url，参数，cookie
	 */
	public AysResponse doQuestByGetMethod(String url, HttpParams params,
			boolean isCookie) {
		AysResponse resp = new AysResponse();
		//
		return resp;
	}

	/**
	 * get请求，需要传递url，参数
	 */
	public AysResponse doQuestByGetMethod(String url, HttpParams params) {
		return doQuestByPostMethod(url, params, false);
	}

	/**
	 * get请求，需要传递url
	 */
	public AysResponse doQuestByGetMethod(String url) {
		return doQuestByGetMethod(url,null);
	}

}
