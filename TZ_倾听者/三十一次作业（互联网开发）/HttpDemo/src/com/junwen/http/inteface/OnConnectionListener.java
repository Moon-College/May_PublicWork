package com.junwen.http.inteface;

public interface OnConnectionListener {
	
	/**
	 * �ɹ�
	 * @param result
	 */
	void success(String result);
	
	/**
	 * ʧ��
	 * @param result
	 */
	void fial(String result);
		
}
