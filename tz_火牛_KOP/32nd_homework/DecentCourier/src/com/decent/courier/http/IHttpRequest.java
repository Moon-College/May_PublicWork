package com.decent.courier.http;

import com.decent.courier.bean.request.HttpParams;

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
	 * @return ������
	 */
	public AsyResponse doMultiQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	
	/**
	 * ͨ��post��ʽ������http����
	 * @param url url
	 * @param httpParams http����
	 * @param isUseCookie �Ƿ�ʹ��cookie
	 * @return ������
	 */
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	/**
	 * ͨ��post��ʽ������http����,��ʹ��cookie
	 * @param url url 
	 * @param httpParams http����
	 * @return ������
	 */
	public AsyResponse doQuestByPostMethod(String url, HttpParams httpParams); 
	/**
	 * ͨ��post��ʽ������http����,��ʹ��cookie,��ʹ��http����
	 * @param url url 
	 * @return ������
	 */
	public AsyResponse doQuestByPostMethod(String url);
	
	/**
	 * ͨ��get��ʽ������http����
	 * @param url url
	 * @param httpParams http����
	 * @param isUseCookie �Ƿ�ʹ��cookie
	 * @return ������
	 */
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie);
	/**
	 * ͨ��get��ʽ������http����,��ʹ��cookie
	 * @param url url 
	 * @param httpParams http����
	 * @return ������
	 */
	public AsyResponse doQuestByGetMethod(String url, HttpParams httpParams); 
	/**
	 * ͨ��get��ʽ������http����,��ʹ��cookie,��ʹ��http����
	 * @param url url 
	 * @return ������
	 */
	public AsyResponse doQuestByGetMethod(String url);	
}
