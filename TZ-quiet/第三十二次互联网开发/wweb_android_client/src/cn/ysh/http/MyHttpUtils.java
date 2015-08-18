package cn.ysh.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;

import cn.ysh.callback.MyCallBack;
import cn.ysh.wweb_android_client.MainActivity;

public class MyHttpUtils {
	private String mUrl;
	private Map<String,String> data;
	private String mMethod;
	private MyCallBack mMyCallBack;
	public MyHttpUtils(String mUrl, Map<String, String> data, String mMethod,
			MyCallBack mMyCallBack) {
		super();
		this.mUrl = mUrl;
		this.data = data;
		this.mMethod = mMethod;
		this.mMyCallBack = mMyCallBack;
	}
	
	public void doRequestByHttpUrlConnection(){
		MyAsyncTask task = new MyAsyncTask();
		task.execute(new Void[]{});
	}
	
	private String doGetByHttpUrlConnection() {
		String content = null;
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for(Map.Entry<String, String> entry : data.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		try {
			URL url = new URL(mUrl+sb.toString());
			Log.i("INFO", "do get url:"+url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(mMethod);
			conn.setConnectTimeout(5000);
			int statusCode = conn.getResponseCode();
			Log.i("INFO", "statusCode:"+statusCode);
			if(statusCode == HttpStatus.SC_OK){
				//请求成功了
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuffer sb_result = new StringBuffer();
				String str;
				while((str = br.readLine()) != null){
					sb_result.append(str);
				}
				content = sb_result.toString();
				Log.i("INFO", "get request success:"+content);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private String doPostByHttpUrlConnection(){
		String content = null;
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry : data.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		if(data != null && data.size()!= 0){
			sb.deleteCharAt(sb.length() - 1);
		}
		try {
			URL url = new URL(mUrl);
			Log.i("INFO", "do post url:"+url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(mMethod);
			conn.setConnectTimeout(5000);
			OutputStream os = conn.getOutputStream();
			byte[] buffer = sb.toString().getBytes();
			os.write(buffer, 0, buffer.length);
			int statusCode = conn.getResponseCode();
			Log.i("INFO", "statusCode:"+statusCode);
			if(statusCode == HttpStatus.SC_OK){
				//请求成功了
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuffer sb_result = new StringBuffer();
				String str;
				while((str = br.readLine()) != null){
					sb_result.append(br.readLine());
				}
				content = sb_result.toString();
				Log.i("INFO", "post request success:"+content);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private class MyAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			if(mMethod.equals("GET")){
				return doGetByHttpUrlConnection();
			}else if(mMethod.equals("POST")){
				return doPostByHttpUrlConnection();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result != null){
				MyHttpUtils.this.mMyCallBack.succeed(result);
			}else{
				MyHttpUtils.this.mMyCallBack.failed(result);
			}
		}

	}
}
