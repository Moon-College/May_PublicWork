package com.dd.courier.http;

import com.dd.courier.bean.request.HttpParams;
import com.dd.courier.listener.HttpCallback;


public interface IHttpRequest {
	//复杂请求
	void doMultiQuestByPostMethod(String url,HttpParams params,boolean isCookie,HttpCallback callback);
	
	//post
	void doQuestByPostMethod(String url,HttpParams params,boolean isCookie,HttpCallback callback);
	
	
	void doQuestByPostMethod(String url,HttpParams params,HttpCallback callback);
	
	
	void doQuestByPostMethod(String url,HttpCallback callback);
	
	
	//get
	void doQuestByGetMethod(String url,HttpParams params,boolean isCookie,HttpCallback callback);
	
	
	void doQuestByGetMethod(String url,HttpParams params,HttpCallback callback);
	
	
	void doQuestByGetMethod(String url,HttpCallback callback);

}
