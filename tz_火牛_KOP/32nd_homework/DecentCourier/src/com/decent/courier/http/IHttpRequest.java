package com.decent.courier.http;

import com.decent.courier.bean.request.HttpParams;
import com.decent.courier.listener.IHttpRequestCallback;

/**
 * http请求类需要实现的接口，包含各种post和get请求的接口
 * 
 * @author K450J
 * 
 */
public interface IHttpRequest {

	/**
	 * 通过post方式，发送http的复杂请求（比如有上传二进制文件之类的）
	 * @param url url
	 * @param httpParams http参数
	 * @param isUseCookie 是否使用cookie
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doMultiQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	
	/**
	 * 通过post方式，发送http请求
	 * @param url url
	 * @param httpParams http参数
	 * @param isUseCookie 是否使用cookie
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	/**
	 * 通过post方式，发送http请求,不使用cookie
	 * @param url url 
	 * @param httpParams http参数
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByPostMethod(String url, HttpParams httpParams,IHttpRequestCallback httpRequestCallback); 
	/**
	 * 通过post方式，发送http请求,不使用cookie,不使用http参数
	 * @param url url 
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByPostMethod(String url,IHttpRequestCallback httpRequestCallback);
	
	/**
	 * 通过get方式，发送http请求
	 * @param url url
	 * @param httpParams http参数
	 * @param isUseCookie 是否使用cookie
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	/**
	 * 通过get方式，发送http请求,不使用cookie
	 * @param url url 
	 * @param httpParams http参数
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByGetMethod(String url, HttpParams httpParams,IHttpRequestCallback httpRequestCallback); 
	/**
	 * 通过get方式，发送http请求,不使用cookie,不使用http参数
	 * @param url url 
	 * @param httpRequestCallback 回调接口
	 * @return 请求结果
	 */
	public void doQuestByGetMethod(String url,IHttpRequestCallback httpRequestCallback);	
}
