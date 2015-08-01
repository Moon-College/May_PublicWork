package com.junwen.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.junwen.http.inteface.OnConnectionListener;

public class HttpUtil {
	
	private String method; //方法
	private String url; //服务器地址
	private Map<String, String> data; //键值对
	private OnConnectionListener onConnectionListener; //回调接口
	private static final String HTTP_TYPE_GET = "GET";
	private static final String HTTP_TYPE_POST = "POST";
	private static final int TIME_OUT = 5000;//延迟时间
	private static final int CONNECTION_SUCCESS = 200; //成功码
	public HttpUtil(String method, String url, Map<String, String> data,
			OnConnectionListener onConnectionListener) {
		this.method = method;
		this.url = url;
		this.data = data;
		this.onConnectionListener = onConnectionListener;
	}
	/**
	 * 执行网络请求，用HttpUrlConnection方式
	 */
	public  void doRequestByHttpurlConnection(){
		HttpTask task = new HttpTask();
		task.execute();
	}
	/**
	 * 执行网络请求，用HttpClient方式
	 */
	public void doRequestByHttpClient(){
		HttpClientTask task = new HttpClientTask();
		task.execute();
	}
	public class HttpClientTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... arg0) {
			if(method.equals(HTTP_TYPE_GET)){
				//采用Get请求方式
				return doGetByHttpGet();
			}else if(method.equals(HTTP_TYPE_POST)){
				//采用Post请求方式
				return doPostByHttpPost();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result!=null){
				onConnectionListener.success(result);
			}else{
				onConnectionListener.fial("没有数据");
			}
		}
		
		/**
		 * 采用HttpGet请求方式访问服务器
		 */
		private String doPostByHttpPost() {
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> item : data.entrySet()) {
			parameters.add(new BasicNameValuePair(item.getKey(), item.getValue()));
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(parameters);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		httppost.setEntity(entity);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(httppost);
			if(response.getStatusLine().getStatusCode() == 200){
				//连接成功
				return EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		}
		
		
		/**
		 * 采用HttpGet方式访问服务器
		 */
		private String doGetByHttpGet() {
		StringBuffer sb = new StringBuffer();
		URL url_get = null;
		sb.append("?");
		for ( Map.Entry<String, String> item : data.entrySet()) {
			sb.append(item.getKey());
			sb.append("=");
			sb.append(item.getValue());
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		try {
			url_get = new URL(url+sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpGet httpget = new HttpGet(url_get.toString());
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == 200){
				//连接成功
				return EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		}
		
	}
	public class HttpTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			if(method.equals(HTTP_TYPE_GET)){
				//Get方式
				return doGetByHttpUrlConnection();
			}else if(method.equals(HTTP_TYPE_POST)){
				//Post方式
				return doPostByHttpUrlConnection();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result!=null){
				onConnectionListener.success(result);
			}else{
				onConnectionListener.fial("没有数据");
			}
		}
		/**
		 * Post方式执行服务器访问
		 */
		private String doPostByHttpUrlConnection() {
		try {
			StringBuffer sb = new StringBuffer();
			URL url_post = new URL(url);
			for (Map.Entry<String, String> item : data.entrySet()) {
				sb.append(item.getKey());
				sb.append("=");
				sb.append(item.getValue());
				sb.append("&");
			}
			if(data!=null && data.size()!=0){
				sb.deleteCharAt(sb.length()-1);
			}
			byte [] datas = sb.toString().getBytes();
			HttpURLConnection conn = (HttpURLConnection) url_post.openConnection();
			conn.setRequestMethod(method);
			conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("Content-Lenght", String.valueOf(datas.length));
			conn.setDoOutput(true);
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(datas);
			int code = conn.getResponseCode();
			if(code == 200){
				//请求成功
				InputStream inputStream = conn.getInputStream();
				return TextUtil.getString(inputStream);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		}
		
		/**
		 * Get方式执行服务器访问
		 */
		private String doGetByHttpUrlConnection() {
			//构建GET的URL地址
			StringBuffer sb = new StringBuffer();
			URL url_get  = null;
			sb.append("?");
			for (Map.Entry<String, String> item  : data.entrySet()) {
				sb.append(item.getKey());
				sb.append("=");
				sb.append(item.getValue());
				sb.append("&");
			}
			sb.deleteCharAt(sb.length()-1);
			try {
				url_get = new URL(url+sb);
				Log.i("INFO", "url地址："+url_get);
				HttpURLConnection conn = (HttpURLConnection) url_get.openConnection();
				conn.setRequestMethod(method);
				conn.setReadTimeout(TIME_OUT);
				int code = conn.getResponseCode();
				if(code == CONNECTION_SUCCESS){
					//连接成功!
					InputStream inputStream = conn.getInputStream();
					return TextUtil.getString(inputStream);
				}else{
					Log.i("INFO", "连接错误,返回码："+code);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	
}
