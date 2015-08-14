package com.decent.courier.utils;

public class DecentConstants {
	/**
	 * cookie��httpͷ����ļ�����
	 */
	public static String SETCOOKIE = "Set-Cookie";
	/**
	 * ����cookie��xml�ļ���
	 */
	public static String COOKIE = "cookie";	
	/**
	 * get��ʽ�ύ����
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post��ʽ�ύ����
	 */
	public static final String POST_METHOD = "POST";
	/**
	 * �ύ��ʽ������
	 */
	public static final String[] REQUEST_METHODS = { GET_METHOD, POST_METHOD };

	/**
	 * Ŀǰ��֧�ֵ�http���ʷ�ʽ
	 */
	public static final int NOT_SUPPORTED_METHOD = 888;
	/**
	 * Ŀǰ��֧�ֵ�http���ʷ�ʽ���ַ�˵��
	 */
	public static final String NOT_SUPPORTED_METHOD_STR = "not supported_method";
	
	public static final int HTTP_OK = 200;
	
	/**
	 * HOST
	 */
	public static final String HOST = "http://120.25.219.175:8080";
	/**
	 * ��½url
	 */
	public static final String LOGIN_URL = HOST+"/user/courierLogin.do";	
}
