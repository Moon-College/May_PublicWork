package com.tz.aysnctaskdownload.task.listener;

/**
 * 
 * @author
 * 
 * @param <T>
 *            返回值类型
 */
public interface DownloadListener<T> {

	public void startLoading(int maxSize);

	public void loading(int loadingSize);

	public void success(T result);

	public void cancel();
}
