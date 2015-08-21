package com.decent.courier.http;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class DecentHttpClient {
	private static HttpClient mHttpClient;

	public static HttpClient getInstance() {
		/*
		 *  需要改进成一个thread-safe，并且多线程能够访问，基于链接池的httpclient
		 *  1、Make sure to release the connection before allocating another one.
		 *  2、Invalid use of SingleClientConnManager: connection still allocated.
		 *  3、java.lang.IllegalStateException: Adapter is detached.
		 */
		if (mHttpClient == null) {
			HttpParams params = new BasicHttpParams();
			ConnPerRoute connPerRoute = new ConnPerRoute() {

				@Override
				public int getMaxForRoute(HttpRoute route) {
					// TODO Auto-generated method stub
					return 100;
				}
			};
			
			ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			ClientConnectionManager cm = new ThreadSafeClientConnManager(
					params, schemeRegistry);
			mHttpClient = new DefaultHttpClient(cm, params);
		}
		return mHttpClient;
	}
}
