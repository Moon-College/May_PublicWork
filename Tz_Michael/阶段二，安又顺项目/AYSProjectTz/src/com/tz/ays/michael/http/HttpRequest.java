package com.tz.ays.michael.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.tz.ays.michael.bean.request.IHttpParams;
import com.tz.ays.michael.callback.IHttpCallBack;
import com.tz.ays.michael.common.Constrant;
import com.tz.ays.michael.utils.LogUtils;
import com.tz.ays.michael.utils.SharedPreferenceUtil;
/**
 * 网络请求工具类
 * @author michael
 *
 */
public class HttpRequest implements IHttpRequest{

	private Context mContext;
	private HttpClient client;
	
	public HttpRequest(Context context){
		this.mContext=context;
		client=MyHttpClient.getClient();
	}
	
	/**
	 * post方式请求
	 * @param path 请求路径
	 * @param params IHttpParams的一个实现类对象
	 * @param needCookie boolean是否需要传递cookie
	 * @param callBack IHttpCallBack的对象，http回调接口
	 */
	public void doQuestByPostMethod(String path, IHttpParams params,
			boolean needCookie,IHttpCallBack callBack) {
		MyAsyncTask task=new MyAsyncTask();
		task.execute(path,params,needCookie,"POST",callBack);
	}

	/**
	 * 重载的2个参数的post方式请求
	 */
	public void doQuestByPostMethod(String path, IHttpParams params,IHttpCallBack callBack) {
		doQuestByPostMethod(path, params, false,callBack);
	}

	/**
	 * 重载的1个参数的post方式请求
	 */
	public void doQuestByPostMethod(String path,IHttpCallBack callBack) {
		doQuestByPostMethod(path, null,callBack);
	}

	/**
	 * get方式请求
	 * @param path 请求路径
	 * @param params IHttpParams的一个实现类对象
	 * @param needCookie boolean是否需要传递cookie
	 * @param callBack IHttpCallBack的对象，http回调接口
	 */
	public void doQuestByGetMethod(String path, IHttpParams params,
			boolean needCookie,IHttpCallBack callBack) {
		MyAsyncTask task=new MyAsyncTask();
		task.execute(path,params,needCookie,"GET",callBack);
	}

	/**
	 * 重载的2个参数的get方式请求
	 */
	public void doQuestByGetMethod(String path, IHttpParams params,IHttpCallBack callBack) {
		doQuestByGetMethod(path, params, false,callBack);
	}

	/**
	 * 重载的1个参数的get方式请求
	 */
	public void doQuestByGetMethod(String path,IHttpCallBack callBack) {
		doQuestByGetMethod(path, null,callBack);
	}

	/**
	 * 多文件上传请求
	 * @param path 请求路径
	 * @param params IHttpParams的一个实现类对象
	 * @param needCookie boolean是否需要传递cookie
	 * @param callBack IHttpCallBack的对象，http回调接口
	 */
	public void doMultiQuestByPostMethod(String path,
			IHttpParams params, boolean needCookie,IHttpCallBack callBack) {
		MyAsyncTask task=new MyAsyncTask();
		task.execute(path,params,needCookie,"MULTI_FILE",callBack);
	}

	private class MyAsyncTask extends AsyncTask<Object, Void, ASYResponse>{

		private IHttpCallBack callBack;

		@Override
		protected ASYResponse doInBackground(Object... params) {
			ASYResponse response=null;
			String url=(String) params[0];
			IHttpParams param=(IHttpParams) params[1];
			boolean nedCoookie=(Boolean) params[2];
			String method=(String) params[3];
			callBack = (IHttpCallBack) params[4];
			if(method.equals("POST")){
				response=requestByPost(url,param,nedCoookie);
			}else if(method.equals("GET")){
				response=requestByGet(url,param,nedCoookie);
			}else if(method.equals("MULTI_FILE")){
				response=requestMultiFileByPost(url,param,nedCoookie);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(ASYResponse result) {
			super.onPostExecute(result);
			if(result.getException()!=null){
				//把异常转为字符串提示用户、
				callBack.failed(parseException(result.getException()));
			}else{
				//没有异常
				callBack.success(result.getResult());
			}
		}
		
	}

	/**
	 * 核心post方法
	 * @param url
	 * @param param
	 * @param nedCoookie
	 * @return ASYResponse
	 */
	public ASYResponse requestByPost(String url, IHttpParams param, boolean nedCoookie) {
		LogUtils.e("asy--", "http post url:"+url+"--params:"+param.toString());
		ASYResponse asyResponse=new ASYResponse();
		HttpPost post=new HttpPost(url);
		if(param!=null){
			List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
			Class<? extends IHttpParams> class1 = param.getClass();
			Field[] declaredFields = class1.getDeclaredFields();
			for(Field field:declaredFields){
				field.setAccessible(true);
				String key=field.getName();
				Object object;
				try {
					object = field.get(param);
					if(object!=null){
						BasicNameValuePair nameValuePair=new BasicNameValuePair(key, String.valueOf(object));
						parameters.add(nameValuePair);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				}
			}
			UrlEncodedFormEntity entity;
			try {
				//解决中文乱码问题
				entity = new UrlEncodedFormEntity(parameters,"utf-8");
				post.setEntity(entity);
				if(nedCoookie){//经常用来登陆验证
					String cookie=(String) SharedPreferenceUtil.getValueFromSP(mContext, Constrant.COOKIE, Constrant.COOKIE_KEY, String.class);
					post.addHeader(Constrant.COOKIE_KEY, cookie);
				}
				//设置连接超时
				post.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				//设置socket超时
				post.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpResponse httpResponse=client.execute(post);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					LogUtils.e("asy--", "post请求成功");
					String content=EntityUtils.toString(httpResponse.getEntity());
					asyResponse.setResult(content);
					//保存服务端返回的cookie
					asyResponse.setHeaders(httpResponse.getAllHeaders());
				}else{
					asyResponse.setException(new IllegalArgumentException());
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (IOException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			}
		}
		return asyResponse;
	}

	/**
	 * 多文件上传的核心方法
	 * @param url
	 * @param param
	 * @param nedCoookie
	 * @return
	 */
	public ASYResponse requestMultiFileByPost(String url, IHttpParams param,
			boolean nedCoookie) {
		LogUtils.e("asy--", "http post multi_file url:"+url+"--params:"+param.toString());
		ASYResponse asyResponse=new ASYResponse();
		HttpPost post=new HttpPost(url);
		//文件内容体
		MultipartEntity entity=new MultipartEntity();
		if(param!=null){
			Class<? extends IHttpParams> class1 = param.getClass();
			Field[] declaredFields = class1.getDeclaredFields();
			for(Field field:declaredFields){
				field.setAccessible(true);
				String key=field.getName();
				Object object;
				try {
					object = field.get(param);
					if(object!=null){
						if(field.getType().getClass().getName().equals("File")){
							//是文件
							FileBody fileBody=new FileBody((File) object);
							entity.addPart(key, fileBody);
						}else{
							//不是文件
							StringBody stringBody=new StringBody(String.valueOf(object), Charset.forName("UTF-8"));
							entity.addPart(key, stringBody);
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				}
			}
			try {
				post.setEntity(entity);
				if(nedCoookie){//经常用来登陆验证
					String cookie=(String) SharedPreferenceUtil.getValueFromSP(mContext, Constrant.COOKIE, Constrant.COOKIE_KEY, String.class);
					post.addHeader(Constrant.COOKIE_KEY, cookie);
				}
				//设置连接超时
				post.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				//设置socket超时
				post.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpResponse httpResponse=client.execute(post);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					LogUtils.e("asy--", "post请求成功");
					String content=EntityUtils.toString(httpResponse.getEntity());
					asyResponse.setResult(content);
					//保存服务端返回的cookie
					asyResponse.setHeaders(httpResponse.getAllHeaders());
				}else{
					asyResponse.setException(new IllegalArgumentException());
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (IOException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			}
		}
		return asyResponse;
	}

	/**
	 * 根据异常来提示信息给用户
	 * @param exception
	 * @return
	 */
	public String parseException(Exception exception) {
		String exception_des=null;
		if(exception instanceof IllegalArgumentException){
			exception_des="参数非法，请检查数据格式";
		}else if(exception instanceof ClientProtocolException){
			exception_des="请求异常，请重新尝试";
		}else{
			exception_des="无法连接服务器，请检查网络";
		}
		return exception_des;
	}

	/**
	 * 核心get方法
	 * @param url
	 * @param param
	 * @param nedCoookie
	 * @return ASYResponse
	 */
	public ASYResponse requestByGet(String url, IHttpParams param,
			boolean nedCoookie) {
		LogUtils.e("asy--", "http get url:"+url+"--params:"+param.toString());
		ASYResponse asyResponse=new ASYResponse();
		StringBuffer sb=new StringBuffer();
		if(param!=null){
			sb.append("?");
			Class<? extends IHttpParams> class1 = param.getClass();
			Field[] declaredFields = class1.getDeclaredFields();
			for(Field field:declaredFields){
				field.setAccessible(true);
				String key=field.getName();
				Object object;
				try {
					object = field.get(param);
					if(object!=null){
						sb.append(key+"="+String.valueOf(object));
						sb.append("&");
					}
					sb=sb.deleteCharAt(sb.length()-1);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					asyResponse.setException(e);
				}
			}
			HttpGet get=new HttpGet(url+sb.toString());
			try {
				if(nedCoookie){//经常用来登陆验证
					String cookie=(String) SharedPreferenceUtil.getValueFromSP(mContext, Constrant.COOKIE, Constrant.COOKIE_KEY, String.class);
					get.addHeader(Constrant.COOKIE_KEY, cookie);
				}
				//设置连接超时
				get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
				//设置socket超时
				get.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				HttpResponse httpResponse=client.execute(get);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					LogUtils.e("asy--", "post请求成功");
					String content=EntityUtils.toString(httpResponse.getEntity());
					asyResponse.setResult(content);
					//保存服务端返回的cookie
					asyResponse.setHeaders(httpResponse.getAllHeaders());
				}else{
					asyResponse.setException(new IllegalArgumentException());
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			} catch (IOException e) {
				e.printStackTrace();
				asyResponse.setException(e);
			}
		}
		return asyResponse;
	}
	
}
