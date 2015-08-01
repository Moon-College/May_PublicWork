package com.junwen.json.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.junwen.json.callback.OnJsonListener;
import com.junwen.json.model.User;


public class HttpUtil {
	private static final int RIGHT_CODE = 200;
	/**
	 * 启动线程，访问服务器
	 */
	public static void doLoginByHttpClient(String url,Object user,OnJsonListener onJsonListener){
		HttpTask task = new HttpTask();
		task.execute(url,user,onJsonListener);
	}
	public static class HttpTask extends AsyncTask<Object, Void, String>{
		
		private OnJsonListener jsonListener = null;
		@Override
 		protected String doInBackground(Object... params) {
			//获取url，json字符串，和接口
			String url = (String) params[0];
			String json = getJson(params[1]);
			jsonListener = (OnJsonListener) params[2];
			HttpPost httppost = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			//数据得添加
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("json", json));
			UrlEncodedFormEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(parameters,"utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			//设置实体
			httppost.setEntity(entity);
			try {
				HttpResponse response = client.execute(httppost);
				if(response.getStatusLine().getStatusCode() == RIGHT_CODE){
					//连接成功
//					Log.i("INFO", "结果"+EntityUtils.toString(response.getEntity()));
					return EntityUtils.toString(response.getEntity());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Log.i("INFO", "onPostExecute");
			if(result != null){
				jsonListener.onSuccess(JSON.parseObject(result, User.class));
			}else{
				jsonListener.onFail("没有数据");
			}
		}
	}
	
	/**
	 * 根据对象，返回对象对应得Json字符串
	 * @param object
	 * @return
	 */
	public static String getJson(Object object){
		return JSON.toJSONString(object);
	}
}
