package com.hq.ays.utils;

public class MyConstants {
//常量类
	public static final String HOST = "http://120.25.219.175:8080";
//	public static final String HOST = "http://10.0.2.2:8080/";
	public static final String LOGIN = HOST+"/user/courierLogin.do";
	
	
	//请求超时和读取超时
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int SO_TIMEOUT = 20000;
	//证件的requestCode
	public static final int FRONT_CARD = 100;
	public static final int REAL_CARD = 101;
	public static final int CERT_CARD = 102;
	//图片缩放后的标准宽
	public static final int BITMAP_STANDRD_WIDTH = 72;
}
