package com.decent.courier.listener;

/**
 * http�첽����ʱ�Ļص��ӿ�
 * @author K450J
 *
 */
public interface IHttpRequestCallback {
	/**
	 * ����ʧ��
	 * @param result ���
	 */
	public void onRequestFail(String result);

	/**
	 * ���ʳɹ�
	 * @param result ���
	 */
	public void onRequestSuccess(String result);
}
