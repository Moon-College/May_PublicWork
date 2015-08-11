package com.dd.courier.http;

import com.dd.courier.bean.request.HttpParams;

public interface IHttpRequest {
	//复杂请求
	AysResponse doMultiQuestByPostMethod(String url,HttpParams params,boolean isCookie);
	
	//post
	AysResponse doQuestByPostMethod(String url,HttpParams params,boolean isCookie);
	
	
	AysResponse doQuestByPostMethod(String url,HttpParams params);
	
	
	AysResponse doQuestByPostMethod(String url);
	
	
	//get
	AysResponse doQuestByGetMethod(String url,HttpParams params,boolean isCookie);
	
	
	AysResponse doQuestByGetMethod(String url,HttpParams params);
	
	
	AysResponse doQuestByGetMethod(String url);

}
