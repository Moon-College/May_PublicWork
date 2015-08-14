package com.decent.courier.http;

import com.decent.courier.bean.request.HttpParams;
import com.decent.courier.listener.IHttpRequestCallback;

/**
 * http��������Ҫʵ�ֵĽӿڣ���������post��get����Ľӿ�
 * 
 * @author K450J
 * 
 */
public interface IHttpRequest {

	/**
	 * ͨ��post��ʽ������http�ĸ������󣨱������ϴ��������ļ�֮��ģ�
	 * @param url url
	 * @param httpParams http����
	 * @param isUseCookie �Ƿ�ʹ��cookie
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doMultiQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	
	/**
	 * ͨ��post��ʽ������http����
	 * @param url url
	 * @param httpParams http����
	 * @param isUseCookie �Ƿ�ʹ��cookie
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	/**
	 * ͨ��post��ʽ������http����,��ʹ��cookie
	 * @param url url 
	 * @param httpParams http����
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByPostMethod(String url, HttpParams httpParams,IHttpRequestCallback httpRequestCallback); 
	/**
	 * ͨ��post��ʽ������http����,��ʹ��cookie,��ʹ��http����
	 * @param url url 
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByPostMethod(String url,IHttpRequestCallback httpRequestCallback);
	
	/**
	 * ͨ��get��ʽ������http����
	 * @param url url
	 * @param httpParams http����
	 * @param isUseCookie �Ƿ�ʹ��cookie
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie,IHttpRequestCallback httpRequestCallback);
	/**
	 * ͨ��get��ʽ������http����,��ʹ��cookie
	 * @param url url 
	 * @param httpParams http����
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByGetMethod(String url, HttpParams httpParams,IHttpRequestCallback httpRequestCallback); 
	/**
	 * ͨ��get��ʽ������http����,��ʹ��cookie,��ʹ��http����
	 * @param url url 
	 * @param httpRequestCallback �ص��ӿ�
	 * @return ������
	 */
	public void doQuestByGetMethod(String url,IHttpRequestCallback httpRequestCallback);	
}
