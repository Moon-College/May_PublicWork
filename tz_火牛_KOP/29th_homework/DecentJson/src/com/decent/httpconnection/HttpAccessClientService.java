package com.decent.httpconnection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.decent.decentutil.DecentLogUtil;
import com.decent.httpconnection.bean.HttpResult;

public class HttpAccessClientService implements HttpAccessService {

	private static final String TAG = "HttpConnectionService";
	/**
	 * 回调函数
	 */
	private HttpAccessCallback mHttpAccessCallback;
	/**
	 * 访问路径
	 */
	private String url;
	/**
	 * 提交请求的方式
	 */
	private String method;
	/**
	 * 参数
	 */
	private Map<String, String> parameters;

	public HttpAccessClientService(HttpAccessCallback mHttpAccessCallback,
			String url, String method, Map<String, String> parameters) {
		super();
		this.mHttpAccessCallback = mHttpAccessCallback;
		this.url = url;
		this.method = method;
		this.parameters = parameters;
	}

	@Override
	public HttpAccessCallback getHttpAccessCallback() {
		// TODO Auto-generated method stub
		return mHttpAccessCallback;
	}

	@Override
	public void setHttpAccessCallback(HttpAccessCallback mHttpAccessCallback) {
		// TODO Auto-generated method stub
		this.mHttpAccessCallback = mHttpAccessCallback;
	}

	@Override
	public void doRequestHttpAccessAsync() {
		// TODO Auto-generated method stub
		AsyncHttpRequestTask task = new AsyncHttpRequestTask(this);
		task.execute(null);
	}

	@Override
	public HttpResult doRequestHttpAccess() {
		HttpResult httpResult = null;
		if (GET_METHOD.equals(method)) {
			httpResult = doRequestHttpConnectionByGetMethod();
		} else if (POST_METHOD.equals(method)) {
			httpResult = doRequestHttpConnectionByPostMethod();
		} else {
			httpResult = new HttpResult();
			httpResult.setResultCode(NOT_SUPPORTED_METHOD);
			httpResult.setContent(NOT_SUPPORTED_METHOD_STR);
		}
		return httpResult;
	}

	private HttpResult doRequestHttpConnectionByPostMethod() {
		DecentLogUtil.d("INFO", "into doRequestHttpConnectionByPostMethod");
		HttpResult result = new HttpResult();
		UrlEncodedFormEntity ueFormEntity = null;
		if (parameters != null) {
			List<BasicNameValuePair> paras = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> para : parameters.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair(para.getKey(),
						para.getValue());
				paras.add(pair);
			}
			try {
				ueFormEntity = new UrlEncodedFormEntity(paras);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setResultCode(HttpResult.URL_ERROR);
				result.setContent(e.getMessage());
				return result;
			}
		}
		
		HttpPost httpPost = new HttpPost(url);
		if(ueFormEntity!=null){
			httpPost.setEntity(ueFormEntity);
		}

		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse resp = httpClient.execute(httpPost);
			int statusCode = resp.getStatusLine().getStatusCode();
			result.setResultCode(statusCode);
			if(statusCode == HttpResult.HTTP_OK){
				HttpEntity httpEntity = resp.getEntity();
				String content = EntityUtils.toString(httpEntity,EntityUtils.getContentCharSet(httpEntity));
				result.setContent(content);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent(e.getMessage());
			return result;			
		} 
		
		// TODO Auto-generated method stub
		return result;
	}

	private HttpResult doRequestHttpConnectionByGetMethod() {
		DecentLogUtil.d("INFO", "into doRequestHttpConnectionByGetMethod");
		HttpResult result = new HttpResult();
		// 1、遍历parameters，构造参数列表，拼接到url后面
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		if (parameters != null) {

			for (Map.Entry<String, String> para : parameters.entrySet()) {
				sb.append(para.getKey());
				sb.append("=");
				sb.append(para.getValue());
				sb.append("&");
			}
		}
		// 删除掉最后的那个字符(可能是多余的那个?或者&)，位置是从0开始计算的
		sb.deleteCharAt(sb.length() - 1);

		DecentLogUtil.d("INFO", "doRequestHttpConnectionByGetMethod access "+url + sb.toString());
		HttpGet httpGet = new HttpGet(url + sb.toString());
		HttpClient httpClient = new DefaultHttpClient();
		try {
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 5000);
			HttpResponse resp = httpClient.execute(httpGet);
			int statusCode = resp.getStatusLine().getStatusCode();
			result.setResultCode(statusCode);
			if (statusCode == HttpResult.HTTP_OK) {
				HttpEntity httpEntity = resp.getEntity();
				String content = EntityUtils.toString(httpEntity,
						EntityUtils.getContentCharSet(httpEntity));
				result.setContent(content);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent(e.getMessage());
		}
		return result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

}
