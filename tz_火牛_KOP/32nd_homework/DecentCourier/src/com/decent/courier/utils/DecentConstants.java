package com.decent.courier.utils;

import android.os.Environment;

public class DecentConstants {
	/**
	 * cookie在http头里面的键名字
	 */
	public static final String SETCOOKIE = "Set-Cookie";
	/**
	 * 保存cookie的xml文件名
	 */
	public static final String COOKIE = "cookie";
	/**
	 * sharedPreference里面用户名的字段
	 */
	public static final String USERNAME = "userName";
	/**
	 * sharedPreference里面password的字段
	 */
	public static final String PASSWORD = "password";
	/**
	 * 保存用户名密码的xml文件名
	 */
	public static final String USER = "user";
	/**
	 * get方式提交请求
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post方式提交请求
	 */
	public static final String POST_METHOD = "POST";
	/**
	 * 复杂内容post提交
	 */
	public static final Object MULTI_POST_METHOD = "MULTI_POST";
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

	
	/**
	 * 注册url
	 */
	public static String REGISTER_URL = HOST+"/user/courierRegister.do";
	
	/**
	 * 数据库的路径
	 */
	public static final String LOCATION_DB_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/location.db";
	
	/**
	 * 服务器返回的ok标志
	 */
	public static final int RET_OK = 0;
	
	//开启图库的3个文本框的requestcode
	public static final int PHOTO_CDRD_FRONT_DATA = 167;
    public static final int PHOTO_CDRD_REAR_DATA = 168;
    public static final int PHOTO_CDRD_WITH_DATA = 169;
    
    //设置图片缩放的标准宽
    public static final int BITMAP_STANDRD_WIDTH = 50;

}
