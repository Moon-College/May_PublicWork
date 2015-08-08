package com.junwen.http.inteface;

public interface OnConnectionListener {
	
	/**
	 * ³É¹¦
	 * @param result
	 */
	void success(String result);
	
	/**
	 * Ê§°Ü
	 * @param result
	 */
	void fial(String result);
		
}
