package com.decent.courier.http;

import com.decent.courier.bean.request.HttpParams;

/**
 * http请求的实现类
 * @author K450J
 *
 */
public class HttpRequest implements IHttpRequest {

	@Override
	public AsyResponse doMultiQuestByPostMethod(String url,
			HttpParams httpParams, boolean isUseCookie) {
		AsyResponse resp = new AsyResponse();
		// TODO Auto-generated method stub
		return resp;
	}

	@Override
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie) {
		AsyResponse resp = new AsyResponse();
		// TODO Auto-generated method stub
		return resp;
	}

	@Override
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams) {
		// TODO Auto-generated method stub
		return doQuestByPostMethod(url,httpParams,false);
	}

	@Override
	public AsyResponse doQuestByPostMethod(String url) {
		// TODO Auto-generated method stub
		return doQuestByPostMethod(url,null,false);
	}

	@Override
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie) {
		AsyResponse resp = new AsyResponse();
		// TODO Auto-generated method stub
		return resp;
	}

	@Override
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams) {
		// TODO Auto-generated method stub
		return doQuestByGetMethod(url,httpParams,false);
	}

	@Override
	public AsyResponse doQuestByGetMethod(String url) {
		// TODO Auto-generated method stub
		return doQuestByGetMethod(url,null,false);
	}

}
