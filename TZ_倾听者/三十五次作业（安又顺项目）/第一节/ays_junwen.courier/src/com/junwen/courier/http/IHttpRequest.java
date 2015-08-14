package com.junwen.courier.http;

import com.junwen.courier.model.request.HttpParams;

public interface IHttpRequest {

	HttpResponse doMultiQueryByPost(String url,HttpParams httpParams,boolean isCook);
	
	HttpResponse doQueryByPost(String url,HttpParams httpParams,boolean isCook);
	
	HttpResponse doQueryByPost(String url,HttpParams httpParams);
	
	HttpResponse doQueryByPost(String url);
	
	HttpResponse doQueryByGet(String url,HttpParams httpParams,boolean isCook);
	
	HttpResponse doQueryByGet(String url,HttpParams httpParams);
	
	HttpResponse doQueryByGet(String url);
	
	
}
