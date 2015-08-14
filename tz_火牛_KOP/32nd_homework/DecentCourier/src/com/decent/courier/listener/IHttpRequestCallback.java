package com.decent.courier.listener;

/**
 * http异步访问时的回调接口
 * @author K450J
 *
 */
public interface IHttpRequestCallback {
	/**
	 * 访问失败
	 * @param result 结果
	 */
	public void onRequestFail(String result);

	/**
	 * 访问成功
	 * @param result 结果
	 */
	public void onRequestSuccess(String result);
}
