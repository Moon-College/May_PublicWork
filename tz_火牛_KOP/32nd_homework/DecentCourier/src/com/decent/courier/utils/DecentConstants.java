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
	 * 经度的键值
	 */
	public static String LONGTITUDE = "longtitude";
	/**
	 * 维度的键值
	 */
	public static String LATITUDE = "latitude";
	
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
    
    /**
     * 坐标位置上报时间,暂时设置1分钟一次
     */
	public static final long LOCATION_REP_PERIOD = 1*60*1000;
	/**
	 * 存放上报或者推送信息的xml文件名
	 */
	public static final String PUSH = "push";
	/**
	 * 内容上报时候Android 设备的设备TYPE
	 */
	public static final int ANDROID_DEVICE_TYPE = 3;
	/**
	 * 存放在里面存的的token键
	 */
	public static final String TOKEN = "token";
	/**
	 * 上报坐标的url
	 */
	public static final String LOCATION_URL = HOST+"/courier/location.do";
	/**
	 * 保存地址信息的键
	 */
	public static final String ADDR = "addr";
	/**
	 * 保存地点坐标信息的xml文件名字
	 */
	public static final String LOC = "loc";

}
