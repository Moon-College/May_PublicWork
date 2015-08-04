package com.decent.httpconnection;

import java.util.Map;

import com.decent.httpconnection.bean.HttpResult;

public interface HttpAccessService {
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

	/**
	 * ����http������,����������첽�ģ� �����setHttpAccessCallback������HttpConnectionCallback�ص���
	 * ���ڳɹ�����ʧ�ܵ�ʱ��ѽ�����ظ��ص�
	 */
	public void doRequestHttpAccessAsync();

	/**
	 * ����http������,���������ͬ����
	 * 
	 * @return ���ؽ��
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
