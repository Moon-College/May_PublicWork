package com.tz.asy.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyHttpClient {
	public  volatile static HttpClient client;
	public static HttpClient getInstance() {
		if (client == null) {
			synchronized (MyHttpClient.class) {
				if (client == null) {
					client = new DefaultHttpClient();
				}
			}
		}
		return client;
	}
}
