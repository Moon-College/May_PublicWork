package com.hq.ays.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.hq.ays.entity.request.HttpParam;
import com.hq.ays.entity.response.AysResponse;
import com.hq.ays.entity.result.ResultCallback;
import com.hq.ays.utils.MyConstants;
import com.hq.ays.utils.MyLog;
import com.hq.ays.activity.R;

public class HttpRequest implements IHttpRequest {
	private HttpClient client;
	private Context context;
	public HttpRequest(Context context) {
		client = MyApacheHttpClient.getInstance();
		this.context = context;
	}

	public AysResponse RequestByPost(String url, HttpParam params,
			NameValuePair header) {
		AysResponse aysResponse = new AysResponse();
		HttpPost post = new HttpPost(url);
		MyLog.i(url);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		// 反射获取该对象的字段
		if (params != null) {
			Field[] declaredFields = params.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				try {
					if (field.get(params) != null) {
						String name = field.getName();// 键
						String value = null;
						try {
							value = String.valueOf(field.get(params));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							aysResponse.setException(e);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							aysResponse.setException(e);
						}
						NameValuePair pair = new BasicNameValuePair(name, value);
						parameters.add(pair);
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				}
			}
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(parameters);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		}
		post.setEntity(entity);// 传递实体数据
		if (header != null) {
			post.setHeader(header.getName(), header.getValue());
		}

		try {
			// 连接超时和读取超时
			setTimeOutForRequest();

		} catch (Exception e) {
			// TODO: handle exception
			aysResponse.setException(new TimeoutException());
		}
		try {
			HttpResponse resp = client.execute(post);
			int statuCode = resp.getStatusLine().getStatusCode();// 返回状态码
			MyLog.d("statuCode:" + statuCode);
			if (statuCode == 200) {
				// 请求成功
				String content = EntityUtils
						.toString(resp.getEntity(), "utf-8");
				aysResponse.setString(content);
				aysResponse.setHeaders(resp.getAllHeaders());
				MyLog.d("result:" + content);
			} else {
				aysResponse.setException(new Exception());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		}
		return aysResponse;
	}

	public AysResponse RequestByPost(String url, HttpParam params) {
		return RequestByPost(url, params, null);
	}

	public AysResponse RequestByPost(String url) {
		// TODO Auto-generated method stub
		return RequestByPost(url, null);
	}

	/**
	 * 设置请求超时异常
	 */
	public void setTimeOutForRequest() {
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT,
				MyConstants.CONNECTION_TIMEOUT);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				MyConstants.SO_TIMEOUT);
	}

	public AysResponse RequestByGet(String url, HttpParam params,
			NameValuePair header) {
		AysResponse aysResponse = new AysResponse();
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		if (params != null) {
			Field[] declaredFields = params.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				try {
					if (field.get(params) != null) {
						sb.append(field.getName());
						sb.append("=");
						try {
							sb.append(field.get(params));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							aysResponse.setException(e);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							aysResponse.setException(e);
						}
						sb.append("&");
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		url = url + sb.toString();
		MyLog.d(url);
		HttpGet get = new HttpGet();
		if (header != null) {
			get.setHeader(header.getName(), header.getValue());
		}
		// 超时判断
		try {
			setTimeOutForRequest();
		} catch (Exception e) {
			aysResponse.setException(new TimeoutException());
		}
		try {
			HttpResponse resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			MyLog.d("statuCode:" + statusCode);
			if (statusCode == 200) {
				// 请求成功了
				String string = EntityUtils.toString(resp.getEntity(), "utf-8");
				aysResponse.setString(string);
				// 获取从服务端传过来的响应头
				aysResponse.setHeaders(resp.getAllHeaders());
			} else {
				aysResponse.setException(new Exception());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		}
		return aysResponse;
	}

	public AysResponse RequestByGet(String url, HttpParam params) {
		// TODO Auto-generated method stub

		return RequestByGet(url, params, null);
	}

	public AysResponse RequestByGet(String url) {
		// TODO Auto-generated method stub
		return RequestByGet(url, null, null);
	}

	/**
	 * 根据Resp直接解析返回结果的实体类
	 * @param resp
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends ResultCallback> T formatResponse(AysResponse resp,T clazz){
		if(resp.getException() == null){
			//获取字符串解析成对象
			clazz = (T)JSON.parseObject(resp.getString(), clazz.getClass());
		}
		return clazz;
	}
	
	public String formatException(AysResponse resp){
		if(resp.getException() instanceof TimeoutException){
			return context.getString(R.string.timeout_excepiton);
		}else if(resp.getException() instanceof IOException){
			return context.getString(R.string.service_error);
		}else{
			return context.getString(R.string.data_excption);
		}
	}
}
