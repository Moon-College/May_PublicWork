package com.decent.courier.utils;

public class DecentConstants {
	/**
	 * cookie在http头里面的键名字
	 */
	public static String SETCOOKIE = "Set-Cookie";
	/**
	 * 保存cookie的xml文件名
	 */
	public static String COOKIE = "cookie";	
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
	
	public static final int HTTP_OK = 200;
	
	/**
	 * HOST
	 */
	public static final String HOST = "http://120.25.219.175:8080";
	/**
	 * 登陆url
	 */
	public static final String LOGIN_URL = HOST+"/user/courierLogin.do";	
}
