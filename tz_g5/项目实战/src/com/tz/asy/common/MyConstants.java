package com.tz.asy.common;

import android.os.Environment;

public class MyConstants {
	//cookie的键
	public static String SETCOOKIE = "Set-Cookie";
	//保存cookie的xml文件名
	public static String COOKIE = "cookie";
	//保存账号信息的xml文件名
	public static String ACCOUNT = "account";
	//账号的键
	public static String USER_NAME = "userName";
	//位置的xml
	public static String LOC = "location";
	//地址的键
	public static String ADDR = "addr";
	//密码的键
	public static String PASSWORD = "password";
	//token的xml
	public static String PUSH = "push";
	//token的键
	public static String TOKEN = "token";
	//连接超时异常
	public static int C_TIMEOUT = 10000;
	//读取超时异常
	public static int S_TIMEOUT = 10000;
	//HOST
	public static final String HOST = "http://120.25.219.175:8080";
	//登陆
	public static final String LOGIN = HOST+"/user/courierLogin.do";
	//注册
	public static String REGISTER = HOST+"/user/courierRegister.do";
	//经纬度更新
	public static String LOCATION = HOST+"/courier/location.do";
	//数据库的路径
	public static final String DB_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/location.db";
	
	//开启图库的3个文本框的requestcode
	public static final int PHOTO_CDRD_FRONT_DATA = 167;
    public static final int PHOTO_CDRD_REAR_DATA = 168;
    public static final int PHOTO_CDRD_WITH_DATA = 169;
    
    //设置图片缩放的标准宽
    public static final int BITMAP_STANDRD_WIDTH = 50;
    
    //上传坐标的事件间隔
    public static final int LOCATION_PERIOD = 60*1000*5;
}
