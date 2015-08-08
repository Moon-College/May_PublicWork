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
		//���߳�ȥ���ʣ��첽
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
		sb.deleteCharAt(sb.length()-1);//ɾ������ģ�����&
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
			conn.setConnectTimeout(5000);//��ʱ
			int statuCode = conn.getResponseCode();
			Log.i("INFO", "get request statuCode:"+statuCode);
			if(statuCode == 200){
				//������������ɹ���
				InputStream inputStream = conn.getInputStream();//�ӷ�����������������
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
		conn.setDoOutput(true);//���ÿ����������д����
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		try {
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(entity);//�������д����
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int statuCode;
		try {
			statuCode = conn.getResponseCode();
			Log.i("INFO", "post request statuCode:"+statuCode);
			if(statuCode == 200){
				//������
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
		}//��ȡ������
		return content;
	}
	private class MyHttpTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(method.equals("GET")){
				//����get����
				return doGetByHttpUrlConnection();
			}else if(method.equals("POST")){
				//����post����
				return doPostByHttpUrlConnection();
			}
			return null;
		}

		//�ص�
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if(result!=null){
				//����ɹ���
				MyHttpUtils.this.callback.success(result);
			}else{
				MyHttpUtils.this.callback.failed(result);
			}
			super.onPostExecute(result);
		}
		
	}
}
