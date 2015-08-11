package com.decent.courier.http;

import com.decent.courier.bean.request.HttpParams;

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
	 * @return 请求结果
	 */
	public AsyResponse doMultiQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	
	/**
	 * 通过post方式，发送http请求
	 * @param url url
	 * @param httpParams http参数
	 * @param isUseCookie 是否使用cookie
	 * @return 请求结果
	 */
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	/**
	 * 通过post方式，发送http请求,不使用cookie
	 * @param url url 
	 * @param httpParams http参数
	 * @return 请求结果
	 */
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams); 
	/**
	 * 通过post方式，发送http请求,不使用cookie,不使用http参数
	 * @param url url 
	 * @return 请求结果
	 */
	public AsyResponse doQuestByPostMethod(String url);
	
	/**
	 * 通过get方式，发送http请求
	 * @param url url
	 * @param httpParams http参数
	 * @param isUseCookie 是否使用cookie
	 * @return 请求结果
	 */
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	/**
	 * 通过get方式，发送http请求,不使用cookie
	 * @param url url 
	 * @param httpParams http参数
	 * @return 请求结果
	 */
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams); 
	/**
	 * 通过get方式，发送http请求,不使用cookie,不使用http参数
	 * @param url url 
	 * @return 请求结果
	 */
	public AsyResponse doQuestByGetMethod(String url);	
}
