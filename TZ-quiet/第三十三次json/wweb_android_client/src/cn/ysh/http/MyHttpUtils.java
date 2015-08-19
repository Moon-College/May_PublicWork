package cn.ysh.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

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
	
	private String doGetByHttpClient(){
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
		HttpGet get = new HttpGet(mUrl+sb.toString());//创建get请求
		Log.i("INFO", "do getclient url:"+mUrl+sb.toString());
		HttpClient client = new DefaultHttpClient();
		//设置超时
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
		try {
			HttpResponse resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
				content = EntityUtils.toString(resp.getEntity(), "utf-8");
				Log.i("INFO", "do get client content:"+content);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
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
	
	private String doPostByHttpClient(){
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
		HttpPost post = new HttpPost(mUrl);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry : data.entrySet()){
			BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			parameters.add(nameValuePair);
		}
		UrlEncodedFormEntity entity = null;//表单entity
		try {
			entity = new UrlEncodedFormEntity(parameters);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		post.setEntity(entity);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
				content = EntityUtils.toString(resp.getEntity(), "utf-8");
				Log.i("INFO", "do post client content:"+content);
			}
		} catch (ClientProtocolException e) {
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
					sb_result.append(str);
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
				//return doGetByHttpUrlConnection();
				return doGetByHttpClient();
			}else if(mMethod.equals("POST")){
				//return doPostByHttpUrlConnection();
				return doPostByHttpClient();
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
