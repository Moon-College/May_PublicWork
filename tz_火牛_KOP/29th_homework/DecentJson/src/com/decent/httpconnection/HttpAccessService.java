package com.decent.httpconnection;

import java.util.Map;

import com.decent.httpconnection.bean.HttpResult;

public interface HttpAccessService {
	/**
	 * get方式提交请求
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post方式提交请求
	 */
	public static final String POST_METHOD = "POST";
	/**
	 * 提交方式的数组
	 */
	public static final String[] REQUEST_METHODS = { GET_METHOD, POST_METHOD };

	/**
	 * 目前不支持的http访问方式
	 */
	public static final int NOT_SUPPORTED_METHOD = 888;
	/**
	 * 目前不支持的http访问方式的字符说明
	 */
	public static final String NOT_SUPPORTED_METHOD_STR = "not supported_method";

	/**
	 * 请求http的连接,这个函数是异步的， 如果用setHttpAccessCallback设置了HttpConnectionCallback回调，
	 * 会在成功或者失败的时候把结果返回给回调
	 */
	public void doRequestHttpAccessAsync();

	/**
	 * 请求http的连接,这个函数是同步的
	 * 
	 * @return 返回结果
	 */
	public HttpResult doRequestHttpAccess();

	public HttpAccessCallback getHttpAccessCallback();

	public void setHttpAccessCallback(HttpAccessCallback mHttpAccessCallback);

	public String getUrl();

	public void setUrl(String url);

	public Map<String, String> getParameters();

	public void setParameters(Map<String, String> parameters);

	public String getMethod();

	public void setMethod(String method);
}
