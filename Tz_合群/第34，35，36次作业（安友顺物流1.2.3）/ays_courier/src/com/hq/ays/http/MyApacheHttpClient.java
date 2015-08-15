package com.hq.ays.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyApacheHttpClient {
	private MyApacheHttpClient(){}
	public static HttpClient client;
	
	public static HttpClient getInstance(){
		if(client == null){
			client = new DefaultHttpClient();
		}
		return client;
	}
}
