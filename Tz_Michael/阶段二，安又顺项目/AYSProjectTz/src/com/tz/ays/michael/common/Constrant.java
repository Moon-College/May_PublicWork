package com.tz.ays.michael.common;

import android.os.Environment;

/**
 * 常量类
 * @author michael
 *
 */
public class Constrant {

	/**保存cookie的xml文件名*/
	public static String COOKIE="cookie";
	/**cookie的键*/
	public static String COOKIE_KEY="f_cookie";
	
	
	/**保存账号信息的xml文件名*/
	public static String ACCOUNT = "account";
		
	/**账号的键*/
	public static String USER_NAME = "userName";
		
	/**密码的键*/
	public static String PASSWORD = "password";
	
	/**数据库的路径*/
	public static final String DB_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/address.db";
	
	//开启图库的3个文本框的requestcode
	public static final int PHOTO_CDRD_FRONT_DATA = 167;
	public static final int PHOTO_CDRD_REAR_DATA = 168;
	public static final int PHOTO_CDRD_WITH_DATA = 169;
	    
	/**设置图片缩放的标准宽*/
	public static final int BITMAP_STANDRD_WIDTH = 50;
	
	//HOST主机ip加端口
	public static final String HOST = "http://120.25.219.175:8080";
	/**登陆路径*/
	public static final String LOGIN = HOST+"/user/courierLogin.do";
	
	/**注册*/
	public static String REGISTER = HOST+"/user/courierRegister.do";
	
}
