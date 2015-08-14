package com.dd.courier.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.dd.courier.bean.request.HttpParams;
import com.dd.courier.bean.result.HttpBaseResult;
import com.dd.courier.common.MyConstants;
import com.dd.courier.listener.HttpCallback;
import com.dd.courier.utils.MyDataUtils;
import com.dd.courier.utils.MyLog;

import android.content.Context;
import android.os.AsyncTask;


public class HttpRequest implements IHttpRequest{
	private Context context;
	private HttpClient client;
	public HttpRequest(Context context){
		this.context = context;
		client = MyHttpClient.getInstance();
	}
	/**
	 * @author 复杂的多文件和文本请求，用post
	 */
	public void doMultiQuestByPostMethod(String url, HttpParams params,
			boolean isCookie,HttpCallback callback) {
		// TODO Auto-generated method stub
	}

	/**
	 * post请求的核心方法
	 */
	private AysResponse doQuestByPost(String url,HttpParams params,boolean isCookie,HttpCallback callback) {
		// TODO Auto-generated method stub
		AysResponse response = new AysResponse();
		HttpPost post = new HttpPost(url);
		MyLog.d("post quest url:"+url);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if(params != null){
			Class<? extends HttpParams> class1 = params.getClass();
			Field[] declaredFields = class1.getDeclaredFields();
			for(Field field: declaredFields){
				field.setAccessible(true);
				//反射获取对象的变量及变量值
				String name = field.getName();//变量名
				try {
					Object value = field.get(params);
					if(value!=null){
						BasicNameValuePair basicNameValuePair = new BasicNameValuePair(name, String.valueOf(value));
						parameters.add(basicNameValuePair);
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.setException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.setException(e);
				}
			}
		}
		//正文，body
		UrlEncodedFormEntity entity = null;
		try {
			//中文乱码
			entity = new UrlEncodedFormEntity(parameters,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setException(e);
		}
		if(isCookie){
			//经常用来登陆验证
			String value = (String) MyDataUtils.getData(context, MyConstants.COOKIE, MyConstants.SETCOOKIE, String.class);
			post.addHeader(MyConstants.SETCOOKIE, value);//把cookie带到服务端
		}
		//超时异常
		setTimeOutException();
		post.setEntity(entity);
		HttpResponse resp = null;
		try {
			resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			MyLog.d("post request statuCode:"+statusCode);
			if(statusCode == 200){
				String content = EntityUtils.toString(resp.getEntity(), "utf-8");
				MyLog.d("post request content:"+content);
				response.setResult(content);
				//请求成功
				Header[] headers = resp.getAllHeaders();//获取服务端的响应头
				MyDataUtils.savaHead(context, headers);//保存服务端返回的cookie
			}else{
				response.setException(new IllegalArgumentException());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setException(e);
		}
		return response;
	}
	
	
	
	/**
	 * post请求，需要传递url，参数，cookie
	 */
	public void doQuestByPostMethod(String url, HttpParams params,
			boolean isCookie,HttpCallback callback) {
//		AysResponse resp = new AysResponse();
		MyHttpTask httpTask = new MyHttpTask();
		httpTask.execute(url,params,isCookie,"POST",callback);
	}

	/**
	 * 重载的两个参数的post请求
	 */
	public void doQuestByPostMethod(String url, HttpParams params,HttpCallback callback) {
		// TODO Auto-generated method stub
		doQuestByPostMethod(url, params, false,callback);
	}

	/**
	 * 重载的一个参数的post请求
	 */
	public void doQuestByPostMethod(String url,HttpCallback callback) {
		// TODO Auto-generated method stub
		 doQuestByPostMethod(url,null,callback);
	}

	
//	/**
//	 * get请求的核心方法
//	 */
//	public AysResponse doQuestByGet(String url,HttpParams params,boolean isCookie,HttpCallback callback){
//		
//	}
	
	/**
	 * get请求，需要传递url，参数，cookie
	 */
	public void doQuestByGetMethod(String url, HttpParams params,
			boolean isCookie,HttpCallback callback) {
		// TODO Auto-generated method stub
	}

	/**
	 * get请求，需要传递url，参数
	 */
	public void doQuestByGetMethod(String url, HttpParams params,HttpCallback callback) {
		// TODO Auto-generated method stub
		doQuestByPostMethod(url, params, false,callback);
	}

	/**
	 * get请求，需要传递url
	 */
	public void doQuestByGetMethod(String url,HttpCallback callback) {
		// TODO Auto-generated method stub
		doQuestByGetMethod(url,null,callback);
	}
	
	
	private class MyHttpTask extends AsyncTask<Object, Void, AysResponse>{
		private HttpCallback callback;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected AysResponse doInBackground(Object... params) {
			// TODO Auto-generated method stub
			AysResponse aysResponse = null;
			String url = (String) params[0];
			HttpParams param = (HttpParams) params[1];
			boolean isCookie = (Boolean) params[2];
			String method = (String) params[3];
			HttpCallback callback = (HttpCallback) params[4];
			this.callback  = callback;
			if(method.equals("POST")){
				//post
				aysResponse = doQuestByPost(url,param,isCookie,callback);
			}else{
				//get
//				aysResponse = doQuestByGet(url,param,isCookie,callback);
			}
			return aysResponse;
		}
		
		
		


		@Override
		protected void onPostExecute(AysResponse result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result.getException() == null){
				//没有异常
				String content = result.getResult();
				callback.success(content);
			}else{
				//把异常转为字符串提示用户、
				String exception = parseException(result.getException());
				callback.fail(exception);
			}
		}





	}

	
	
/**
 * 超时异常
 */
	private void setTimeOutException(){
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, MyConstants.C_TIMEOUT);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, MyConstants.S_TIMEOUT);
	}

	

	private String parseException(Exception exception) {
		// TODO Auto-generated method stub
		String str = null;
		if(exception instanceof IllegalArgumentException){
			str = "数据异常，请检查数据格式";
		}else if(exception instanceof ClientProtocolException){
			str = "请求异常，请重新尝试";
		}else{
			str = "无法连接服务器，请检查网络";
		}
		return str;
	}
	
	/**
	 * 讲返回的json字符串转出对象
	 */
	
	public <T extends HttpBaseResult> T formatResult(String json,Class<T> clazz){
		T newInstance;
		newInstance = JSON.parseObject(json,clazz);
		return newInstance;
	}
}
