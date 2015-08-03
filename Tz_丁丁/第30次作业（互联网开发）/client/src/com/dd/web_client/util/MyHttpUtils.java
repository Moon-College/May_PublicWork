package com.dd.web_client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import android.os.AsyncTask;
import android.util.Log;

import com.dd.web_client.callback.MyHttpCallback;

public class MyHttpUtils {
	private String url;
	private Map<String,String> data;
	private String method;
	private com.dd.web_client.callback.MyHttpCallback callback;
	public MyHttpUtils(String url, Map<String, String> data, String method,
			MyHttpCallback callback) {
		super();
		this.url = url;
		this.data = data;
		this.method = method;
		this.callback = callback;
	}
	public void doRequestByHttpUrlConnection(){
		//子线程去访问，异步
		MyHttpTask task = new MyHttpTask();
		task.execute(null);
	}
	private String doGetByHttpUrlConnection() {
		String content = null;
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		for(Map.Entry<String, String> entry:data.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);//删掉多余的？或者&
		URL url = null;
		try {
			url = new URL(this.url+sb.toString());
			Log.i("INFO", "get request url:"+this.url+sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);//超时
			int statuCode = conn.getResponseCode();
			Log.i("INFO", "get request statuCode:"+statuCode);
			if(statuCode == 200){
				//哈哈哈，请求成功了
				InputStream inputStream = conn.getInputStream();//从服务输入来的输入流
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer result = new StringBuffer();
				String result_str;
				while((result_str = reader.readLine())!=null){
					result.append(result_str);
				}
				content = result.toString();
				Log.i("INFO", "get request success:"+content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	private String doPostByHttpUrlConnection() {
		// TODO Auto-generated method stub
		String content= null;
		StringBuffer sb = new StringBuffer();
		for(Map.Entry<String, String> entry:data.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		if(data!=null&data.size()!=0){
			sb.deleteCharAt(sb.length()-1);
		}
		byte[] entity = sb.toString().getBytes();
		URL url = null ; 
		try {
			url = new URL(this.url);
			Log.i("INFO", "post request Url:"+url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.setRequestMethod(this.method);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);//设置可以向服务器写数据
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		try {
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(entity);//向服务器写数据
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int statuCode;
		try {
			statuCode = conn.getResponseCode();
			Log.i("INFO", "post request statuCode:"+statuCode);
			if(statuCode == 200){
				//哈哈哈
				InputStream inputStream = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer result = new StringBuffer();
				String result_str;
				while((result_str = reader.readLine())!=null){
					result.append(result_str);
				}
				content = result.toString();
				Log.i("INFO", "get request success:"+content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取返回码
		return content;
	}
	private class MyHttpTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(method.equals("GET")){
				//发送get请求
				return doGetByHttpUrlConnection();
			}else if(method.equals("POST")){
				//发送post请求
				return doPostByHttpUrlConnection();
			}
			return null;
		}

		//回调
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if(result!=null){
				//请求成功了
				MyHttpUtils.this.callback.success(result);
			}else{
				MyHttpUtils.this.callback.failed(result);
			}
			super.onPostExecute(result);
		}
		
	}
}
