package com.tz.ays.michael.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * 为了保证是同一个客户端（session）这里使用单例模式
 * @author michael
 *
 */
public class MyHttpClient {

	private static HttpClient client;
	
	public static HttpClient getClient(){
		if(client==null){
			client=new DefaultHttpClient();
		}
		return client;
	}
	
}
