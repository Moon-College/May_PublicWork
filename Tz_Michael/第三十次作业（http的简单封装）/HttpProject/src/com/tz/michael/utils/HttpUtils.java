package com.tz.michael.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import android.os.AsyncTask;
import android.util.Log;

public class HttpUtils {

	private String path;
	private String method;
	private Map<String,String> params;
	private boolean isDefaultMethod;//设置是否使用默认的方法
	private HttpCallBack httpCallBack;
	public HttpUtils(String path, String method, Map<String, String> params,HttpCallBack callback) {
		super();
		this.path = path;
		this.method = method;
		this.params = params;
		this.httpCallBack=callback;
	}
	
	/**
	 * 统一访问网络方法
	 */
	public void getDataFromNet(){
		isDefaultMethod=false;
		MyAsyncTask myAsyncTask=new MyAsyncTask();
		myAsyncTask.execute(null);
	}
	
	/**
	 * 这里使用异步，减少使用Handler，便于代码的管理，提高可读性
	 * @author michael
	 *
	 */
	private class MyAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			if(isDefaultMethod){
				//这里使用httpClient方法
				return doGetDataByHttpClient();
			}else{
				//这里使用httpUrlConnection方法
				return doGetDataByHttpUrlConnection();
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result!=null){
				httpCallBack.success(result);
			}else{
				httpCallBack.success("获取数据失败");
			}
		}
	}

	/**
	 * 使用httpClient来获取数据
	 * @return
	 */
	public String doGetDataByHttpClient() {
		return null;
	}

	/**
	 * 使用httpUrlConnection来获取数据
	 * @return
	 */
	public String doGetDataByHttpUrlConnection() {
		if(method.equals("GET")){
			return getDataByUrlGet();
		}else if(method.equals("POST")){
			return getDataByUrlPost();
		}
		return null;
	}

	/**
	 * 使用urlConnection的post方法获取数据
	 * @return
	 */
	private String getDataByUrlPost() {
		StringBuffer sb=new StringBuffer();
		for(Entry<String, String> entry:params.entrySet()){
			String key=entry.getKey();
			String value=entry.getValue();
			sb.append(key+"="+value);
			sb.append("&");
		}
		if(params.entrySet()!=null&&params.entrySet().size()!=0){
			sb.deleteCharAt(sb.length()-1);
		}
		HttpURLConnection conn=null;
		InputStream in;
		try {
			URL url=new URL(path);
			conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			conn.setDoOutput(true);//设置可以向服务器写数据
			PrintWriter printWriter=new PrintWriter(conn.getOutputStream());
			printWriter.print(sb.toString());
			printWriter.flush();
			printWriter.close();
			if(conn.getResponseCode()==200){
				Log.i("pp--", "urlConnection的post方式连接服务器成功");
				in=conn.getInputStream();
				StringBuffer content=new StringBuffer();
				BufferedReader reader=new BufferedReader(new InputStreamReader(in));
				String str;
				while((str=reader.readLine())!=null){
					content.append(str);
				}
				return content.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用urlConnection的get方法获取数据
	 * @return
	 */
	private String getDataByUrlGet() {
		StringBuffer sb=new StringBuffer();
		String pms="";
		sb.append("?");
		for(Entry<String, String> entry:params.entrySet()){
			String key=entry.getKey();
			String value=entry.getValue();
			sb.append(key+"="+value);
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);//删掉多余的？或者&
		HttpURLConnection conn=null;
		InputStream in = null;
		try {
			URL url=new URL(path+sb.toString());
			conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			if(conn.getResponseCode()==200){
				Log.i("pp--", "urlConnection的get方式连接服务器成功");
				in=conn.getInputStream();
				StringBuffer content=new StringBuffer();
				BufferedReader reader=new BufferedReader(new InputStreamReader(in));
				String str;
				while((str=reader.readLine())!=null){
					content.append(str);
				}
				return content.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
