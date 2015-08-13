package com.decent.courier.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.decent.courier.bean.request.HttpParams;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.MyDataUtils;

/**
 * http�����ʵ����
 * 
 * @author K450J
 * 
 */
public class HttpRequest implements IHttpRequest {
	private Context context;
	private HttpClient client;
	
	public HttpRequest(Context context) {
		this.context = context;
		client = DecentHttpClient.getInstance();
	}

	@Override
	public void doMultiQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie, IHttpRequestCallback httpRequestCallback) {

	}

	@Override
	public void doQuestByPostMethod(String url, HttpParams httpParams,
			boolean isUseCookie, IHttpRequestCallback httpRequestCallback) {
		HttpRequestAsyncTask requestTask = new HttpRequestAsyncTask();
		requestTask.execute(url, httpParams, Boolean.valueOf(isUseCookie),
				httpRequestCallback,DecentConstants.POST_METHOD);
	}

	@Override
	public void doQuestByPostMethod(String url, HttpParams httpParams,
			IHttpRequestCallback httpRequestCallback) {
		// TODO Auto-generated method stub
		doQuestByPostMethod(url, httpParams, false, httpRequestCallback);
	}

	@Override
	public void doQuestByPostMethod(String url,
			IHttpRequestCallback httpRequestCallback) {
		// TODO Auto-generated method stub
		doQuestByPostMethod(url, null, false, httpRequestCallback);
	}

	@Override
	public void doQuestByGetMethod(String url, HttpParams httpParams,
			boolean isUseCookie, IHttpRequestCallback httpRequestCallback) {
		HttpRequestAsyncTask requestTask = new HttpRequestAsyncTask();
		requestTask.execute(url, httpParams, Boolean.valueOf(isUseCookie),
				httpRequestCallback,DecentConstants.GET_METHOD);
	}

	@Override
	public void doQuestByGetMethod(String url, HttpParams httpParams,
			IHttpRequestCallback httpRequestCallback) {
		// TODO Auto-generated method stub
		doQuestByGetMethod(url, httpParams, false, httpRequestCallback);
	}

	@Override
	public void doQuestByGetMethod(String url,
			IHttpRequestCallback httpRequestCallback) {
		// TODO Auto-generated method stub
		doQuestByGetMethod(url, null, false, httpRequestCallback);
	}

	private String getExceptionMessage(Exception exception) {
		// TODO Auto-generated method stub
		return exception.toString()+":"+exception.getMessage();
	}

	public AsyResponse doRequestHttpConnectionByPostMethod(String url,
			HttpParams httpParams, Boolean isUseCookie) {
		AsyResponse asyResponse = new AsyResponse();
		HttpPost httpPost = new HttpPost(url);
		/*
		 * ����httpParams����
		 */
		List<BasicNameValuePair> parameters = null;
		if (httpParams != null) {
			parameters = new ArrayList<BasicNameValuePair>();
			// ʹ�÷���������BasicNameValuePair
			Class<? extends HttpParams> clazz = httpParams.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object fieldValue = null;
				try {
					fieldValue = field.get(httpParams);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					asyResponse.setException(e);
					return asyResponse;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					asyResponse.setException(e);
					return asyResponse;
				}
				String strValue = null;
				if (fieldValue != null) {
					strValue = String.valueOf(fieldValue);
				}
				BasicNameValuePair pair = new BasicNameValuePair(fieldName,
						strValue);
				parameters.add(pair);
				field.setAccessible(false);
			}
		}

		/*
		 * ���parameters��ֵ������form���ݵ�httpPost����
		 */
		if (parameters != null) {
			UrlEncodedFormEntity formEntity = null;
			try {
				formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
			} catch (UnsupportedEncodingException e) {
				asyResponse.setException(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
				return asyResponse;
			}
			httpPost.setEntity(formEntity);
		}

		/*
		 * ���ʹ��cookie������header��DecentConstants.SETCOOKIE
		 */
		if (isUseCookie) {
			httpPost.addHeader(DecentConstants.SETCOOKIE, (String) MyDataUtils
					.getData(context, DecentConstants.COOKIE,
							DecentConstants.SETCOOKIE, String.class));
		}
		try {
			HttpResponse httpResponse = client.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			DecentLogUtil.d("statusCode="+statusCode);
			// ���http���سɹ�200
			if (statusCode == DecentConstants.HTTP_OK) {
				// ����header������cookie
				Header[] headers = httpResponse.getAllHeaders();
				if (headers != null) {
					MyDataUtils.savaHead(context, headers);
				}
				// ����body
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					String content = EntityUtils.toString(httpEntity,
							EntityUtils.getContentCharSet(httpEntity));
					asyResponse.setResult(content);
				}
			} else {
				asyResponse.setException(new IllegalAccessException());
				return asyResponse;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			asyResponse.setException(e);
			return asyResponse;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			asyResponse.setException(e);
			return asyResponse;
		}
		return asyResponse;
	}

	private AsyResponse doRequestHttpConnectionByGetMethod(String url,
			HttpParams httpParams, Boolean isUseCookie) {
		AsyResponse asyResponse = new AsyResponse();

		// 1������parameters����������б�ƴ�ӵ�url����
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		//�������httpParams,���sb
		
		if (httpParams != null) {
			Class<? extends HttpParams> clazz = HttpParams.class;
			Field[] fileds = clazz.getDeclaredFields();
			for(Field field:fileds){
				field.setAccessible(true);
				String fieldName = field.getName();
				Object fieldValue = null;
				try {
					fieldValue = field.get(httpParams);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					asyResponse.setException(e);
					return asyResponse;					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					asyResponse.setException(e);
					return asyResponse;					
				}
		        if(fieldValue!=null){
					sb.append(fieldName);
					sb.append("=");
					sb.append(String.valueOf(fieldValue));
					sb.append("&");		        	
		        }
			}
		}
		// ɾ���������Ǹ��ַ�(�����Ƕ�����Ǹ�?����&)��λ���Ǵ�0��ʼ�����
		sb.deleteCharAt(sb.length() - 1);

		//ƴ��url����ʼ��HttpGet
		HttpGet httpGet = new HttpGet(url + sb.toString());
		
		/*
		 * ���ʹ��cookie������header��DecentConstants.SETCOOKIE
		 */
		if (isUseCookie) {
			httpGet.addHeader(DecentConstants.SETCOOKIE, (String) MyDataUtils
					.getData(context, DecentConstants.COOKIE,
							DecentConstants.SETCOOKIE, String.class));
		}
		try {
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			client.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 5000);
			HttpResponse httpResponse = client.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			DecentLogUtil.d("statusCode="+statusCode);
			if (statusCode == DecentConstants.HTTP_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String content = EntityUtils.toString(httpEntity,
						EntityUtils.getContentCharSet(httpEntity));
				asyResponse.setResult(content);
			}else{
				asyResponse.setException(new IllegalArgumentException());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			asyResponse.setException(e);
			return asyResponse;
		}
				
		// TODO Auto-generated method stub
		return asyResponse;
	}

	public class HttpRequestAsyncTask extends
			AsyncTask<Object, Void, AsyResponse> {

		private IHttpRequestCallback requestCallback;

		@Override
		protected AsyResponse doInBackground(Object... params) {
			AsyResponse asyResponse;

			// ��ȡ����
			// requestTask.execute(url,httpParams,isUseCookie,httpRequestCallback,method);
			String url = (String) params[0];
			HttpParams httpParams = (HttpParams) params[1];
			Boolean isUseCookie = (Boolean) params[2];
			this.requestCallback = (IHttpRequestCallback) params[3];
			String method = (String) params[4];
			if (DecentConstants.POST_METHOD.equals(method)) {
				asyResponse = doRequestHttpConnectionByPostMethod(url,
						httpParams, isUseCookie);
			} else if (DecentConstants.GET_METHOD.equals(method)) {
				asyResponse = doRequestHttpConnectionByGetMethod(url,
						httpParams, isUseCookie);
			} else {
				asyResponse = new AsyResponse();
				asyResponse.setException(new IllegalArgumentException(
						DecentConstants.NOT_SUPPORTED_METHOD_STR));
			}
			// TODO Auto-generated method stub
			return asyResponse;
		}

		@Override
		protected void onPostExecute(AsyResponse result) {
			Exception e = result.getException();
			if (e == null) {
				requestCallback.onRequestSuccess(result.getResult());
			} else {
				requestCallback.onRequestFail(getExceptionMessage(e));
			}
		}
	}
}
