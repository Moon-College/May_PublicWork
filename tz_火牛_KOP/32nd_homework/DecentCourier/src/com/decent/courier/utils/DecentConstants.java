package com.decent.courier.utils;

import android.os.Environment;

public class DecentConstants {
	/**
	 * cookie��httpͷ����ļ�����
	 */
	public static final String SETCOOKIE = "Set-Cookie";
	/**
	 * ����cookie��xml�ļ���
	 */
	public static final String COOKIE = "cookie";
	/**
	 * sharedPreference�����û������ֶ�
	 */
	public static final String USERNAME = "userName";
	/**
	 * sharedPreference����password���ֶ�
	 */
	public static final String PASSWORD = "password";
	/**
	 * �����û��������xml�ļ���
	 */
	public static final String USER = "user";
	/**
	 * get��ʽ�ύ����
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post��ʽ�ύ����
	 */
	public static final String POST_METHOD = "POST";
	/**
	 * ��������post�ύ
	 */
	public static final Object MULTI_POST_METHOD = "MULTI_POST";
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

	
	/**
	 * ע��url
	 */
	public static String REGISTER_URL = HOST+"/user/courierRegister.do";
	/**
	 * ���ȵļ�ֵ
	 */
	public static String LONGTITUDE = "longtitude";
	/**
	 * ά�ȵļ�ֵ
	 */
	public static String LATITUDE = "latitude";
	
	/**
	 * ���ݿ��·��
	 */
	public static final String LOCATION_DB_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/location.db";
	
	/**
	 * ���������ص�ok��־
	 */
	public static final int RET_OK = 0;
	
	//����ͼ���3���ı����requestcode
	public static final int PHOTO_CDRD_FRONT_DATA = 167;
    public static final int PHOTO_CDRD_REAR_DATA = 168;
    public static final int PHOTO_CDRD_WITH_DATA = 169;
    
    //����ͼƬ���ŵı�׼��
    public static final int BITMAP_STANDRD_WIDTH = 50;
    
    /**
     * ����λ���ϱ�ʱ��,��ʱ����1����һ��
     */
	public static final long LOCATION_REP_PERIOD = 1*60*1000;
	/**
	 * ����ϱ�����������Ϣ��xml�ļ���
	 */
	public static final String PUSH = "push";
	/**
	 * �����ϱ�ʱ��Android �豸���豸TYPE
	 */
	public static final int ANDROID_DEVICE_TYPE = 3;
	/**
	 * ����������ĵ�token��
	 */
	public static final String TOKEN = "token";
	/**
	 * �ϱ������url
	 */
	public static final String LOCATION_URL = HOST+"/courier/location.do";
	/**
	 * �����ַ��Ϣ�ļ�
	 */
	public static final String ADDR = "addr";
	/**
	 * ����ص�������Ϣ��xml�ļ�����
	 */
	public static final String LOC = "loc";
	/**
	 * Ĭ��ÿҳ��ʾ��Ŀ
	 */
	public static final Integer ITEM_PER_PAGE = 10;
	/**
	 * ��ѯҵ��Ա�鿴���ж�����url
	 */
	public static final String ORDERLIST_URL = HOST+"/order/courierOrders.do";
	/**
	 * ��ѯҵ��Ա�������۵�url
	 */
	public static final String ALL_COMMENTS_URL = HOST+"/comment/viewCourierComments.do";

}
