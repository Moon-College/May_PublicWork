package com.tz.asy.http;


import com.tz.asy.bean.request.HttpParams;
import com.tz.asy.listener.HttpCallback;

public interface IHttpRequest {
	//复杂请求
	void doQuestByMultiPostMethod(String url, HttpParams params, boolean isCookie, HttpCallback callback);
	
	//post
	void doQuestByPostMethod(String url, HttpParams params, boolean isCookie, HttpCallback callback);
	
	
	void doQuestByPostMethod(String url, HttpParams params, HttpCallback callback);
	
	
	void doQuestByPostMethod(String url, HttpCallback callback);
	
	
	//get
	void doQuestByGetMethod(String url, HttpParams params, boolean isCookie, HttpCallback callback);
	
	
	void doQuestByGetMethod(String url, HttpParams params, HttpCallback callback);
	
	
	void doQuestByGetMethod(String url, HttpCallback callback);

}
