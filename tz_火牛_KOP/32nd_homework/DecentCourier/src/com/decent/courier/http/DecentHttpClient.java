package com.decent.courier.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class DecentHttpClient {
	private static HttpClient mHttpClient;

	public static HttpClient getInstance() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
		}
		return mHttpClient;
	}
}
