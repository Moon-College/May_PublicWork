package com.tz.ays.michael.http;

import com.tz.ays.michael.bean.request.IHttpParams;
import com.tz.ays.michael.callback.IHttpCallBack;

public interface IHttpRequest {

	//post
	public void doQuestByPostMethod(String path,IHttpParams params,boolean needCookie,IHttpCallBack callBack);
	
	public void doQuestByPostMethod(String path,IHttpParams params,IHttpCallBack callBack);
	
	public void doQuestByPostMethod(String path,IHttpCallBack callBack);
	
	//get
	public void doQuestByGetMethod(String path,IHttpParams params,boolean needCookie,IHttpCallBack callBack);
	
	public void doQuestByGetMethod(String path,IHttpParams params,IHttpCallBack callBack);
	
	public void doQuestByGetMethod(String path,IHttpCallBack callBack);
	
	//多文件上传
	public void doMultiQuestByPostMethod(String path,IHttpParams params,boolean needCookie,IHttpCallBack callBack);
	
}
